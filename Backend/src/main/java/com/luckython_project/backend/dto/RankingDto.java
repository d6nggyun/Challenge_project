package com.luckython_project.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 랭킹 반환 DTO
public class RankingDto {
    private Long userId;
    private String username;
    private Integer point;
}
