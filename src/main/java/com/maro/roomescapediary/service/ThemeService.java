package com.maro.roomescapediary.service;

import com.maro.roomescapediary.dto.ThemeDto;
import com.maro.roomescapediary.entity.BaseEntity;
import com.maro.roomescapediary.entity.Store;
import com.maro.roomescapediary.entity.Theme;
import com.maro.roomescapediary.repository.ThemeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final StoreService storeService;
    private final ThemeRepository themeRepository;

    public List<ThemeDto> searchThemes() {
        List<Theme> all = themeRepository.findAll();
        return all.stream().map(Theme::toDto).collect(Collectors.toList());
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
        theme.updateTheme(store, themeDto);
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
