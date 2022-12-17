package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.ReviewDto;
import com.maro.roomescapediary.dto.ReviewSearchDto;
import com.maro.roomescapediary.entity.Review;
import com.maro.roomescapediary.entity.Theme;
import com.maro.roomescapediary.entity.Users;
import com.maro.roomescapediary.repository.ReviewRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ThemeService themeService;
    private final UserService userService;
    private final ReviewRepository reviewRepository;

    public Page<ReviewDto> searchReviews(ReviewSearchDto searchDto, Pageable pageable) {
        searchDto.checkAndSetValues();
        return reviewRepository.searchReviews(searchDto, pageable);
    }

    public ReviewDto searchReview(int reviewSeq) {
        Review review = this.findById(reviewSeq);
        return review.toDto();
    }

    @Transactional
    public void addReview(ReviewDto reviewDto) {
        Theme theme = themeService.findById(reviewDto.getThemeSeq());
        Users user = userService.findById(reviewDto.getUserSeq());
        reviewRepository.save(reviewDto.toEntity(theme, user));
    }

    @Transactional
    public void modifyReview(ReviewDto reviewDto) {
        Theme theme = themeService.findById(reviewDto.getThemeSeq());
        Users user = userService.findById(reviewDto.getUserSeq());
        Review review = this.findById(reviewDto.getSeq());
        review.updateReview(reviewDto, theme, user);
    }

    @Transactional
    public void removeReview(int reviewSeq) {
        Review review = this.findById(reviewSeq);
        review.delete();
    }

    public Review findById(int reviewSeq) {
        return reviewRepository.findById(reviewSeq).orElseThrow(IllegalArgumentException::new);
    }
}
