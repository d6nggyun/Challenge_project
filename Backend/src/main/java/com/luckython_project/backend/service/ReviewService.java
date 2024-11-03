package com.luckython_project.backend.service;

import com.luckython_project.backend.config.exception.CustomException;
import com.luckython_project.backend.config.exception.ErrorCode;
import com.luckython_project.backend.dto.ChallengeDto.UserChallengeDto;
import com.luckython_project.backend.dto.ReviewDto.ReviewDto;
import com.luckython_project.backend.dto.ReviewDto.ReviewListDto;
import com.luckython_project.backend.entity.Challenge;
import com.luckython_project.backend.entity.Review;
import com.luckython_project.backend.entity.User;
import com.luckython_project.backend.entity.UserChallenge;
import com.luckython_project.backend.repository.ChallengeRepository;
import com.luckython_project.backend.repository.ReviewRepository;
import com.luckython_project.backend.repository.UserChallengeRepository;
import com.luckython_project.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final UserChallengeRepository userChallengeRepository;

    // 1. 특정 챌린지에 대한 리뷰 생성하기
    @Transactional
    public UserChallengeDto createReview(Long chId, Long userId, ReviewDto reviewDto) {
        Challenge challenge = challengeRepository.findById(chId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid challenge ID: " + chId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Review review = Review.builder()
                .user(user)
                .challenge(challenge)
                .review(reviewDto.getReview())
                .build();
        reviewRepository.save(review);

        // 리뷰 작성 여부 설정
        UserChallenge userChallenge = UserChallenge.builder()
                .user(user)
                .challenge(challenge)
                .review(review)
                .isParticipated(true)
                .isCertified(true)
                .isReviewed(true)
                .build();

        userChallengeRepository.save(userChallenge);

        // 리뷰 등록 시에 해당 포인트 추가
        user.updateUser(reviewDto.getPoint());
        userRepository.save(user);

        return UserChallengeDto.of(userChallenge);
    }

    // 2. 특정 챌린지에 대한 리뷰 조회하기
    @Transactional(readOnly = true)
    public List<ReviewListDto> getReviewsByChallengeId(Long chId) {
        return reviewRepository.findByChallenge_ChId(chId)
                .stream()
                .map(review -> ReviewListDto.builder()
                        .userId(review.getUser().getUserId())
                        .userName(review.getUser().getUsername())
                        .review(review.getReview())
                        .build())
                .toList();
    }
}