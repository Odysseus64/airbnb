package plasma.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plasma.airbnb.enums.DecisionStatus;
import plasma.airbnb.enums.Region;
import plasma.airbnb.enums.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private DecisionStatus decisionStatus;

    @Column(name = "town_province")
    private String townProvince;

    @Column(name = "max_of_guests")
    private int maxGuests;

    @Column(name = "price")
    private int price;

    @Column(name = "type")
    private Type type;

    @Column(name = "region")
    private Region region;

    private Long previewImageId;
    private int rating;
    private LocalDateTime date_now = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<FeedBack> feedBacks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Application application;
}