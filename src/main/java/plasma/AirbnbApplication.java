package plasma;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import plasma.airbnb.enums.Region;
import plasma.airbnb.enums.Type;
import plasma.airbnb.model.Product;
import plasma.airbnb.reposiroty.ProductRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class AirbnbApplication {
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(AirbnbApplication.class, args);
    }

    @PostConstruct
    public void init (){
        Product product1= new Product();
        product1.setTitle("omat s");
        product1.setRating(5);
        product1.setType(Type.APARTMENT);
        product1.setRegion(Region.BATKEN);
        product1.setDescription("Стройка много чего другого и хуйня полное");
        Product product2 = new Product();
        product2.setTitle("ITKG");
        product2.setRating(2);
        product1.setRegion(Region.OSH);
        product2.setType(Type.APARTMENT);
        Product product3= new Product();
        product3.setTitle("stroy");
        product3.setRating(5);
        product3.setType(Type.APARTMENT);
        product3.setDescription("This is a product which 3 description");
        product1.setRegion(Region.BISHKEK);
        product3.setType(Type.HOUSE);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
}
