package com.maro.roomescapediary.image.service;

import com.maro.roomescapediary.image.dto.ImageDto;
import com.maro.roomescapediary.image.entity.Image;
import com.maro.roomescapediary.image.repository.ImageRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<ImageDto> searchImages() {
        List<Image> all = imageRepository.findAll();
        return all.stream().map(Image::toDto).collect(Collectors.toList());
    }

    public ImageDto searchImage(int imageSeq) {
        Image image = this.findById(imageSeq);
        return image.toDto();
    }

    @Transactional
    public void addImage(ImageDto imageDto) {
        imageRepository.save(imageDto.toEntity());
    }

    @Transactional
    public void modifyImage(ImageDto imageDto) {
        Image image = this.findById(imageDto.getSeq());
        image.updateImage(imageDto);
    }

    @Transactional
    public void removeImage(int imageSeq) {
        Image image = this.findById(imageSeq);
        image.delete();
    }

    public Image findById(int imageSeq) {
        return imageRepository.findById(imageSeq).orElseThrow(IllegalArgumentException::new);
    }
}
