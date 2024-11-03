package com.luckython_project.backend.dto.ChallengeDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChallengeUserListDto {
    private Long chId;
    private String title;
    private String content;
    private long dDay; // 변경된 필드

    @Builder
    public ChallengeUserListDto(Long chId, String title, String content, long dDay) { // 변경된 생성자
        this.chId = chId;
        this.title = title;
        this.content = content;
        this.dDay = dDay;
    }
}