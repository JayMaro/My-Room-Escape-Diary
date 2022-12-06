package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.UserDto;
import com.maro.roomescapediary.entity.Users;
import com.maro.roomescapediary.repository.UserRepository;
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
        userRepository.save(userDto.toEntity());
    }

    @Transactional
    public void modifyUser(UserDto userDto) {
        Users user = this.findById(userDto.getSeq());
        user.updateUser(userDto);
    }

    @Transactional
    public void removeUser(int userSeq) {
        Users user = this.findById(userSeq);
        user.delete();
    }

    public Users findById(int userSeq) {
        return userRepository.findById(userSeq).orElseThrow(IllegalArgumentException::new);
    }
    
}
