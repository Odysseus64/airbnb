package plasma.airbnb.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import plasma.airbnb.enums.Role;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = " _user")
@Data
@Slf4j
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_sequence")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String description;
    private String phoneNumber;
    private boolean active;

    @ElementCollection
    private List<String> favorites;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    private LocalDateTime dateOfCreate;

    // Нужно будет указать на схеме, что я подключил (User to FeedBack)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<FeedBack> feedBacks = new ArrayList<>();
    /*
    To send a "supposedly" applications by the user to accept a house/apartment
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    private List<Application> applications;

    public User(String name,
                String email,
                String password,
                Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email) {
        this.email = email;
    }

    public void saveToFavorites(String item) {
        favorites.add(item);
        log.info(item + " добавлен в избранное пользователя " + name + ".");
    }

    public void deleteToFavorites(String item){
        if(favorites.contains(item)){
            favorites.remove(item);
            log.info(item + " удален из избранного пользователя " + name + ".");
        }else {
            log.info("не найден в избранном пользователя " + name + ".");
        }

    }

    @PrePersist
    private void init() {
        dateOfCreate = LocalDateTime.now();
    }
}