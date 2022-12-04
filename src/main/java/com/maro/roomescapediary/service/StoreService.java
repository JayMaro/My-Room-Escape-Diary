package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.StoreDto;
import com.maro.roomescapediary.entity.BaseEntity;
import com.maro.roomescapediary.entity.Store;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.maro.roomescapediary.repository.StoreRepository;
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
        Store store = storeRepository.findById(storeSeq).orElseThrow(RuntimeException::new);
        return store.toDto();
    }

    @Transactional
    public void addStore(StoreDto storeDto) {
        storeRepository.save(storeDto.toEntity());
    }

    @Transactional
    public void modifyStore(StoreDto storeDto) {
        storeRepository.findById(storeDto.getSeq()).ifPresent(
            store -> store.updateStore(storeDto)
        );
    }

    @Transactional
    public void removeStore(int storeSeq) {
        storeRepository.findById(storeSeq).ifPresent(BaseEntity::delete);
    }
}
