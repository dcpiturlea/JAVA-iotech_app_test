package ro.iotech.database;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ro.iotech.Model.SensorsDatas.SensorsDataService;
import ro.iotech.Model.SensorsDatas.SensorsDatas;
import ro.iotech.Model.SensorsDatas.SensorsDatasRowMapper;
import ro.iotech.Model.User.UserRowMapper;

import java.util.List;

@Repository
public class SensorsDatasDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<SensorsDatas> getAllSensorsDatas(Integer userId) {
        return jdbcTemplate.query("select * from parametri_senzori where id_user=" + userId + "", new SensorsDatasRowMapper());
    }




}
