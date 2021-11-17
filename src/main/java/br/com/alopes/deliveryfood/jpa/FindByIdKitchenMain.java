package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.model.Kitchen;
import br.com.alopes.deliveryfood.domain.service.RegisterKitchenService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class FindByIdKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RegisterKitchenService service = applicationContext.getBean(RegisterKitchenService.class);

        Kitchen kitchen = service.findById(1L);
        System.out.println(kitchen.getName());
    }
}
