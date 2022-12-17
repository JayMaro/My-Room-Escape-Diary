package com.maro.roomescapediary.review.repository;

import com.maro.roomescapediary.review.dto.ReviewDto;
import com.maro.roomescapediary.review.dto.ReviewSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewCustomRepository {

    Page<ReviewDto> searchReviews(ReviewSearchDto searchDto, Pageable pageable);

}
