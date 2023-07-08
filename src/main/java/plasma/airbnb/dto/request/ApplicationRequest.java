package plasma.airbnb.dto.request;

import lombok.Data;
import plasma.airbnb.model.Product;
import plasma.airbnb.model.User;

/**
 * Created by mouflon on 05.07.2023.
 */
@Data
public class ApplicationRequest {

    private User user;
    private Product product;
}