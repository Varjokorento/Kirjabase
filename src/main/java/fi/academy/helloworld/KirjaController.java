package fi.academy.helloworld;

import fi.academy.helloworld.data.KirjaEntity;
import fi.academy.helloworld.data.KirjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/kirjat")
public class KirjaController {

    @Autowired
    KirjaRepository kirjaRepository;

    @GetMapping()
    public List<KirjaEntity> get() {
        return kirjaRepository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<KirjaEntity> findbyId(@PathVariable int id) {
        return kirjaRepository.findById(id);
    }

    @PostMapping
    public void createNew(@RequestBody KirjaEntity kirjaEntity) {
        kirjaRepository.save(kirjaEntity);

    }

    @PutMapping
    public void update(@RequestBody KirjaEntity kirjaEntity) {
        kirjaRepository.save(kirjaEntity);
    }

    @DeleteMapping("/{id}")
    public void deletebyId(@PathVariable int id) {
        kirjaRepository.deleteById(id);
    }

}
