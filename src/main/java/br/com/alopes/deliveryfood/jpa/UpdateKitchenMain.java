package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class UpdateKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RegisterKitchen registerKitchen = applicationContext.getBean(RegisterKitchen.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setId(1L);
        kitchen1.setName("Brasileira");

        registerKitchen.save(kitchen1);
    }
}
