package plasma.airbnb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import plasma.airbnb.model.FeedBack;
import plasma.airbnb.reposiroty.FeedBackRepository;
import plasma.airbnb.reposiroty.methods.FeedBackMethods;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedBackService implements FeedBackMethods {

    private final FeedBackRepository feedBackRepository;

    @Override
    public FeedBack saveFeedback(FeedBack feedBack) {
        try {
            log.info("Saving feedback: {}", feedBack);
            return feedBackRepository.save(feedBack);
        } catch (Exception exception) {
            log.error("Error while saving feedback: {}", exception.getMessage());
            throw new RuntimeException("Failed to save feedback", exception);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            log.info("Deleting feedback with id: {}", id);
            feedBackRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error while deleting feedback: {}", exception.getMessage());
            throw new RuntimeException("Failed to delete feedback" + exception);
        }
    }

    @Override
    public void update(Long id, FeedBack feedBack) {
        try {
            FeedBack feedBack1 = feedBackRepository.findById(id).orElseThrow();
            feedBack1.setImages(feedBack.getImages());
            feedBack1.setProduct(feedBack.getProduct());
            feedBack1.setFeedBack(feedBack.getFeedBack());
            feedBackRepository.save(feedBack1);
            log.info("FeedBack updated: {}", feedBack1);
            log.info("Feedback finding with id: {}", id);
        } catch (Exception exception) {
            log.error("Error while updating feedback: {}", exception.getMessage());
            throw new RuntimeException("FeedBack not found with id: {}" + exception);
        }
    }

    @Override
    public FeedBack findById(Long id) {
        try {
            return feedBackRepository.findById(id).orElseThrow(() -> new RuntimeException("Finding FeedBack with id: " + id));
        } catch (Exception exception) {
            log.error("Error while finding feedback: {}", exception.getMessage());
            throw new RuntimeException("Failed to find feedback", exception);
        }
    }

    @Override
    public FeedBack likeFeedBack(Long id) {
        FeedBack feedback = feedBackRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found FeedBack"));
        feedback.setLikeCount(feedback.getLikeCount() + 1);
        return feedBackRepository.save(feedback);
    }

    @Override
    public FeedBack dislikeFeedBack(Long id) {
        FeedBack feedback = feedBackRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found FeedBack"));
        feedback.setDislike(feedback.getDislike() + 1);
        return feedBackRepository.save(feedback);
    }

    @Override
    public double calculateAverageRating(List<FeedBack> feedbackList) {
        if (feedbackList.size() == 0)
            return 0.0;
        int sum = feedbackList.stream()
                .mapToInt(feedback -> feedback.getRating())
                .sum();
        return (double) sum / feedbackList.size();
    }

    @Override
    public List<FeedBack> findAll() {
        return feedBackRepository.findAll();
    }
}
