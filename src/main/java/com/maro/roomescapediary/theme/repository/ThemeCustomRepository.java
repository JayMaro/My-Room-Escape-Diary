package com.maro.roomescapediary.theme.repository;

import com.maro.roomescapediary.theme.dto.ThemeDto;
import com.maro.roomescapediary.theme.dto.ThemeSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeCustomRepository {

    Page<ThemeDto> searchTheme(ThemeSearchDto searchDto, Pageable pageable);
}
