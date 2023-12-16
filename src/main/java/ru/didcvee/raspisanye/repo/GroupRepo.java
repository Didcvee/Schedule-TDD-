package ru.didcvee.raspisanye.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.didcvee.raspisanye.entity.Group;

import java.util.List;

@Repository
public class GroupRepo {
    private static final String GET_ALL = """
            select * from group_
            """;
    private static final String get = """
            select * from group_ where name = ?
            """;
    private final JdbcTemplate jdbcTemplate;

    public GroupRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Group> getAll(){
        List<Group> groups = jdbcTemplate.query(GET_ALL, (RowMapper<Group>) (rs, rowNum) -> new Group(
                rs.getString("name")
        ));
        return groups;
    }


}
