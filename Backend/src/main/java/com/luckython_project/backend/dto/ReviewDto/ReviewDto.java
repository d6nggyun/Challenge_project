package com.luckython_project.backend.dto.ReviewDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private String review;
    private Integer point;

    @Builder
    public ReviewDto(String review, Integer point) {
        this.review = review;
        this.point = point;
    }
}