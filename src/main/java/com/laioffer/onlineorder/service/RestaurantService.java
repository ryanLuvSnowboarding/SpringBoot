package com.laioffer.onlineorder.service;

import com.laioffer.onlineorder.entity.MenuItemEntity;
import com.laioffer.onlineorder.entity.RestaurantEntity;
import com.laioffer.onlineorder.model.MenuItemDto;
import com.laioffer.onlineorder.model.RestaurantDto;
import com.laioffer.onlineorder.repository.MenuItemRepository;
import com.laioffer.onlineorder.repository.RestaurantRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//RestaurantService depends on RestaurantRepository and MenuItemRepository
@Service
public class RestaurantService {


    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;


    public RestaurantService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Cacheable("restaurants")
//if cacheable, the code below would be skipped
    public List<RestaurantDto> getRestaurants() {
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        List<MenuItemEntity> menuItemEntities = menuItemRepository.findAll();
        //Grouping base on Restaurant ID
        Map<Long, List<MenuItemDto>> groupedMenuItems = new HashMap<>();
        //Use two for loop to construct RestaurantDto and put all restaurantDto into list
        for (MenuItemEntity menuItemEntity : menuItemEntities) {
            List<MenuItemDto> group = groupedMenuItems.computeIfAbsent(menuItemEntity.restaurantId(), k -> new ArrayList<>());
            MenuItemDto menuItemDto = new MenuItemDto(menuItemEntity);
            group.add(menuItemDto);
        }

        List<RestaurantDto> results = new ArrayList<>();
        for (RestaurantEntity restaurantEntity : restaurantEntities) {
            RestaurantDto restaurantDto = new RestaurantDto(restaurantEntity, groupedMenuItems.get(restaurantEntity.id()));
            results.add(restaurantDto);
        }
        return results;
    }


}
