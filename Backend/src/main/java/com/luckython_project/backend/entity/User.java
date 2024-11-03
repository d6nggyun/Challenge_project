package com.luckython_project.backend.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 유저 ID

    @Column(nullable = false, unique = true, length = 50)
    private String username; // 유저 이름

    @Column(nullable = false)
    private Integer point; // 유저 포인트

    @Column(nullable = false)
    private Integer participation; // 유저의 챌린지 참여 횟수

    @Column(nullable = false)
    private Integer success; // 유저의 챌린지 성공 횟수

    @Builder
    public User(Long userId, String username, Integer point, Integer participation, Integer success) {
        this.userId = userId;
        this.username = username;
        this.point = point;
        this.participation = participation;
        this.success = success;
    }

    // 추가된 값을 더해 유저 필드 수정
    public void updateUser(Integer point) {
        this.point += point;
        this.participation += 1;
        this.success += 1;
    }
}
