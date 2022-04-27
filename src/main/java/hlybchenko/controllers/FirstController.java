package hlybchenko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){
//        System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message", "Hello " + name + " " + surname);
        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }
    @GetMapping("/calculator")
    public String calculator(@RequestParam("firstValue")  double firstValue,
                             @RequestParam("secondValue") double secondValue,
                             @RequestParam("action") String action,
                             Model model){
        double solve;
        switch (action){
            case "multiplication" -> solve = firstValue * secondValue;
            case "addition" -> solve = firstValue + secondValue;
            case "subtraction" -> solve = firstValue - secondValue;
            default -> solve = firstValue / secondValue;
        }
        model.addAttribute("solve", "You solve: " + solve);
        return "first/calculator";
    }
}