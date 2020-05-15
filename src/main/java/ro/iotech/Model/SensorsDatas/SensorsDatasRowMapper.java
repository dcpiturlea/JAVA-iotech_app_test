package ro.iotech.Model.SensorsDatas;

import org.springframework.jdbc.core.RowMapper;
import ro.iotech.Model.User.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorsDatasRowMapper implements RowMapper<SensorsDatas> {
    @Override
    public SensorsDatas mapRow(ResultSet resultSet, int i) throws SQLException {
        SensorsDatas sensorsDatas= new SensorsDatas();
        sensorsDatas.setId(resultSet.getInt("ID"));
        sensorsDatas.setTemp(resultSet.getDouble("temperatura"));
        sensorsDatas.setHum(resultSet.getDouble("umiditate"));
        sensorsDatas.setCo2(resultSet.getInt("CO2"));
        sensorsDatas.setButan(resultSet.getInt("butan"));
        sensorsDatas.setCalitateAer(resultSet.getInt("calitate.aer"));
        sensorsDatas.setIp_tip_senzori(resultSet.getInt("id_tip_senzori"));
        sensorsDatas.setId_user(resultSet.getInt("id_user"));
        sensorsDatas.setData_adaugarii(resultSet.getString("data.adaugarii"));
        sensorsDatas.setOra_adaugarii(resultSet.getString("data.adaugarii").substring(10, 16));
        return sensorsDatas;
    }
}
