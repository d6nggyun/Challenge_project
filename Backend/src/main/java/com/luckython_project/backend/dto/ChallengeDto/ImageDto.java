package com.luckython_project.backend.dto.ChallengeDto;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageDto {
    private String chImg;

    public ImageDto() {}

    @Builder
    public ImageDto(String chImg) {
        this.chImg = chImg;
    }
}