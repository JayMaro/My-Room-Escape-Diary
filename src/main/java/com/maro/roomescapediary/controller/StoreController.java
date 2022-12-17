package com.maro.roomescapediary.controller;

import com.maro.roomescapediary.dto.StoreDto;
import com.maro.roomescapediary.dto.StoreSearchDto;
import com.maro.roomescapediary.service.StoreService;
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
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public Page<StoreDto> searchStores(StoreSearchDto searchDto, Pageable pageable) {
        return storeService.searchStores(searchDto, pageable);
    }

    @GetMapping("/{storeSeq}")
    public StoreDto searchStore(@PathVariable int storeSeq) {
        return storeService.searchStore(storeSeq);
    }

    @PostMapping
    public void addStore(@Valid @RequestBody StoreDto storeDto) {
        storeService.addStore(storeDto);
    }

    @PutMapping("/{storeSeq}")
    public void modifyStore(@PathVariable int storeSeq, @Valid @RequestBody StoreDto storeDto) {
        storeDto.setSeq(storeSeq);
        storeService.modifyStore(storeDto);
    }

    @DeleteMapping("/{storeSeq}")
    public void removeStore(@PathVariable int storeSeq) {
        storeService.removeStore(storeSeq);
    }

}
