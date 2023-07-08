package plasma.airbnb.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import plasma.airbnb.model.Application;

/**
 * Created by mouflon on 05.07.2023.
 */
@Repository
@Transactional
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}