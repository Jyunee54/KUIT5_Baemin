package com.kuit.baemin.dao;

import com.kuit.baemin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 회원가입
    public Long save(User user) {
        String sql = "INSERT INTO users (email, password, name, phone, created_at) VALUES (?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getName(), user.getPhone());

        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    // 이메일 중복 체크
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    // 이메일로 유저 찾기
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
    }

    // 회원 정보 조회 by userId
    public User findById(Long userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userId);
    }

    // 회원 정보 수정
    public void updateUser(Long userId, String name, String phone) {
        String sql = "UPDATE users SET name = ?, phone = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, name, phone, userId);
    }
}
