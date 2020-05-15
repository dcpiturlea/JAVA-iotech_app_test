package ro.iotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.iotech.Model.SensorsDatas.SensorsDataService;
import ro.iotech.Model.SensorsDatas.SensorsDatas;
import ro.iotech.Model.User.User;
import ro.iotech.Model.User.UserService;
import ro.iotech.security.ParametersException;
import ro.iotech.security.UserSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserMainPageController {

    @Autowired
    SensorsDataService sensorsDataService;


    @Autowired
    UserSession userSession;

    @GetMapping("user_main_page")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("users_page/indexuser");
        if (userSession.getUserId() == 0) {
            return new ModelAndView("redirect:login.html");
        } else {

            String userEmail = userSession.getEmail();
            List<SensorsDatas> datas = sensorsDataService.getSensorsDatas(userSession.getUserId());
            try {
                sensorsDataService.checkParameters(datas);
            } catch (ParametersException e) {
                modelAndView.addObject("paramStat", e.getMessage());
            }
            modelAndView.addObject("datas", datas);
            modelAndView.addObject("email", userEmail);
        }
        return modelAndView;
    }
}
