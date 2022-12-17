package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.JwtDto;
import com.maro.roomescapediary.dto.LoginDto;
import com.maro.roomescapediary.dto.UserDto;
import com.maro.roomescapediary.entity.Users;
import com.maro.roomescapediary.enums.JwtCode;
import com.maro.roomescapediary.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto searchUser(int userSeq) {
        Users user = this.findByUserSeq(userSeq);
        return user.toDto();
    }

    @Transactional
    public void addUser(UserDto userDto) {
        getHashPassword(userDto);
        userRepository.save(userDto.toEntity());
    }

    @Transactional
    public void modifyUser(UserDto userDto) {
        getHashPassword(userDto);
        Users user = this.findByUserSeq(userDto.getSeq());
        user.updateUser(userDto);
    }

    private void getHashPassword(UserDto userDto) {
        String password = userDto.getPassword();
        String salt = this.getSalt();
        String hashPassword = this.encrypt(password, salt);
        userDto.setPassword(hashPassword);
        userDto.setSalt(salt);
    }

    @Transactional
    public void removeUser(int userSeq) {
        Users user = this.findByUserSeq(userSeq);
        user.delete();
    }

    public Users findByUserSeq(int userSeq) {
        return userRepository.findById(userSeq).orElseThrow(IllegalArgumentException::new);
    }

    public String getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[20];
        sr.nextBytes(salt);
        StringBuffer sb = new StringBuffer();
        for (byte b : salt) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String encrypt(String password, String salt) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((password + salt).getBytes());
            byte[] hashPassword = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : hashPassword) {
                sb.append(String.format("%02x", b));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO public private 수정 필요

    public JwtDto login(LoginDto loginDto) {
        Users users = userRepository.findUserById(loginDto.getId()).orElseThrow(IllegalArgumentException::new);
        String hashPassword = encrypt(loginDto.getPassword(), users.getSalt());
        if (!users.getPassword().equals(hashPassword)) {
            throw new RuntimeException();
        }
        UserDto userDto = users.toDto();
        Map<String, Object> claims = new HashMap<>();
        return makeJwt(claims, userDto, JwtCode.ACCESS_TOKEN);
    }

    private JwtDto makeJwt(Map<String, Object> claims, UserDto userDto, JwtCode jwtCode) {
        // TODO 에러 던지는거 변경 필요
        if (Objects.isNull(jwtCode)) {
            throw new RuntimeException();
        }

        JwtDto jwtDto = new JwtDto();
        String jti = serialize(userDto);
        String jwt = Jwts.builder()
            .setClaims(claims)
            .setId(jti)
            .setSubject(userDto.getId())
            .setExpiration(Date.from(LocalDateTime.now().plusDays(jwtCode.getTime()).atZone(ZoneId.systemDefault()).toInstant()))
            .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(SignatureAlgorithm.HS512, jwtCode.getKey())
            .compact();

        jwtDto.setJwtCode(jwtCode);
        jwtDto.setJti(jti);
        jwtDto.setJwt(jwt);

        return jwtDto;
    }

    private Jws<Claims> getJwtClaim(String jwt, JwtDto jwtDto) {
        try {
            return Jwts.parser()
                .setSigningKey(jwtDto.getJwtCode().getKey())
                .parseClaimsJws(jwt);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean validateToken(Jws<Claims> claims) {
        return !claims.getBody()
            .getExpiration()
            .before(new Date());
    }

    private String getJwtKey(Jws<Claims> claims) {
        return claims.getBody().getId();
    }

    private Object getJwtClaimValue(Jws<Claims> claims, String key) {
        return claims.getBody().get(key);
    }

    private String serialize(UserDto userDto) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(userDto);
                byte[] bytes = baos.toByteArray();
                return Base64.getEncoder().encodeToString(bytes);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserDto deserialize(String jti) {
        byte[] bytes = Base64.getDecoder().decode(jti);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object user = ois.readObject();
                return (UserDto) user;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
