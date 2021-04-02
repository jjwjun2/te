package bitcamp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return String.format("%s", LocalDate.now());
    }
}
