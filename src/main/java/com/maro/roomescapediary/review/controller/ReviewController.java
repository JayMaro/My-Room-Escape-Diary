package com.maro.roomescapediary.review.controller;

import com.maro.roomescapediary.review.dto.ReviewDto;
import com.maro.roomescapediary.review.dto.ReviewSearchDto;
import com.maro.roomescapediary.review.service.ReviewService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public Page<ReviewDto> searchReviews(ReviewSearchDto searchDto, Pageable pageable) {
        return reviewService.searchReviews(searchDto, pageable);
    }

    @GetMapping("/{reviewSeq}")
    public ReviewDto searchReview(@PathVariable int reviewSeq) {
        return reviewService.searchReview(reviewSeq);
    }

    @PostMapping
    public void addReview(@Valid @RequestBody ReviewDto reviewDto) {
        reviewService.addReview(reviewDto);
    }

    @PutMapping("/{reviewSeq}")
    public void modifyReview(@PathVariable int reviewSeq, @Valid @RequestBody ReviewDto reviewDto) {
        reviewDto.setSeq(reviewSeq);
        reviewService.modifyReview(reviewDto);
    }

    @DeleteMapping("/{reviewSeq}")
    public void removeReview(@PathVariable int reviewSeq) {
        reviewService.removeReview(reviewSeq);
    }
}
