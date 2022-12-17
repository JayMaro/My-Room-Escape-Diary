package com.maro.roomescapediary.image.repository;

import com.maro.roomescapediary.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
