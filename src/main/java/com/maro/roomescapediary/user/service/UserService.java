package com.maro.roomescapediary.user.service;

import com.maro.roomescapediary.common.exceptions.ValidateException;
import com.maro.roomescapediary.user.dto.UserDto;
import com.maro.roomescapediary.user.entity.Authority;
import com.maro.roomescapediary.user.entity.Users;
import com.maro.roomescapediary.user.repository.UserRepository;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto searchUser(int userSeq) {
        Users user = this.findByUserSeq(userSeq);
        return user.toDto();
    }

    @Transactional
    public void signUp(UserDto userDto) {
        userRepository.findUserById(userDto.getId()).ifPresent(users -> {
            new ValidateException("이미 존재하는 아이디입니다.");
        });
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Authority authority = new Authority("ADMIN");
        userDto.setAuthorities(Collections.singleton(authority));
        userRepository.save(userDto.toEntity());
    }

    @Transactional
    public void modifyUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users user = this.findByUserSeq(userDto.getSeq());
        user.updateUser(userDto);
    }

    @Transactional
    public void removeUser(int userSeq) {
        Users user = this.findByUserSeq(userSeq);
        user.delete();
    }

    public Users findByUserSeq(int userSeq) {
        return userRepository.findById(userSeq).orElseThrow(IllegalArgumentException::new);
    }
}
