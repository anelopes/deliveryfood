package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeleteKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository repository = applicationContext.getBean(KitchenRepository.class);

        repository.delete(1L);
    }
}
