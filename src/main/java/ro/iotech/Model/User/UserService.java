package ro.iotech.Model.User;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.iotech.database.UserDAO;
import ro.iotech.security.ExistUserException;
import ro.iotech.security.InvalidPasswordException;
import ro.iotech.security.UserSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;


    @Autowired
    UserSession userSession;

    public void saveNewUser(String email, String phone, String password, String password2) throws InvalidPasswordException {

        char[] passwordChar=password.toCharArray();
        boolean hasUpperCase=false;
        boolean hasLowerCase=false;
        boolean hasNumber=false;

        for (char c : passwordChar) {
            if( Character.isLetter(c) ){
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            }
            else {
                hasNumber = true;
            }
        }

        if (password.length() < 8) {
            throw new InvalidPasswordException("parola trebuie sa aibe peste 8 caractere");
        }

        if(!hasLowerCase){
            throw new InvalidPasswordException("Parola trebuie sa contina minim o litera mica");

        }
        if(!hasUpperCase){
            throw new InvalidPasswordException("Parola trebuie sa contina minim o litera mare");

        }
        if(!hasNumber){
            throw new InvalidPasswordException("Parola trebuie sa contina minim o cifra");

        }

        if (!password.equals(password2)) {
            throw new InvalidPasswordException("Parolele nu se potrivesc");
        }

        if (userDAO.findByEmail(email).size() > 0) {
            throw new InvalidPasswordException("Exista deja un utilizator cu acest email");
        }
        if (userDAO.findPhone(phone).size() > 0) {
            throw new InvalidPasswordException("Exista deja un utilizator cu acest telefon");
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String createAccountDate=dateFormat.format(date);

        //folosim functia md5 pentru a 'cripta' parola
        String passwordMD5 = DigestUtils.md5Hex(password);
        userDAO.saveNewUser(email, phone, createAccountDate, passwordMD5);
    }

    public List<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public void loginUser(String email, String password) throws ExistUserException {
         password=DigestUtils.md5Hex(password);
        List<User> userList = userDAO.findByEmailAndPassword(email, password);
        if (userList.size() == 0) {
            throw new ExistUserException("Credentialele nu sunt corecte!");
        }
        if (userList.size() > 1) {
            throw new ExistUserException("Credentialele nu sunt corecte!");
        }
        if (userList.size() == 1) {
            User userFromDatabase = userList.get(0);
            if (!userFromDatabase.getPassword().equals(password)) {
                throw new ExistUserException("Credentialele nu sunt corecte!");
            } else {
                userSession.setUserId(userFromDatabase.getId());
                userSession.setEmail(email);
            }
        }
    }

    public int getUserId(String email){

        return userDAO.getUserId(email);
    }
}
