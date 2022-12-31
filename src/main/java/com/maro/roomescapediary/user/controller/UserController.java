package com.maro.roomescapediary.user.controller;

import com.maro.roomescapediary.common.jwt.JwtFilter;
import com.maro.roomescapediary.common.jwt.TokenProvider;
import com.maro.roomescapediary.user.dto.LoginDto;
import com.maro.roomescapediary.user.dto.UserDto;
import com.maro.roomescapediary.user.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/{userSeq}")
    public UserDto searchUser(@PathVariable int userSeq) {
        return userService.searchUser(userSeq);
    }

    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody UserDto userDto) {
        userService.signUp(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
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
