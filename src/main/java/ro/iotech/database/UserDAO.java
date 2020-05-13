package ro.iotech.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.iotech.Model.User.User;
import ro.iotech.Model.User.UserRowMapper;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveNewUser(String email, String phone, String createAccountDate, String password) {
        jdbcTemplate.update("insert into users values(null, ?, ?, null, null, null, ?, ?)", email, password, createAccountDate, phone);
    }

    public List<User> findByEmail(String email) {
        return jdbcTemplate.query("select * from users where email='" + email + "';", new UserRowMapper());
    }

    public List<User> findPhone(String phone) {
        return jdbcTemplate.query("select * from users where telefon='" + phone + "';", new UserRowMapper());
    }

    public List<User> findByEmailAndPassword(String email, String password) {
        return jdbcTemplate.query("select * from users where email='" + email + "' and parola='"+password+"';", new UserRowMapper());
    }
}
