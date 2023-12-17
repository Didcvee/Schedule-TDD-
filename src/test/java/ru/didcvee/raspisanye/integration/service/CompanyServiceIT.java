package ru.didcvee.raspisanye.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import ru.didcvee.raspisanye.entity.Amogus;
import ru.didcvee.raspisanye.integration.annotation.IT;
import ru.didcvee.raspisanye.service.RaspService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // Проставил это в spring.properties
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = RaspisanyeApplication.class, initializers = ConfigDataApplicationContextInitializer.class)

public class CompanyServiceIT extends IntegrationTestBase {

    private final RaspService raspService;
    private static final String GROUP_NAME = "IP1-22";
    private static final Date FROM = new Date(123,1,1);
    private static final Date TO = new Date(123,11,31);

    public CompanyServiceIT(RaspService raspService) {
        this.raspService = raspService;
    }

    @Test
    void findBy(){

        List<Amogus> actualResult = raspService.getBy(GROUP_NAME, FROM, TO);
        List<Amogus> amogusList = actualResult.stream().map(amogus -> new Amogus(amogus.getS(), amogus.getVa(), amogus.getAs(), amogus.getItem(), new Date(1))).collect(Collectors.toList());

        Assertions.assertTrue(!actualResult.isEmpty());

        var expectedResult = List.of(new Amogus(2,"IP1-22","TUASDAY","VGVDSGW",new Date(1)), new Amogus(1, "IP1-22","MONDAY","RUSSIAN_LANG",new Date(1)));
        Assertions.assertEquals(expectedResult,amogusList);
    }

}
