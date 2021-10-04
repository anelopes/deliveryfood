package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InsertKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RegisterKitchen registerKitchen = applicationContext.getBean(RegisterKitchen.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Brasileira");

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setName("Japonesa");

        kitchen1 = registerKitchen.save(kitchen1);
        kitchen2 = registerKitchen.save(kitchen2);

        System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
        System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
    }
}
