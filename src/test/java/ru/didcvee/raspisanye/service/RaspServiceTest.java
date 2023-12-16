package ru.didcvee.raspisanye.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.didcvee.raspisanye.entity.Amogus;
import ru.didcvee.raspisanye.repo.RaspRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RaspServiceTest {
    private static final String GROUP_NAME = "IP1-22";
    private static final Date FROM = new Date(122,1,1);
    private static final Date TO = new Date(122,5,3);
    @Mock
    private RaspRepo repo;
    @InjectMocks
    private RaspService raspService;

    @Test
    void getBy() {
        Mockito.doReturn(List.of(new Amogus(1, "IP1-22","MONDAY","RUSSIAN LANG",new Date(122,2,2))))
                .when(repo)
                .getBy(GROUP_NAME,FROM,TO);

        List<Amogus> actualResult = raspService.getBy(GROUP_NAME, FROM, TO);

        Assertions.assertTrue(!actualResult.isEmpty());

        var expectedResult = List.of(new Amogus(1, "IP1-22","MONDAY","RUSSIAN LANG",new Date(122,2,2)));
        Assertions.assertEquals(expectedResult,actualResult);
    }
}