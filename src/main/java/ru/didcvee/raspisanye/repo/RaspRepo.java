package ru.didcvee.raspisanye.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.didcvee.raspisanye.entity.Amogus;

import java.util.Date;
import java.util.List;

@Repository
public class RaspRepo {
    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_BY_FILTERING = """
            SELECT * FROM rasp WHERE
             group_id = ? AND
              date_ BETWEEN ? AND ?;
            """;

    public RaspRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Amogus> getBy(String groupName, Date from, Date to) {
//        java.sql.Date date1 = new java.sql.Date(from.getTime());
//        java.sql.Date date2 = new java.sql.Date(to.getTime());
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2023, 11, 12); // 1 января 2022 года
//        long milliseconds = calendar.getTimeInMillis();
//        java.sql.Date date = new java.sql.Date(milliseconds);
//        System.out.println(date);
//
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.set(2023,11, 18); // 1 января 2022 года
//        long milliseconds2= calendar2.getTimeInMillis();
//        java.sql.Date date3 = new java.sql.Date(milliseconds2);
//
//
//        List<Amogus> query = jdbcTemplate.query(FIND_BY_FILTERING, ((rs, rowNum) -> new Amogus(rs.getInt("ordered"),
//                rs.getString("group_id"), rs.getString("week_day"),
//                rs.getString("item"), new Date(rs.getDate("date_").getTime()))), groupName, date, date3);
//        System.out.println(query);
//        return query;


        RowMapper<Amogus> rowMapper = (rs, rowNum) -> {
            Amogus amogus = new Amogus();
            amogus.setS(rs.getInt("ordered"));
            amogus.setVa(rs.getString("group_id"));
            amogus.setAs(rs.getString("week_day"));
            amogus.setItem(rs.getString("item"));
            amogus.setDate(rs.getDate("date_"));
            return amogus;
        };

        Date startDate = new Date(1,0,1);
        System.out.println(startDate);
        Date endDate = new Date(8000, 11, 31);
        System.out.println(endDate);
        System.out.println(groupName);

        List<Amogus> result = jdbcTemplate.query(
                    FIND_BY_FILTERING,
                new Object[] { groupName, from, to },
                rowMapper
        );
        System.out.println(result);
        return result;

    }
}
