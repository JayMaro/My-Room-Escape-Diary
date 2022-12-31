package com.maro.roomescapediary.user.service;

import com.maro.roomescapediary.user.entity.Users;
import com.maro.roomescapediary.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findUserById(id)
            .map(user -> createUser(id, user))
            .orElseThrow(() -> new UsernameNotFoundException(id + " -> 해당 id를 찾을수 없습니다."));
    }

    private User createUser(String id, Users user) {
        if (!user.getUseFlag()) {
            throw new RuntimeException(id + " -> 비활성화된 계정입니다.");
        }

        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
            .collect(Collectors.toList());

        return new User(user.getId(),
            user.getPassword(),
            grantedAuthorities);
    }
}
