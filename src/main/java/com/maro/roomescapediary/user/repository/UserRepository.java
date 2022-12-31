package com.maro.roomescapediary.user.repository;

import com.maro.roomescapediary.user.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findUserById(String id);

}
