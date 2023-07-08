package plasma;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import plasma.airbnb.enums.Role;
import plasma.airbnb.model.User;
import plasma.airbnb.reposiroty.UserRepository;
import javax.annotation.PostConstruct;

@RestController
@SpringBootApplication
@RequiredArgsConstructor
public class AirbnbApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AirbnbApplication.class, args);
    }

    @PostConstruct
    public void init() {
        User admin = new User();
        admin.setName("Marlen");
        admin.setEmail("mouflon@gmail.com");
        admin.setPassword(passwordEncoder.encode("mario"));
        admin.setRole(Role.ADMIN);

        User user = new User();
        user.setName("Somebody");
        user.setEmail("user@gmail.com");
        user.setPassword(passwordEncoder.encode("userO"));
        user.setRole(Role.USER);

        userRepository.save(admin);
        userRepository.save(user);
    }
}