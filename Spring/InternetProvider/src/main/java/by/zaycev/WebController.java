package by.zaycev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WebController {
    {
        System.out.println("CONTROLLER____________");
    }
    @RequestMapping(value = "/InternetProvider", method = RequestMethod.GET)
    public String index() {
        System.out.println("INDEX--------------------");
        return "index";
    }
    @RequestMapping(value = "/InternetProvider/redirect", method = RequestMethod.GET)
    public String redirect() {
        System.out.println("REDIRECT--------------------");
        return "redirect:/InternetProvider/finalPage";
    }
    @RequestMapping(value = "/InternetProvider/finalPage", method = RequestMethod.GET)
    public String finalPage() {
        return "final";
    }
}