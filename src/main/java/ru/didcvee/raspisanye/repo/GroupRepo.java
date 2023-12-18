package ru.didcvee.raspisanye.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    private static final String GET_ONE = """
            select * from group_ WHERE NAME=?
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
    public Group findOne(String name){
        Group group = jdbcTemplate.queryForObject(GET_ONE, new Object[]{name}, mapper());
        return group;
    }


    public Page<Group> getByFilteringAndPagination(GroupFilteringPag groupFilter) {
        int offset = groupFilter.getPage() * groupFilter.getSize();

        String countQuery = "SELECT COUNT(*) FROM group_ WHERE name LIKE ?";
        int totalCount = jdbcTemplate.queryForObject(countQuery, Integer.class, "%" + groupFilter.getNameGroup() + "%");

        String query = "SELECT * FROM group_ WHERE name LIKE ? ORDER BY " + groupFilter.getSort() + " LIMIT ? OFFSET ?";
        List<Group> groups = jdbcTemplate.query(query, mapper(), "%" + groupFilter.getNameGroup() + "%", groupFilter.getSize(), offset);
        return new PageImpl<>(groups, PageRequest.of(groupFilter.getPage(), groupFilter.getSize()), totalCount);
    }
    private RowMapper<Group> mapper(){
        return (rs, rowNum) -> new Group(
                rs.getString("name"),
                rs.getInt("people_count")
        );
    }
}
