package com.maro.roomescapediary.store.service;

import com.maro.roomescapediary.store.dto.StoreDto;
import com.maro.roomescapediary.store.dto.StoreSearchDto;
import com.maro.roomescapediary.store.entity.Store;
import com.maro.roomescapediary.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Page<StoreDto> searchStores(StoreSearchDto searchDto, Pageable pageable) {
        searchDto.checkAndSetValues();
        return storeRepository.searchStore(searchDto, pageable);
    }

    public StoreDto searchStore(int storeSeq) {
        Store store = this.findById(storeSeq);
        return store.toDto();
    }

    @Transactional
    public void addStore(StoreDto storeDto) {
        storeRepository.save(storeDto.toEntity());
    }

    @Transactional
    public void modifyStore(StoreDto storeDto) {
        Store store = this.findById(storeDto.getSeq());
        store.updateStore(storeDto);
    }

    @Transactional
    public void removeStore(int storeSeq) {
        Store store = this.findById(storeSeq);
        store.delete();
    }

    public Store findById(int storeSeq) {
        return storeRepository.findById(storeSeq).orElseThrow(IllegalArgumentException::new);
    }
}
