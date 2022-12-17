package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.ThemeDto;
import com.maro.roomescapediary.dto.ThemeSearchDto;
import com.maro.roomescapediary.entity.Store;
import com.maro.roomescapediary.entity.Theme;
import com.maro.roomescapediary.repository.ThemeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final StoreService storeService;
    private final ThemeRepository themeRepository;

    public Page<ThemeDto> searchThemes(ThemeSearchDto searchDto, Pageable pageable) {
        searchDto.checkAndSetValues();
        return themeRepository.searchTheme(searchDto, pageable);
    }

    public ThemeDto searchTheme(int themeSeq) {
        Theme theme = this.findById(themeSeq);
        return theme.toDto();
    }

    @Transactional
    public void addTheme(ThemeDto themeDto) {
        Store store = storeService.findById(themeDto.getStoreSeq());
        themeRepository.save(themeDto.toEntity(store));
    }

    @Transactional
    public void modifyTheme(ThemeDto themeDto) {
        Store store = storeService.findById(themeDto.getStoreSeq());
        Theme theme = this.findById(themeDto.getSeq());
        theme.updateTheme(themeDto, store);
    }

    @Transactional
    public void removeTheme(int themeSeq) {
        Theme theme = this.findById(themeSeq);
        theme.delete();
    }

    public Theme findById(int themeSeq) {
        return themeRepository.findById(themeSeq).orElseThrow(IllegalArgumentException::new);
    }
}
