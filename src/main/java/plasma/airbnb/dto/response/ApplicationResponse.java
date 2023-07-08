package plasma.airbnb.dto.response;

import lombok.Data;
import plasma.airbnb.enums.DecisionStatus;
import plasma.airbnb.model.Product;
import plasma.airbnb.model.User;

/**
 * Created by mouflon on 05.07.2023.
 */
@Data
public class ApplicationResponse {

    private Long applicationId;
//    private User user;
//    private Product product;
    private DecisionStatus decisionStatus;
    private boolean accepted;
    private String message;
}