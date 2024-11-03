package com.luckython_project.backend.dto.UserDto;

import com.luckython_project.backend.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
// 유저 정보 반환 DTO
public class DetailUserDto {
    private Long userId;
    private String username;
    private Integer point;
    private Integer participation;
    private Integer success;

    @Builder
    public DetailUserDto(Long userId, String username, Integer point, Integer participation, Integer success) {
        this.userId = userId;
        this.username = username;
        this.point = point;
        this.participation = participation;
        this.success = success;
    }

    // User 엔티티를 DTO로 변환
    public static DetailUserDto of(User user) {
        return DetailUserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .point(user.getPoint())
                .participation(user.getParticipation())
                .success(user.getSuccess())
                .build();
    }
}
