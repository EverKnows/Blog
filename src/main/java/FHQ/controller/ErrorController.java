package FHQ.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    @RequestMapping("/404")
    public String page404(HttpServletRequest request, Throwable ex, Model model) {
        String message = ex.getMessage();
        System.out.println("错误信息：" + message);
        return "error/404";
    }

    @RequestMapping("/500")
    public String page500() {
        return "error/500";
    }
}
