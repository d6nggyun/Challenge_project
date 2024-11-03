package com.luckython_project.backend.dto.ChallengeDto;


import com.luckython_project.backend.dto.ReviewDto.ReviewListDto;
import com.luckython_project.backend.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChallengeDetailDto {
    private String title;
    private String content;
    private String hashtag;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer prize;
    private List<ReviewListDto> review;

    @Builder
    public ChallengeDetailDto(String title, String content, Long userId, String hashtag, LocalDate startDate, LocalDate endDate, Integer prize, List<ReviewListDto> review) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prize = prize;
        this.review = review;
    }
}