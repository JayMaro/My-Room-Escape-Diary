package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.UserDto;
import com.maro.roomescapediary.entity.Users;
import com.maro.roomescapediary.repository.UserRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto searchUser(int userSeq) {
        Users user = this.findById(userSeq);
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
        Users user = this.findById(userDto.getSeq());
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
        Users user = this.findById(userSeq);
        user.delete();
    }

    public Users findById(int userSeq) {
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
}
