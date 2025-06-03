package com.kuit.baemin.controller;

import com.kuit.baemin.dao.UserDao;
import com.kuit.baemin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    // 회원가입
    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userDao.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "이미 존재하는 이메일입니다."));
        }

        Long userId = userDao.save(user);

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "message", "회원가입이 완료되었습니다."
        ));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        try {
            User user = userDao.findByEmail(email);

            if (!user.getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "비밀번호가 일치하지 않습니다."));
            }

            return ResponseEntity.ok(Map.of(
                    "userId", user.getUserId(),
                    "message", "로그인 성공"
            ));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "존재하지 않는 이메일입니다."));
        }
    }

    // 회원 정보 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userId) {
        try {
            User user = userDao.findById(userId);

            // 비밀번호는 응답에 포함 X
            return ResponseEntity.ok(Map.of(
                    "userId", user.getUserId(),
                    "email", user.getEmail(),
                    "name", user.getName(),
                    "phone", user.getPhone()
            ));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "회원 정보를 찾을 수 없습니다."));
        }
    }

    // 회원 정보 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Map<String, String> updateRequest) {
        String name = updateRequest.get("name");
        String phone = updateRequest.get("phone");

        userDao.updateUser(userId, name, phone);

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "message", "회원 정보가 수정되었습니다."
        ));
    }

}
