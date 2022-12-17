package com.maro.roomescapediary.store.repository;

import com.maro.roomescapediary.store.dto.StoreDto;
import com.maro.roomescapediary.store.dto.StoreSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreCustomRepository {

    Page<StoreDto> searchStore(StoreSearchDto searchDto, Pageable pageable);
}
