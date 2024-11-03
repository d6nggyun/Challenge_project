package com.luckython_project.backend.dto.ReviewDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewListDto {
    private Long userId;
    private String userName;
    private String review;

    @Builder
    public ReviewListDto(Long userId, String userName, String review) {
        this.userId = userId;
        this.userName = userName;
        this.review = review;
    }
}