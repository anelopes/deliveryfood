package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.model.Restaurant;
import br.com.alopes.deliveryfood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAllRestaurantMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository repository = applicationContext.getBean(RestaurantRepository.class);

        List<Restaurant> restaurantList = repository.findAll();
        restaurantList.forEach(restaurant -> System.out.printf("%s - %f - %s\n",
                restaurant.getName(), restaurant.getFreightRate(), restaurant.getKitchen().getName()));
    }
}
