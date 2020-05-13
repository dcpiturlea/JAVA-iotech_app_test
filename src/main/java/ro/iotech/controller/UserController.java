package ro.iotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    UserSession userSession;

    //CE TREBUIE SA FACEM AICI???
    // - daca parolele sunt identice
    @GetMapping("/register-form")
    public ModelAndView registerAction(@RequestParam("email") String email,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("password") String password,
                                       @RequestParam("password2") String password2) {
        ModelAndView modelAndView = new ModelAndView("register");

        //salvare efectiva in baza de date !!!
        try {
            userService.saveNewUser(email, phone, password, password2);
        } catch (InvalidPasswordException invalidPassword) {
            String messageException = invalidPassword.getMessage();
            modelAndView.addObject("message", messageException);
            return modelAndView;
        }

        //redirectionam user-ul catre pagina pagina de login daca este totul ok
        return new ModelAndView("redirect:/login.html");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password
                              ) {
        ModelAndView modelAndView = new ModelAndView("login");

        try {
            userService.loginUser(email, password);
            modelAndView.addObject("message", "");
            modelAndView = new ModelAndView("users_page/indexuser");
        } catch (ExistUserException existUserException) {
            String messageException = existUserException.getMessage();
            modelAndView.addObject("message", messageException);

        }
        return modelAndView;
    }

    @GetMapping("users_page/indexuser")
    public ModelAndView dashboard() {
        if (userSession.getUserId() == 0) {
            return new ModelAndView("redirect:index.html");
        }
        //verific daca user-ul este logat sau nu
        return new ModelAndView("dashboard");
    }
}
