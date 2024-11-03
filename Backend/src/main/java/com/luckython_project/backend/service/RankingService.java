package com.luckython_project.backend.service;

import com.luckython_project.backend.config.exception.CustomException;
import com.luckython_project.backend.config.exception.ErrorCode;
import com.luckython_project.backend.dto.RankingDto;
import com.luckython_project.backend.entity.User;
import com.luckython_project.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final UserRepository userRepository;

    // 랭킹 조회
    public List<RankingDto> getRanking() {
        List<User> rankings = userRepository.findAll();

        if (rankings.isEmpty()) {
            throw new CustomException(ErrorCode.RANK_NOT_FOUND);
        }

        // 점수 내림차순 정렬 후 상위 10명 선택
        return rankings.stream()
                .sorted(Comparator.comparingInt(User::getPoint).reversed())
                .limit(10)
                .map(user -> new RankingDto(
                        user.getUserId(),
                        user.getUsername(),
                        user.getPoint()
                ))
                .collect(Collectors.toList());
    }
}