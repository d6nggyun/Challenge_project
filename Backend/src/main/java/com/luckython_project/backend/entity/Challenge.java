package com.luckython_project.backend.entity;

import com.luckython_project.backend.dto.ChallengeDto.ChallengeDto;
import com.luckython_project.backend.dto.ChallengeDto.ImageDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    private List<UserChallenge> userChallenges;

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Column(length = 10)
    private String hashtag;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column(length = 50)
    private String chImg;

    @Column
    private Integer prize;

    @Builder
    public Challenge(Long chId, String title, String content, List<UserChallenge> userChallenges, List<Review> reviews, String hashtag, LocalDate startDate, LocalDate endDate, String chImg, Integer prize) {
        this.chId = chId;
        this.title = title;
        this.content = content;
        this.userChallenges = userChallenges;
        this.reviews = reviews;
        this.hashtag = hashtag;
        this.startDate = startDate;
        this.endDate = endDate;
        this.chImg = chImg;
        this.prize = prize;
    }


    //인증을 안햇네 관리자 같은거 ㅋㅋ
    public void updateChallenge(ChallengeDto challengeDto) {
        this.title = challengeDto.getTitle();
        this.content = challengeDto.getContent();
        this.hashtag = challengeDto.getHashtag();
        this.startDate = challengeDto.getStartDate();
        this.endDate = challengeDto.getEndDate();
        this.prize = challengeDto.getPrize();
    }

    public void updateImg(ImageDto imageDto){
        this.chImg = imageDto.getChImg();
    }

    public void addUserChallenge(UserChallenge userChallenge) {
        this.userChallenges.add(userChallenge);
    }
}