package ru.didcvee.raspisanye.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.didcvee.raspisanye.RaspisanyeApplication;
import ru.didcvee.raspisanye.entity.Amogus;
import ru.didcvee.raspisanye.service.RaspService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = RaspisanyeApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CompanyServiceIT {
    @Autowired
    private RaspService raspService;
    private static final String GROUP_NAME = "IP1-22";
    private static final Date FROM = new Date(123,1,1);
    private static final Date TO = new Date(123,11,31);
    @Test
    void findBy(){

        List<Amogus> actualResult = raspService.getBy(GROUP_NAME, FROM, TO);
        List<Amogus> amogusList = actualResult.stream().map(amogus -> new Amogus(amogus.getS(), amogus.getVa(), amogus.getAs(), amogus.getItem(), new Date(1))).collect(Collectors.toList());

        Assertions.assertTrue(!actualResult.isEmpty());

        var expectedResult = List.of(new Amogus(1, "IP1-22","MONDAY","RUSSIAN_LANG",new Date(1)),
                new Amogus(2,"IP1-22","TUASDAY","VGVDSGW",new Date(1)));
        Assertions.assertEquals(expectedResult,amogusList);
    }
    public static String convert(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
