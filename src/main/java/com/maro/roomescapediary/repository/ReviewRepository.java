package com.maro.roomescapediary.repository;

import com.maro.roomescapediary.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewCustomRepository {

}
