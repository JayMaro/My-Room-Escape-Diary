package com.maro.roomescapediary.repository;

import com.maro.roomescapediary.dto.ReviewDto;
import com.maro.roomescapediary.dto.ReviewSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewCustomRepository {

    Page<ReviewDto> searchReviews(ReviewSearchDto searchDto, Pageable pageable);

}
