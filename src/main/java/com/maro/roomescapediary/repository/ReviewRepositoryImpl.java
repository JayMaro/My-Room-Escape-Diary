package com.maro.roomescapediary.repository;

import static com.maro.roomescapediary.entity.QReview.review;
import static com.maro.roomescapediary.entity.QTheme.theme;

import com.maro.roomescapediary.dto.ReviewDto;
import com.maro.roomescapediary.dto.ReviewSearchDto;
import com.maro.roomescapediary.utils.CommonUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ReviewRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ReviewDto> searchReviews(ReviewSearchDto searchDto, Pageable pageable) {
        List<ReviewDto> contents = queryFactory
            .select(
                Projections.constructor(ReviewDto.class,
                    review.seq,
                    review.theme.seq,
                    review.user.seq,
                    review.title,
                    review.memberCount,
                    review.successFlag,
                    review.visitDate,
                    review.reviewRating,
                    review.difficultyRating,
                    review.activityRating,
                    review.fearRating,
                    review.memo,
                    review.useFlag
                )
            ).from(review)
            .innerJoin(review.theme, theme)
            .where(
                theme.name.like(CommonUtils.makeLikeStr(searchDto.getThemeName())),
                review.title.like(CommonUtils.makeLikeStr(searchDto.getReviewTitle())),
                makeMemberCountExpression(searchDto),
                makeSuccessFlagExpression(searchDto),
                makeStartDateExpression(searchDto),
                makeEndDateExpression(searchDto),
                makeReviewRatingExpression(searchDto)
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(review.count())
            .from(review)
            .innerJoin(review.theme, theme)
            .where(
                theme.name.like(CommonUtils.makeLikeStr(searchDto.getThemeName())),
                review.title.like(CommonUtils.makeLikeStr(searchDto.getReviewTitle())),
                makeMemberCountExpression(searchDto),
                makeSuccessFlagExpression(searchDto),
                makeStartDateExpression(searchDto),
                makeEndDateExpression(searchDto),
                makeReviewRatingExpression(searchDto)
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchOne();

        return new PageImpl<>(contents, pageable, count);
    }

    private BooleanExpression makeMemberCountExpression(ReviewSearchDto searchDto) {
        if (Objects.isNull(searchDto.getMemberCount())) {
            return null;
        }
        return review.memberCount.eq(searchDto.getMemberCount());
    }

    private BooleanExpression makeSuccessFlagExpression(ReviewSearchDto searchDto) {
        if (Objects.isNull(searchDto.getSuccessFlag())) {
            return null;
        }
        return review.successFlag.eq(searchDto.getSuccessFlag());
    }

    private BooleanExpression makeStartDateExpression(ReviewSearchDto searchDto) {
        if (Objects.isNull(searchDto.getStartDate())) {
            return null;
        }
        return review.visitDate.before(searchDto.getStartDate()).not();
    }

    private BooleanExpression makeEndDateExpression(ReviewSearchDto searchDto) {
        if (Objects.isNull(searchDto.getEndDate())) {
            return null;
        }
        return review.visitDate.after(searchDto.getEndDate()).not();
    }

    private BooleanExpression makeReviewRatingExpression(ReviewSearchDto searchDto) {
        if (Objects.isNull(searchDto.getReviewRating())) {
            return null;
        }
        return review.reviewRating.eq(searchDto.getReviewRating());
    }
}
