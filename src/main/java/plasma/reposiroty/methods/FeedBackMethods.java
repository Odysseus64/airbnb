package plasma.reposiroty.methods;




import plasma.model.FeedBack;

import java.util.List;

public interface FeedBackMethods {
    void deleteById(Long id);
    void update(Long id, FeedBack feedBack);
   FeedBack findById(Long id);
    FeedBack save(FeedBack feedBack);
    List<FeedBack> findAll();
}