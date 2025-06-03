package com.kuit.baemin.controller;

import com.kuit.baemin.dao.MenuDao;
import com.kuit.baemin.dao.RestaurantDao;
import com.kuit.baemin.entity.Menu;
import com.kuit.baemin.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantDao restaurantDao;
    private final MenuDao menuDao;

    @Autowired
    public RestaurantController(RestaurantDao restaurantDao, MenuDao menuDao) {
        this.restaurantDao = restaurantDao;
        this.menuDao = menuDao;
    }


    @GetMapping
    public ResponseEntity<?> getRestaurants(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        // 페이지는 1부터 시작, offset은 0부터 시작
        int offset = (page - 1) * size;

        List<Restaurant> restaurants = restaurantDao.findAllWithPaging(offset, size);
        int totalElements = restaurantDao.countRestaurants();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        // 응답 포맷
        Map<String, Object> response = new HashMap<>();
        response.put("content", restaurants);
        response.put("page", page);
        response.put("size", size);
        response.put("totalElements", totalElements);
        response.put("totalPages", totalPages);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getRestaurantDetail(@PathVariable Long restaurantId) {
        try {
            Restaurant restaurant = restaurantDao.findById(restaurantId);
            List<Menu> menus = menuDao.findMenusByRestaurantId(restaurantId);

            // 응답 구성
            Map<String, Object> response = new HashMap<>();
            response.put("restaurantId", restaurant.getRestaurantId());
            response.put("name", restaurant.getName());
            response.put("category", restaurant.getCategory());
            response.put("description", restaurant.getDescription());
            response.put("rating", restaurant.getRating());
            response.put("deliveryFee", restaurant.getDeliveryFee());
            response.put("menus", menus);

            return ResponseEntity.ok(response);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "음식점을 찾을 수 없습니다."));
        }
    }
}




