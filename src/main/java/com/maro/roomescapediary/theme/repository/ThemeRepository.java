package com.maro.roomescapediary.theme.repository;

import com.maro.roomescapediary.theme.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Integer>, ThemeCustomRepository {

}
