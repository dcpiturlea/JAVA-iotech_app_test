package ro.iotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.iotech.Model.Contact.ContactService;
import ro.iotech.Model.User.UserService;
import ro.iotech.security.ContactFormException;

@Controller
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping("/contact-form")
    public ModelAndView registerAction(@RequestParam("name") String name,
                                       @RequestParam("email") String email,
                                       @RequestParam("subject") String subject,
                                       @RequestParam("message") String message) {

        ModelAndView modelAndView = new ModelAndView("contact");
        try {
            contactService.saveContactForm(name, email,subject,message);
            modelAndView.addObject("message","Mesajul a fost trimis cu succes!" );
        } catch (ContactFormException contactFormException) {
            String messageException = contactFormException.getMessage();
            modelAndView.addObject("message", messageException);
        }

        return modelAndView;
    }

    @GetMapping("/contact")
    public ModelAndView register() {
        return new ModelAndView("contact");
    }

}
