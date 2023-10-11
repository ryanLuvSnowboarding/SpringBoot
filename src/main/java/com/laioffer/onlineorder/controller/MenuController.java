package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.MenuItemEntity;
import com.laioffer.onlineorder.model.RestaurantDto;
import com.laioffer.onlineorder.service.MenuItemService;
import com.laioffer.onlineorder.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {


    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    // There is no new argument in constructor function. Spring Boot help us do this. Spring Boot doing
    //this by setting up @sign annotation.
    public MenuController(RestaurantService restaurantService, MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }

    //The @sign below is actually make the code readable for other programmers
    //{restaurantId} must match @PathVariable("restaurantId"), otherwise cannot find the path
    @GetMapping("/restaurant/{restaurantId}/menu")
    public List<MenuItemEntity> getMenuByRestaurant(@PathVariable("restaurantId")  long restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }


    @GetMapping("/restaurants/menu")
    public List<RestaurantDto> getMenuForAllRestaurants() {
        return restaurantService.getRestaurants();
    }
}
