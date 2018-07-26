package fi.academy.helloworld.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KirjaRepository extends CrudRepository<KirjaEntity, Integer> {
    List<KirjaEntity> findByJulkaisuvuosi(int julkaisuvuosi);
    List<KirjaEntity> findByKirjoittaja(String kirjoittaja);
    List<KirjaEntity> findByNimi(String nimi);
    @Override
    List<KirjaEntity> findAll();

}
