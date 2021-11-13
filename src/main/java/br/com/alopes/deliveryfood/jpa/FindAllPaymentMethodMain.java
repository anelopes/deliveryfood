package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.model.PaymentMethod;
import br.com.alopes.deliveryfood.domain.repository.PaymentMethodRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAllPaymentMethodMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentMethodRepository repository = applicationContext.getBean(PaymentMethodRepository.class);

        List<PaymentMethod> paymentMethodList = repository.findAll();
        paymentMethodList.forEach(paymentMethod -> System.out.println(paymentMethod.getDescription()));
    }
}
