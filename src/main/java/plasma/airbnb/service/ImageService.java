package plasma.airbnb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import plasma.airbnb.model.Image;
import plasma.airbnb.reposiroty.ImageRepository;
import plasma.airbnb.reposiroty.methods.ImageMethods;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService implements ImageMethods {
    public final ImageRepository service;
    @Override
    public void deleteById(Long id) {
        try{
            log.info("Deleting image with id: {}", id);
            service.deleteById(id);
        }catch (Exception exception){
            log.error("Error while deleting image: {}", exception.getMessage());
            throw new RuntimeException("Failed to delete image", exception);
        }
    }

    @Override
    public void update(Long id, Image image) {
        try{
            Image ored = service.findById(id).orElseThrow();
            ored.setPreviewImage(image.isPreviewImage());
            ored.setName(image.getName());
            ored.setBytes(image.getBytes());
            ored.setContentType(image.getContentType());
            ored.setOriginalFileName(image.getOriginalFileName());
            ored.setSize(image.getSize());
            service.save(ored);
            log.info("Image updated: {}", ored);
            log.info("Image finding with id: {}", id);
        }catch (Exception exception){
            log.error("Error while updating image: {}", exception.getMessage());
            throw new RuntimeException("Image not found with id" +  exception);
        }
    }

    @Override
    public Image findById(Long id) {
        try {
            log.info("Finding image with id: {}", id);
            return service.findById(id).orElseThrow(() ->
                    new RuntimeException("Image not found with id: {}" + id));
        }catch (Exception exception){
            log.error("Error while finding image: {}", exception.getMessage());
            throw new RuntimeException("Failed to find image", exception);
        }
    }

    @Override
    public Image save(Image image) {
        try{
            log.info("Saving image: {}", image);
            return service.save(image);
        }catch (Exception exception){
            log.error("Error while saving image: {}", exception.getMessage());
            throw new RuntimeException("Failed to save image", exception);
        }
    }

    @Override
    public List<Image> findAll() {
        return service.findAll();
    }
}
