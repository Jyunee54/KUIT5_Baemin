package com.kuit.baemin.dao;

import com.kuit.baemin.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RestaurantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 페이징 조회
    public List<Restaurant> findAllWithPaging(int offset, int size) {
        String sql = "SELECT * FROM restaurants ORDER BY created_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Restaurant.class), size, offset);
    }

    // 전체 레스토랑 수
    public int countRestaurants() {
        String sql = "SELECT COUNT(*) FROM restaurants";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 음식점 상세 조회
    public Restaurant findById(Long restaurantId) {
        String sql = "SELECT * FROM restaurants WHERE restaurant_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Restaurant.class), restaurantId);
    }
}

