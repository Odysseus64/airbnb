package plasma.airbnb.model;

import lombok.*;
import plasma.airbnb.enums.DecisionStatus;
import javax.persistence.*;

/**
 * Created by mouflon on 05.07.2023.
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    // The user who sent the application for acceptance of the house/apartment
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
    private boolean accepted;
    private String message;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;
}