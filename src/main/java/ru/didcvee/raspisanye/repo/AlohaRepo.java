package ru.didcvee.raspisanye.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AlohaRepo {
    private final JdbcTemplate jdbcTemplate;

    public AlohaRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<String> get(){
        List<String> stringList = jdbcTemplate.query("select * from aloha", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("mamasito");
            }
        });
        return stringList;

    }
}
