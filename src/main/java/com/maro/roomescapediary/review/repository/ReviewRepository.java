package com.maro.roomescapediary.review.repository;

import com.maro.roomescapediary.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewCustomRepository {

}
