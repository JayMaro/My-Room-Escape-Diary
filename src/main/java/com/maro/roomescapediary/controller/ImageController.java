package com.maro.roomescapediary.controller;

import com.maro.roomescapediary.dto.ImageDto;
import com.maro.roomescapediary.service.ImageService;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<ImageDto> searchImages() {
        return imageService.searchImages();
    }

    @GetMapping("/{imageSeq}")
    public ImageDto searchImage(@PathVariable int imageSeq) {
        return imageService.searchImage(imageSeq);
    }

    @PostMapping
    public void addImage(@Valid @RequestBody ImageDto imageDto) {
        imageService.addImage(imageDto);
    }

    @PutMapping("/{imageSeq}")
    public void modifyImage(@PathVariable int imageSeq, @Valid @RequestBody ImageDto imageDto) {
        imageDto.setSeq(imageSeq);
        imageService.modifyImage(imageDto);
    }

    @DeleteMapping("/{imageSeq}")
    public void removeImage(@PathVariable int imageSeq) {
        imageService.removeImage(imageSeq);
    }

}
