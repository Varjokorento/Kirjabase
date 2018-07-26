package fi.academy.helloworld;

import fi.academy.helloworld.data.KirjaEntity;
import fi.academy.helloworld.data.KirjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloworldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }

    @Bean
    public CommandLineRunner alusta(@Autowired KirjaRepository kp) {
        return args -> {
            kp.save(new KirjaEntity("Joku kirja", "Joku kirjoittaja", 2001));
            kp.save(new KirjaEntity("Joku toinen kirja", "Joku toinen kirjoittaja", 2002));
            kp.save(new KirjaEntity("Joku kolmas kirja", "Joku kolmas kirjoittaja", 2003));
        };

    }
}
