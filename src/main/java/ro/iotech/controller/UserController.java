package ro.iotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.iotech.Model.User.UserService;
import ro.iotech.security.ExistUserException;
import ro.iotech.security.InvalidPasswordException;
import ro.iotech.security.UserSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    //CE TREBUIE SA FACEM AICI???
    // - daca parolele sunt identice
    @GetMapping("/register-form")
    public ModelAndView registerAction(@RequestParam("email") String email,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("password") String password,
                                       @RequestParam("password2") String password2) {
        ModelAndView modelAndView = new ModelAndView("register");
        ModelAndView loginTemplate= new ModelAndView("login");

        //salvare efectiva in baza de date !!!
        try {
            userService.saveNewUser(email, phone, password, password2);
            loginTemplate.addObject("message", "Contul a fost creat cu succes, va puteti loga!");

        } catch (InvalidPasswordException invalidPassword) {
            String messageException = invalidPassword.getMessage();
            modelAndView.addObject("message", messageException);
            return modelAndView;
        }

        //redirectionam user-ul catre pagina pagina de login daca este totul ok
        return loginTemplate;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password
                              ) {
        ModelAndView modelAndView = new ModelAndView("login");

        try {
            userService.loginUser(email, password);

            modelAndView.addObject("message", "");
            modelAndView = new ModelAndView("redirect:/user_main_page");
        } catch (ExistUserException existUserException) {
            String messageException = existUserException.getMessage();
            modelAndView.addObject("message", messageException);

        }
        return modelAndView;
    }


}
