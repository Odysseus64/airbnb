package plasma.airbnb.reposiroty;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import plasma.airbnb.enums.Region;
import plasma.airbnb.enums.Type;
import plasma.airbnb.model.Product;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select s from Product s where s.title = :title ")
    List<Product> findByTitle(@Param("title") String title);

    @Query("select s from Product  s where s.price = :priceo")
    List<Product> findByPrice(@Param("price") int price);

    @Query("select s from Product s where s.region = :region")
    List<Product> findByRegion(@Param("region") Region region);

    @Query("select s from Product s order by s.title desc ")
    List<Product> ratingTitle();

    @Query("select s from Product s order by s.price desc ")
    List<Product> ratingPrice();

    @Query("select s from Product s order by s.region desc ")
    List<Product> ratingRegion();

    @Query("select s from Product s order by s.type desc ")
    List<Product> ratingType();

    @Query("select  s from  Product  s order by s.title desc")
    List<Product> search(@RequestParam("name") String title,
                         @RequestParam("city") String city,
                         @RequestParam("region") Region region);

    List<Region> region(@RequestParam("region") Region region);

    @Query("select s from Product s order by s.type desc")
    List<Product> sortByType(@RequestParam("type") Type type);

    @Query("SELECT s FROM Product s order by s.rating desc")
    List<Product> sortRatingAndThree(Pageable pageable);

    @Query("select s from Product s order by s.rating desc")
    List<Product> sortRating();

    @Query("select s from Product s order by s.date_now")
    List<Product> sortDateLast();
}