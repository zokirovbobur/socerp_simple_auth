package bbro.socerp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
    @RequestMapping("/")
    public ModelAndView hw(){
        return new ModelAndView("index.html");
    }
}
