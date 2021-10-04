package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.model.Kitchen;
import br.com.alopes.deliveryfood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAllKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository repository = applicationContext.getBean(KitchenRepository.class);

        List<Kitchen> kitchenList = repository.findAll();
        kitchenList.forEach(kitchen -> System.out.println(kitchen.getName()));
    }
}
