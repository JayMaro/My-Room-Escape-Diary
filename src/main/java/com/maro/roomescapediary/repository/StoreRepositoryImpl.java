package com.maro.roomescapediary.repository;

import static com.maro.roomescapediary.entity.QStore.store;

import com.maro.roomescapediary.dto.StoreDto;
import com.maro.roomescapediary.dto.StoreSearchDto;
import com.maro.roomescapediary.utils.CommonUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
                store.name.like(CommonUtils.makeLikeStr(searchDto.getName())),
                store.branchName.like(CommonUtils.makeLikeStr(searchDto.getBranchName()))
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = queryFactory
            .select(store.count())
            .from(store)
            .where(
                store.name.like(CommonUtils.makeLikeStr(searchDto.getName())),
                store.branchName.like(CommonUtils.makeLikeStr(searchDto.getBranchName()))
            )
            .fetchOne();

        return new PageImpl<>(contents, pageable, count);
    }
}
