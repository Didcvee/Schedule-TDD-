package ru.didcvee.raspisanye.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import ru.didcvee.raspisanye.integration.service.IntegrationTestBase;

import java.util.List;

class AlohaRepoTest extends IntegrationTestBase {
    private final AlohaRepo alohaRepo;

    AlohaRepoTest(AlohaRepo alohaRepo) {
        this.alohaRepo = alohaRepo;
    }
    @Test
    @Sql({"/papa.sql"})
    void test(){
        List<String> actual = alohaRepo.get();
        Assertions.assertTrue(!actual.isEmpty());

        var expected = List.of("mamasito");
        Assertions.assertEquals(expected,actual);
    }
}