package com.maro.roomescapediary.repository;

import com.maro.roomescapediary.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findUserById(String id);

}
