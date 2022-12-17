package com.maro.roomescapediary.repository;

import com.maro.roomescapediary.dto.ThemeDto;
import com.maro.roomescapediary.dto.ThemeSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeCustomRepository {

    Page<ThemeDto> searchTheme(ThemeSearchDto searchDto, Pageable pageable);
}
