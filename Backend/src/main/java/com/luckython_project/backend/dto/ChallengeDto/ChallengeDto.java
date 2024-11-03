package com.luckython_project.backend.dto.ChallengeDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChallengeDto {
    private String title;
    private String content;
    private String hashtag;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer prize;

    @Builder
    public ChallengeDto(String title, String content, Long userId, String hashtag, LocalDate startDate, LocalDate endDate, Integer prize) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prize = prize;
    }
}