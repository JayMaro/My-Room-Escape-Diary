package com.maro.roomescapediary.controller;

import com.maro.roomescapediary.dto.UserDto;
import com.maro.roomescapediary.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userSeq}")
    public UserDto searchUser(@PathVariable int userSeq) {
        return userService.searchUser(userSeq);
    }

    @PostMapping
    public void addUser(@Valid @RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PutMapping("/{userSeq}")
    public void modifyUser(@PathVariable int userSeq, @Valid @RequestBody UserDto userDto) {
        userDto.setSeq(userSeq);
        userService.modifyUser(userDto);
    }

    @DeleteMapping("/{userSeq}")
    public void removeUser(@PathVariable int userSeq) {
        userService.removeUser(userSeq);
    }
}
