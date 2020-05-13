package ro.iotech.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveContactForm(String name, String email, String subiect, String message) {
        jdbcTemplate.update("insert into contact values(null, ?, ?, ?, ?)", name, email, subiect, message);
    }

}
