package com.laioffer.onlineorder.repository;

import com.laioffer.onlineorder.entity.RestaurantEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface RestaurantRepository extends ListCrudRepository<RestaurantEntity, Long> {
}
//no more API because Spring already wrote APIs