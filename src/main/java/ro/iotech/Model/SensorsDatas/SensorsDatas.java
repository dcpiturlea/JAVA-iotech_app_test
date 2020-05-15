package ro.iotech.Model.SensorsDatas;

import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.util.Date;

@Repository
public class SensorsDatas {

    private int Id;
    private double temp;
    private double hum;
    private int co2;
    private int butan;
    private int calitateAer;
    private int ip_tip_senzori;
    private int id_user;
    private String data_adaugarii;
    private String ora_adaugarii;


    public String getOra_adaugarii() {
        return ora_adaugarii;
    }

    public void setOra_adaugarii(String ora_adaugarii) {
        this.ora_adaugarii = ora_adaugarii;
    }

    public String getData_adaugarii() {
        return data_adaugarii;
    }

    public void setData_adaugarii(String data_adaugarii) {
        this.data_adaugarii = data_adaugarii;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHum() {
        return hum;
    }

    public void setHum(double hum) {
        this.hum = hum;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getButan() {
        return butan;
    }

    public void setButan(int butan) {
        this.butan = butan;
    }

    public int getCalitateAer() {
        return calitateAer;
    }

    public void setCalitateAer(int calitateAer) {
        this.calitateAer = calitateAer;
    }

    public int getIp_tip_senzori() {
        return ip_tip_senzori;
    }

    public void setIp_tip_senzori(int ip_tip_senzori) {
        this.ip_tip_senzori = ip_tip_senzori;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
