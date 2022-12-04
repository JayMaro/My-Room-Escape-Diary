package com.maro.roomescapediary.controller;

import com.maro.roomescapediary.dto.StoreDto;
import com.maro.roomescapediary.service.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public List<StoreDto> searchStores() {
        return storeService.searchStores();
    }

    @GetMapping("/{storeSeq}")
    public StoreDto searchStore(@PathVariable int storeSeq) {
        return storeService.searchStore(storeSeq);
    }

    @PostMapping
    public void addStore(@RequestBody StoreDto storeDto) {
        storeService.addStore(storeDto);
    }

    @PutMapping("/{storeSeq}")
    public void modifyStore(@PathVariable int storeSeq, @RequestBody StoreDto storeDto) {
        storeDto.setSeq(storeSeq);
        storeService.modifyStore(storeDto);
    }

    @DeleteMapping("/{storeSeq}")
    public void removeStore(@PathVariable int storeSeq) {
        storeService.removeStore(storeSeq);
    }

}
