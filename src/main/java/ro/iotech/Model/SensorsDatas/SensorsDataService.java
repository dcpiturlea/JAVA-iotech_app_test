package ro.iotech.Model.SensorsDatas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.iotech.database.SensorsDatasDAO;
import ro.iotech.database.UserDAO;
import ro.iotech.security.ExistUserException;
import ro.iotech.security.ParametersException;

import java.util.List;

@Service
public class SensorsDataService {

    @Autowired
    SensorsDatasDAO sensorsDatasDAO;


    public List<SensorsDatas> getSensorsDatas(Integer userId) {

        return sensorsDatasDAO.getAllSensorsDatas(userId);
    }

    public String checkParameters(List<SensorsDatas> sensorsDatas) throws ParametersException {
        String result = "Nu exista date adaugate!";
        for (SensorsDatas s : sensorsDatas) {

            if (s.getTemp() > 25.0 && s.getHum() > 80.0 && s.getCalitateAer() > 500) {
                throw new ParametersException("Toti parametrii au fost depasiti!");
            }

            if (s.getTemp() > 25.0 && s.getHum() > 80.0 || (s.getTemp() > 25.0 && s.getCalitateAer() > 500)
                    || (s.getCalitateAer() > 500 && s.getHum() > 80.0)) {
                throw new ParametersException("Doi parametrii au fost depasiti!");
            }

            if (s.getTemp() > 25.0) {
                throw new ParametersException("Temperatura depasita!");
            }


            if (s.getHum() > 80.0) {
                throw new ParametersException("Umiditate depasita!");
            }


            if (s.getCalitateAer() > 500) {
                throw new ParametersException("Calitate aer depasita!");
            }
            else{
                result="Parametrii in limite normale!";
            }
        }

        return result;


    }
}
