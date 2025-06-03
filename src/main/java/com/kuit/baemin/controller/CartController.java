package com.kuit.baemin.controller;

import com.kuit.baemin.dao.CartDao;
import com.kuit.baemin.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartDao cartDao;

    @Autowired
    public CartController(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    // 장바구니 담기
    @PostMapping
    public ResponseEntity<?> addCartItem(@RequestBody CartItem cartItem) { //Todo 수정할 것 dto 생성해야 할 듯
        Long cartItemId = cartDao.addCartItem(cartItem);

        return ResponseEntity.ok(Map.of(
                "cartItemId", cartItemId,
                "message", "장바구니에 추가되었습니다."
        ));
    }

    // 장바구니 조회
    @GetMapping
    public ResponseEntity<?> getCart(@RequestParam Long userId) {
        // (지금은 JWT 없이 userId Param 으로 받음 → 나중에 JWT 사용 시 토큰에서 userId 추출)
        List<CartItem> cartItems = cartDao.findCartByUserId(userId);

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "cartItems", cartItems
        ));
    }

    // 장바구니 항목 삭제
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long cartItemId) {
        cartDao.deleteCartItem(cartItemId);

        return ResponseEntity.ok(Map.of(
                "message", "장바구니 항목이 삭제되었습니다."
        ));
    }

    // 장바구니 비우기
    @DeleteMapping
    public ResponseEntity<?> clearCart(@RequestParam Long userId) {
        cartDao.clearCart(userId);

        return ResponseEntity.ok(Map.of(
                "message", "장바구니가 비워졌습니다."
        ));
    }
}
