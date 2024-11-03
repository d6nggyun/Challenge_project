package com.luckython_project.backend.controller;

import com.luckython_project.backend.dto.ReviewDto.ReviewDto;
import com.luckython_project.backend.dto.ReviewDto.ReviewListDto;
import com.luckython_project.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    // 1. 특정 챌린지에 대한 리뷰 생성하기
    @PostMapping("/{chId}/{userId}")
    public ResponseEntity<String> createReview(@PathVariable("chId") Long chId, @PathVariable("userId") Long userId, @RequestBody ReviewDto reviewDto) {
        reviewService.createReview(chId, userId, reviewDto);
        return ResponseEntity.status(201).body("Review created successfully for challenge ID: " + chId);
    }

    // 2. 특정 챌린지에 대한 리뷰 조회하기
    @GetMapping("/{chId}")
    public ResponseEntity<List<ReviewListDto>> getReviews(@PathVariable("chId") Long chId) {
        List<ReviewListDto> reviews = reviewService.getReviewsByChallengeId(chId);
        return ResponseEntity.ok(reviews);
    }
}