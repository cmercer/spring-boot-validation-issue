package spring.boot.validation.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;

/**
 *
 */
@Controller
@RequestMapping("/")
@Validated
public class ValidationController {


   @GetMapping
   @ResponseBody
   public String get(@NotBlank @RequestParam String echo) {
      return "Hello " + echo + "!";
   }
}
