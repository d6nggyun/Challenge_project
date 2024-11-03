package com.luckython_project.backend.dto.ChallengeDto;

import com.luckython_project.backend.entity.UserChallenge;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserChallengeResponseDto {
    private Long userId;
    private Long chId;

    public UserChallengeResponseDto(UserChallenge userChallenge) {
        this.userId = userChallenge.getUser().getUserId();
        this.chId = userChallenge.getChallenge().getChId();
    }
}