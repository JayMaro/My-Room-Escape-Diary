package com.maro.roomescapediary.repository;

import static com.maro.roomescapediary.entity.QStore.store;

import com.maro.roomescapediary.dto.StoreDto;
import com.maro.roomescapediary.dto.StoreSearchDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public class StoreRepositoryImpl implements StoreCustomRepository{

    private final JPAQueryFactory queryFactory;

    public StoreRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<StoreDto> searchStore(StoreSearchDto searchDto, Pageable pageable) {
        List<StoreDto> contents = queryFactory
            .select(
                Projections.constructor(StoreDto.class,
                    store.seq,
                    store.address,
                    store.name,
                    store.branchName,
                    store.url,
                    store.useFlag
                )
            )
            .from(store)
            .where(
                StringUtils.hasText(searchDto.getName()) ? store.name.like("%" + searchDto.getName() + "%") : null,
                StringUtils.hasText(searchDto.getBranchName()) ? store.branchName.like("%" + searchDto.getBranchName() + "%") : null
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(store.count())
            .from(store)
            .fetchOne();
        return new PageImpl<>(contents, pageable, count);
    }
}
