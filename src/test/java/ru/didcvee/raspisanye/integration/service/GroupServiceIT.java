package ru.didcvee.raspisanye.integration.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.didcvee.raspisanye.service.GroupService;

@SpringBootTest
public class GroupServiceIT extends IntegrationTestBase {
    @Autowired
    private GroupService groupService;
    @Test
    void test(){

    }
}
