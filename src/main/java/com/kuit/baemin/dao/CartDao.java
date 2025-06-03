package com.kuit.baemin.dao;

import com.kuit.baemin.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 장바구니 담기
    public Long addCartItem(CartItem cartItem) {
        String sql = "INSERT INTO cart_items (user_id, restaurant_id, menu_id, quantity, created_at) VALUES (?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql, cartItem.getUserId(), cartItem.getRestaurantId(), cartItem.getMenuId(), cartItem.getQuantity());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    // 장바구니 조회
    public List<CartItem> findCartByUserId(Long userId) {
        String sql = "SELECT * FROM cart_items WHERE user_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CartItem.class), userId);
    }

    // 장바구니 항목 삭제
    public void deleteCartItem(Long cartItemId) {
        String sql = "DELETE FROM cart_items WHERE cart_item_id = ?";
        jdbcTemplate.update(sql, cartItemId);
    }

    // 장바구니 비우기
    public void clearCart(Long userId) {
        String sql = "DELETE FROM cart_items WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }
}

