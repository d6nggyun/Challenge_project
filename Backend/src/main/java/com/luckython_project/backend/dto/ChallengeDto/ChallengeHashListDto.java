package com.luckython_project.backend.dto.ChallengeDto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChallengeHashListDto {
    private Long chId;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer participants;

    @Builder
    public ChallengeHashListDto(Long chId, String title, String content, LocalDate startDate, LocalDate endDate, Integer participants) {
        this.chId = chId;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
    }
}