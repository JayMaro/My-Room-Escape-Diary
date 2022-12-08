package com.maro.roomescapediary.repository;

import com.maro.roomescapediary.dto.StoreDto;
import com.maro.roomescapediary.dto.StoreSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreCustomRepository {

    Page<StoreDto> searchStore(StoreSearchDto searchDto, Pageable pageable);
}
