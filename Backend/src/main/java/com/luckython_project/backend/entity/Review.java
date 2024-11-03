package com.luckython_project.backend.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chId", nullable = false)
    private Challenge challenge;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    @Builder
    public Review(Long reviewId, User user, Challenge challenge, String review) {
        this.reviewId = reviewId;
        this.user = user;
        this.challenge = challenge;
        this.review = review;
    }
}