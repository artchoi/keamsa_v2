package keamsa.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import keamsa.InHelloApplication;
import keamsa.domain.Hellod;
import lombok.Data;

@Entity
@Table(name = "Hello_table")
@Data
public class Hello {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String hello;

    @PostPersist
    public void onPostPersist() {
        Hellod hellod = new Hellod(this);
        hellod.publishAfterCommit();
        // Get request from Hello
        //keamsa.external.Hello hello =
        //    Application.applicationContext.getBean(keamsa.external.HelloService.class)
        //    .getHello(/** mapping value needed */);

    }

    public static HelloRepository repository() {
        HelloRepository helloRepository = InHelloApplication.applicationContext.getBean(
            HelloRepository.class
        );
        return helloRepository;
    }
}
