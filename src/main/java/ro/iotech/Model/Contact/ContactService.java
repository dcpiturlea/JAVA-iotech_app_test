package ro.iotech.Model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.iotech.database.ContactDAO;
import ro.iotech.database.UserDAO;
import ro.iotech.security.ContactFormException;
import ro.iotech.security.InvalidPasswordException;

@Service
public class ContactService {

    @Autowired
    ContactDAO contactDAO;

    public void saveContactForm(String name, String email, String subject, String message) throws ContactFormException {
        if (email.length() < 5) {
            throw new ContactFormException("Emailul nu poate avea sub 5 caractere");

        }
        if (!email.contains("@")) {
            throw new ContactFormException("Emailul trebuie sa contina '@' si '.' ");

        }
        if (!email.contains(".")) {
            throw new ContactFormException("Emailul trebuie sa contina '@' si '.' ");

        }
        if (name.length() < 5) {
            throw new ContactFormException("Numele nu poate avea sub 5 caractere");
        }
        if (subject.length() < 5) {
            throw new ContactFormException("Subiectul nu poate avea sub 5 caractere");

        }
        if (message.length() < 5) {
            throw new ContactFormException("Mesajul nu poate avea sub 5 caractere");

        }
        else {
            contactDAO.saveContactForm(name, email, subject, message);

        }
    }
}
