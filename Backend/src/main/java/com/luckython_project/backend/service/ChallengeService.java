package com.luckython_project.backend.service;

import com.luckython_project.backend.config.exception.CustomException;
import com.luckython_project.backend.config.exception.ErrorCode;
import com.luckython_project.backend.dto.ChallengeDto.*;
import com.luckython_project.backend.dto.ReviewDto.ReviewListDto;
import com.luckython_project.backend.entity.Challenge;
import com.luckython_project.backend.entity.Review;
import com.luckython_project.backend.entity.User;
import com.luckython_project.backend.entity.UserChallenge;
import com.luckython_project.backend.repository.ChallengeRepository;
import com.luckython_project.backend.repository.UserChallengeRepository;
import com.luckython_project.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final ReviewService reviewService;

    // 1. 챌린지 생성하기
    @Transactional
    public void createChallenge(ChallengeDto challengeDto) {
        Challenge challenge = Challenge.builder()
                .title(challengeDto.getTitle())
                .content(challengeDto.getContent())
                .hashtag(challengeDto.getHashtag())
                .startDate(challengeDto.getStartDate())
                .endDate(challengeDto.getEndDate())
                .prize(challengeDto.getPrize())
                .build();
        challengeRepository.save(challenge);
    }

    // 2. userId 아이디 별 챌린지 조회하기
    @Transactional(readOnly = true)
    public List<ChallengeUserListDto> getUserChallenge(Long userId) {
        return userChallengeRepository.findAllByUser_UserId(userId)
                .stream()
                .map(userChallenge -> {
                    Challenge challenge = userChallenge.getChallenge();
                    long dDay = ChronoUnit.DAYS.between(challenge.getEndDate(), LocalDate.now());
                    return ChallengeUserListDto.builder()
                            .chId(challenge.getChId())
                            .title(challenge.getTitle())
                            .content(challenge.getContent())
                            .dDay(dDay)
                            .build();
                })
                .collect(Collectors.toList());
    }

    // 2. chId 아이디 별 챌린지 조회하기
    public ChallengeDetailDto getChallenge(Long chId) {
        Challenge challenge = challengeRepository.findById(chId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND));

        List<ReviewListDto> reviewList = reviewService.getReviewsByChallengeId(chId);

        return ChallengeDetailDto.builder()
                .title(challenge.getTitle())
                .content(challenge.getContent())
                .hashtag(challenge.getHashtag())
                .endDate(challenge.getEndDate())
                .prize(challenge.getPrize())
                .review(reviewList)
                .build();
    }

    // 3. 해시태그별 챌린지 목록 조회하기
    @Transactional(readOnly = true)
    public List<ChallengeHashListDto> getChallengesByHashtag(String hashtag) {
        return challengeRepository.findAllByHashtag(hashtag)
                .stream()
                .map(challenge -> {
                    Integer participants = userChallengeRepository.countByChallenge(challenge);
                    return ChallengeHashListDto.builder()
                            .chId(challenge.getChId())
                            .title(challenge.getTitle())
                            .content(challenge.getContent())
                            .startDate(challenge.getStartDate())
                            .endDate(challenge.getEndDate())
                            .participants(participants)  // 참여자 수 설정
                            .build();
                })
                .collect(Collectors.toList());
    }

    // 4. 챌린지 수정하기
    @Transactional
    public void updateChallenge(Long chId, ChallengeDto challengeDto) {
        Challenge challenge = challengeRepository.findById(chId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND));
        challenge.updateChallenge(challengeDto);
        challengeRepository.save(challenge);
    }

    // 5. 챌린지 삭제하기
    @Transactional
    public void deleteChallenge(Long chId) {
        Challenge challenge = challengeRepository.findById(chId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid challenge ID: " + chId));
        challengeRepository.delete(challenge);
    }

    // 6. 이미지 인증하기
    @Transactional
    public UserChallengeDto verifyImage(Long chId, Long userId, ImageDto imageDto) {
        Challenge challenge = challengeRepository.findById(chId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid challenge ID: " + chId));
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        challenge.updateImg(imageDto);

        UserChallenge userChallenge = UserChallenge.builder()
                .user(user)
                .challenge(challenge)
                .review(null)
                .isParticipated(true)
                .isCertified(true)
                .isReviewed(false)
                .build();

        return UserChallengeDto.of(userChallenge);
    }

    // 7. 챌린지 참여하기
    @Transactional
    public UserChallengeDto participateInChallenge(Long chId, Long userId) {
        Challenge challenge = challengeRepository.findById(chId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid challenge ID: " + chId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

        UserChallenge userChallenge = UserChallenge.builder()
                .user(user)
                .challenge(challenge)
                .review(null)
                .isParticipated(true)
                .isCertified(false)
                .isReviewed(false)
                .build();

        userChallengeRepository.save(userChallenge);
        challenge.addUserChallenge(userChallenge);

        return UserChallengeDto.of(userChallenge);
    }

    // 8. 모든 챌린지 조회하기
    public List<ChallengeHashListDto> getAllChallenges() {
        return challengeRepository.findAll()
                .stream()
                .map(challenge -> {
                    Integer participants = userChallengeRepository.countByChallenge(challenge);
                    return ChallengeHashListDto.builder()
                            .chId(challenge.getChId())
                            .title(challenge.getTitle())
                            .content(challenge.getContent())
                            .startDate(challenge.getStartDate())
                            .endDate(challenge.getEndDate())
                            .participants(participants)  // 참여자 수 설정
                            .build();
                })
                .collect(Collectors.toList());
    }
}