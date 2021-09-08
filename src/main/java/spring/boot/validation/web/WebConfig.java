package spring.boot.validation.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
//@EnableAutoConfiguration(exclude = ValidationFixAutoConfiguration.class)
@EnableAutoConfiguration(exclude = ValidationAutoConfiguration.class)
public class WebConfig {
}
