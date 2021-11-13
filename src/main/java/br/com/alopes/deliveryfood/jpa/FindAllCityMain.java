package br.com.alopes.deliveryfood.jpa;

import br.com.alopes.deliveryfood.DeliveryFoodApiApplication;
import br.com.alopes.deliveryfood.domain.model.City;
import br.com.alopes.deliveryfood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAllCityMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository repository = applicationContext.getBean(CityRepository.class);

        List<City> cityList = repository.findAll();
        cityList.forEach(city -> System.out.printf("%s - %s\n", city.getName(), city.getState().getName()));
    }
}
