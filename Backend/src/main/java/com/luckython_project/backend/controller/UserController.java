package com.luckython_project.backend.controller;

import com.luckython_project.backend.dto.UserDto.DetailUserDto;
import com.luckython_project.backend.dto.UserDto.UpdateUserDto;
import com.luckython_project.backend.entity.User;
import com.luckython_project.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 유저 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<DetailUserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok()
                .body(userService.getUser(userId));
    }

    // 유저 등록
    @PostMapping
    public ResponseEntity<DetailUserDto> createUser(@RequestBody User user){
        return ResponseEntity.ok()
                .body(userService.createUser(user));
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok()
                .body(null);
    }
}