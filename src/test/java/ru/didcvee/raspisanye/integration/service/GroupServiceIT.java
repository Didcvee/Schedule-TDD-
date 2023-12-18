package ru.didcvee.raspisanye.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.didcvee.raspisanye.entity.Group;
import ru.didcvee.raspisanye.repo.GroupRepo;
import ru.didcvee.raspisanye.service.GroupService;
import ru.didcvee.raspisanye.util.GroupFilteringPag;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/papa.sql")
@Transactional
public class GroupServiceIT extends IntegrationTestBase {

    private final GroupService groupService;
    private final GroupRepo groupRepo;

    public GroupServiceIT(GroupService groupService, GroupRepo groupRepo) {
        this.groupService = groupService;
        this.groupRepo = groupRepo;
    }

    @Test
    void test(){
        List<Group> all = groupService.getAll();
        assertThat(all.size()).isEqualTo(4);;
    }
    @Test
    void addGroup(){
        Group group = new Group("Group 23", 23);
        String actual = groupService.addGroup(group);
        Assertions.assertEquals("Успешно",actual);

        assertThatThrownBy(() -> {
            groupService.addGroup(new Group("Group 23",123)); // вызываем метод с той же группой
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Такая группа уже есть");

    }
    @Test
    void testPagination(){
        Group group1 = new Group("Group 1", 10);
        Group group2 = new Group("Group 2", 15);

        List<Group> result = groupService.getByFilteringAndPagination(new GroupFilteringPag(1,2,""));

        assertThat(result).containsExactly(group1,group2);

    }
    @Test
    void testPaginationAndFilter(){
        Group group2 = new Group("LOL", 30);
        List<Group> result = groupService.getByFilteringAndPagination(new GroupFilteringPag(1,2,"L"));
        List<Group> resultEmpty = groupService.getByFilteringAndPagination(new GroupFilteringPag(1,4,"sss"));
        assertTrue(resultEmpty.isEmpty());
        assertTrue(!result.isEmpty());
        assertThat(result).containsExactly(group2);
    }
}
