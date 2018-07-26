package fi.academy.helloworld;

import fi.academy.helloworld.data.KirjaEntity;
import fi.academy.helloworld.data.KirjaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)

public class HelloWorldApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    KirjaRepository kirjaRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static String baseurl = "/kirjat";

    @Test
    public void testaaKirjaListaEiOleNull() {
        ResponseEntity<List<KirjaEntity>> response =
        restTemplate.exchange(baseurl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<KirjaEntity>>() {}
        );
        //assertThat(200, response.getStatusCodeValue());
        assertThat(response.getStatusCodeValue(), is(200));
        List<KirjaEntity> kirjat = response.getBody();
        assertThat(kirjat, is(notNullValue()));
    }

    @Test
    public void testaaKirjanVoiHakeaIdlla() {
        Iterable<KirjaEntity> kijrat = kirjaRepository.findAll();
        KirjaEntity vertailuKirja = kijrat.iterator().next();

        ResponseEntity<KirjaEntity> kirjaResponseEntity = restTemplate.getForEntity(
                baseurl+"/{id}", KirjaEntity.class, vertailuKirja.getId());
        assertEquals(200, kirjaResponseEntity.getStatusCodeValue());
        KirjaEntity kirja = kirjaResponseEntity.getBody();
        assertThat(kirja, is(notNullValue()));
        assertThat(kirja.getId(), is(equalTo(vertailuKirja.getId())));
        assertThat(kirja.getNimi(), is(equalTo(vertailuKirja.getNimi())));
    }

    @Test
    public void testaaOlematonHenkiloTuottaaNotFoundTuloksen() {
        ResponseEntity<KirjaEntity> kirjaVastaus = restTemplate
                .getForEntity(baseurl+"/{id}", KirjaEntity.class, 100);
        assertEquals(HttpStatus.NOT_FOUND, kirjaVastaus.getStatusCode());
        int suurinId = jdbcTemplate.queryForList("select max(id) from kirjat", Integer.class).get(0);
        kirjaVastaus = restTemplate.getForEntity(baseurl+"/{id}", KirjaEntity.class, ++suurinId);
        assertEquals(HttpStatus.NOT_FOUND, kirjaVastaus);
    }

}
