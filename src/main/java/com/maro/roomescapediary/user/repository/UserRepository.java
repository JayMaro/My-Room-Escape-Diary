package com.maro.roomescapediary.user.repository;

import com.maro.roomescapediary.user.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findUserById(String id);

}
