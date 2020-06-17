package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EmailController {
    private static Pattern pattern;
    private Matcher matcher;
    private static final String REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    public EmailController(){
        pattern = Pattern.compile(REGEX);
    }
    @GetMapping("/")
    String getIndex(){
        return "index";
    }
    @PostMapping("/validate")
    String validateMail(@RequestParam("email") String email, Model model){
        boolean isValidate =  this.validate(email);
        if(!isValidate) {
            model.addAttribute("message", "Email is invalid");
            return "index";
        }
        model.addAttribute("email",email);
        return "success";
    }
    private boolean validate(String regex){
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
