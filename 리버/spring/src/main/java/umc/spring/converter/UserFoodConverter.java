package umc.spring.converter;

import umc.spring.domain.Food;
import umc.spring.domain.mapping.UserFood;

import java.util.List;
import java.util.stream.Collectors;

public class UserFoodConverter {

    public static List<UserFood> toUserFoodList(List<Food> foodList) {

        return foodList.stream()
                .map(food ->
                        UserFood.builder()
                                .food(food)
                                .build()
                ).collect(Collectors.toList());
    }
}
