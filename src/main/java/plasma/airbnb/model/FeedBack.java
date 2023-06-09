package plasma.airbnb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// Не используйте Builder его синтаксис не понятный и не оптими
@Builder
@Table(name = "feed_back")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feed_back")
    private String feedBack;
    private int like;
    private int dislike;
    private int rating;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "feedBack")
    private List<Image> images = new ArrayList<>();
}