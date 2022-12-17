package com.maro.roomescapediary.theme.repository;

import static com.maro.roomescapediary.theme.entity.QTheme.theme;

import com.maro.roomescapediary.common.utils.CommonUtils;
import com.maro.roomescapediary.theme.dto.ThemeDto;
import com.maro.roomescapediary.theme.dto.ThemeSearchDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ThemeRepositoryImpl implements ThemeCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ThemeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<ThemeDto> searchTheme(ThemeSearchDto searchDto, Pageable pageable) {
        List<ThemeDto> contents = queryFactory
            .select(
                Projections.constructor(ThemeDto.class,
                    theme.seq,
                    theme.store.seq,
                    theme.name,
                    theme.price,
                    theme.difficultyRating,
                    theme.desc,
                    theme.url,
                    theme.useFlag
                )
            ).from(theme)
            .where(
                theme.name.like(CommonUtils.makeLikeStr(searchDto.getName())),
                theme.price.goe(searchDto.getLowPrice()),
                theme.price.loe(searchDto.getHighPrice()),
                theme.difficultyRating.goe(searchDto.getLowRating()),
                theme.difficultyRating.loe(searchDto.getHighRating())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(theme.count())
            .from(theme)
            .where(
                theme.name.like(CommonUtils.makeLikeStr(searchDto.getName())),
                theme.price.goe(searchDto.getLowPrice()),
                theme.price.loe(searchDto.getHighPrice()),
                theme.difficultyRating.goe(searchDto.getLowRating()),
                theme.difficultyRating.loe(searchDto.getHighRating())
            )
            .fetchOne();

        return new PageImpl<>(contents, pageable, count);
    }
}
