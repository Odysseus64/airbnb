package plasma.airbnb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import plasma.airbnb.dto.response.ProductResponse;
import plasma.airbnb.enums.Region;
import plasma.airbnb.enums.Type;
import plasma.airbnb.model.Product;
import plasma.airbnb.reposiroty.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static plasma.airbnb.enums.Type.APARTMENT;

@Service
@Slf4j
@RequiredArgsConstructor
public class SortedService {
    private final ProductRepository repository;

    public List<Product> sortByName(String title) {
        try {
            if (title != null) {
                log.info("Sort by name: {}", title);
                repository.findByTitle(title);
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return repository.findAll();
    }

    public List<Product> sortByPrice(int price) {
        try {
            if (price != 0) {
                log.info("Sort by price: {}", price);
                repository.ratingPrice();
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return repository.findAll();
    }

    public List<Product> sortByRatingType(Region region) {
        try {
            if (region != null) {
                log.info("Info: {}", region);
                repository.findByRegion(region);
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return repository.findAll();
    }

    public List<Product> sortByRatingType(Type type) {
        try {
            if (type == null) {
                log.info("Info: {}", type);
                repository.ratingType();
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return repository.findAll();
    }

    public List<Product> search(String title, String city, Region region){
        try{
            if (title != null || city != null || region != null) {
                log.info("Searching with: {}", title);
                log.info("Searching with: {}", city);
                log.info("Searching with: {}", region);
                repository.search(title, city, region);
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return repository.findAll();
    }

    public List<Product> regions (Region region){
        try{
            if (region != null) {
                log.info("Get region: {}", region);
                repository.region(region);
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return repository.findAll();
    }

    public List<Product> types (Type type){
        try {
            if (type != null) {
                log.info("Get type: {}", type);
                repository.sortByType(type);
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return repository.findAll();
    }
    // Даниел код длинный и не понятный напиши лучше
    public List<ProductResponse> ratingThree() {
        List<ProductResponse> productResponses = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 3);
        for (Product product : repository.sortRatingAndThree(pageable)) {
            if (product.getType() == Type.HOUSE) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setTitle(product.getTitle());
                productResponse.setRating(product.getRating());
                productResponse.setPrice(product.getPrice());
                try {
                    productResponse.setDate_now(product.getDate_now());
                    productResponse.setImages(product.getImages());
                } catch (Exception e) {
                    productResponse.setImages(null);
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                try {
                    productResponse.setRegion(product.getRegion());
                } catch (Exception e) {
                    productResponse.setRegion(null);
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                productResponses.add(productResponse);
            }
        }
        return productResponses;
    }

    public List<ProductResponse> ratingSort() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : repository.sortRating()) {
            if (product.getType() == Type.HOUSE) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setTitle(product.getTitle());
                productResponse.setRating(product.getRating());
                productResponse.setPrice(product.getPrice());
                try {
                    productResponse.setDate_now(product.getDate_now());
                    productResponse.setImages(product.getImages());
                } catch (Exception e) {
                    productResponse.setImages(null);
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                try {
                    productResponse.setRegion(product.getRegion());
                } catch (Exception e) {
                    productResponse.setRegion(null);
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                productResponses.add(productResponse);
            }
        }
        return productResponses;
    }

    public List<ProductResponse> sortForDate() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : repository.sortDateLast()) {
            if (product.getType() == Type.HOUSE) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setTitle(product.getTitle());
                productResponse.setRating(product.getRating());
                productResponse.setPrice(product.getPrice());
                try {
                    productResponse.setDate_now(product.getDate_now());
                    productResponse.setImages(product.getImages());
                } catch (Exception e) {
                    productResponse.setImages(null);
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                try {
                    productResponse.setRegion(product.getRegion());
                } catch (Exception e) {
                    productResponse.setRegion(null);
                    log.error(e.getMessage());
                    throw new RuntimeException();
                }
                productResponseList.add(productResponse);
            }
        }
        return productResponseList;
    }

    public List<ProductResponse> ratingSortApartments() {
        return repository.sortRating().stream()
                .filter(product -> product.getType() == APARTMENT)
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setTitle(product.getTitle());
                    productResponse.setRating(product.getRating());
                    productResponse.setPrice(product.getPrice());
                    try {
                        productResponse.setDate_now(product.getDate_now());
                    } catch (Exception e) {
                        log.error("Error setting date: " + e.getMessage());
                        productResponse.setDate_now(null);
                    }

                    try {
                        productResponse.setImages(product.getImages());
                    } catch (Exception e) {
                        log.error("Error setting images: " + e.getMessage());
                        productResponse.setImages(null);
                    }

                    try {
                        productResponse.setRegion(product.getRegion());
                    } catch (Exception e) {
                        log.error("Error setting region: " + e.getMessage());
                        productResponse.setRegion(null);
                    }
                    return productResponse;
                })
                .collect(Collectors.toList());
    }
}