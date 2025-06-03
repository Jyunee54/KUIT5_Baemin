package com.kuit.baemin.dao;

import com.kuit.baemin.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MenuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 해당 음식점의 메뉴 목록 조회
    public List<Menu> findMenusByRestaurantId(Long restaurantId) {
        String sql = "SELECT * FROM menus WHERE restaurant_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Menu.class), restaurantId);
    }
}

