package com.maro.roomescapediary.controller;

import com.maro.roomescapediary.dto.ThemeDto;
import com.maro.roomescapediary.dto.ThemeSearchDto;
import com.maro.roomescapediary.service.ThemeService;
import java.util.List;
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
@RequestMapping("/theme")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping
    public Page<ThemeDto> searchThemes(ThemeSearchDto searchDto, Pageable pageable) {
        return themeService.searchThemes(searchDto, pageable);
    }

    @GetMapping("/{themeSeq}")
    public ThemeDto searchTheme(@PathVariable int themeSeq) {
        return themeService.searchTheme(themeSeq);
    }

    @PostMapping
    public void addTheme(@RequestBody ThemeDto themeDto) {
        themeService.addTheme(themeDto);
    }

    @PutMapping("/{themeSeq}")
    public void modifyTheme(@PathVariable int themeSeq, @RequestBody ThemeDto themeDto) {
        themeDto.setSeq(themeSeq);
        themeService.modifyTheme(themeDto);
    }

    @DeleteMapping("/{themeSeq}")
    public void removeTheme(@PathVariable int themeSeq) {
        themeService.removeTheme(themeSeq);
    }
}
