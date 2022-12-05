package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.StoreDto;
import com.maro.roomescapediary.entity.Store;
import com.maro.roomescapediary.repository.StoreRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreDto> searchStores() {
        List<Store> all = storeRepository.findAll();
        return all.stream().map(Store::toDto).collect(Collectors.toList());
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
        return storeRepository.findById(storeSeq).orElseThrow(RuntimeException::new);
    }
}
