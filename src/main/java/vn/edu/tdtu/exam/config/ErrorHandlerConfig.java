package vn.edu.tdtu.exam.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlerConfig implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/login";
    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}