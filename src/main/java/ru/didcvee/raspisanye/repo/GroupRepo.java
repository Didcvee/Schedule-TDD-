package ru.didcvee.raspisanye.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.didcvee.raspisanye.entity.Group;
import ru.didcvee.raspisanye.util.GroupFilteringPag;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Repository
public class GroupRepo {
    private static final String GET_ALL = """
            select * from group_
            """;
    private static final String FILTERING_PAGINATION = "SELECT * FROM group_ WHERE name LIKE ? LIMIT ? OFFSET ?";

    private static final String CREATE_GROUP = "INSERT INTO GROUP_(NAME,PEOPLE_COUNT) VALUES(?,?)";
    private final JdbcTemplate jdbcTemplate;

    public GroupRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int addGroup(Group group){
        int update = jdbcTemplate.update(CREATE_GROUP,group.getName(),group.getCountPeople());
        return update;
    }

    public List<Group> getAll(){
        List<Group> groups = jdbcTemplate.query(GET_ALL, mapper());
        return groups;
    }


    public List<Group> getByFilteringAndPagination(GroupFilteringPag groupFilter) {
        int offset = (groupFilter.getPage() - 1) * groupFilter.getSize();
        List<Group> groupList = jdbcTemplate.query(FILTERING_PAGINATION, new Object[]{"%" + groupFilter.getNameGroup() + "%", groupFilter.getSize(), offset}, mapper());
        return groupList;
    }
    private RowMapper<Group> mapper(){
        return (rs, rowNum) -> new Group(
                rs.getString("name"),
                rs.getInt("people_count")
        );
    }
}
