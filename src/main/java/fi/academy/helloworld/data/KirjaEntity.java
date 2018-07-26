package fi.academy.helloworld.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
@Entity
public class KirjaEntity implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String nimi;
    @Column
    private String kirjoittaja;
    @Column
    private int julkaisuvuosi;

    public KirjaEntity(String nimi, String kirjoittaja, int julkaisuvuosi){
        this.nimi = nimi;
        this.kirjoittaja  = kirjoittaja;
        this.julkaisuvuosi = julkaisuvuosi;

    }

    public KirjaEntity(){}


    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    public int getJulkaisuvuosi() {
        return julkaisuvuosi;
    }

    public void setJulkaisuvuosi(int julkaisuvuosi) {
        this.julkaisuvuosi = julkaisuvuosi;
    }
}
