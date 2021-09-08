package spring.boot.validation.parentcontext;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.boot.validation.shared.Caller;
import spring.boot.validation.shared.ConfigProperties;
import spring.boot.validation.shared.ConfigPropertiesForceCreation;

/**
 *
 */
@Configuration
//@EnableAutoConfiguration(exclude = ValidationFixAutoConfiguration.class)
@EnableAutoConfiguration(exclude = ValidationAutoConfiguration.class)
@ComponentScan
public class ParentConfig {

  @Bean
  public ConfigProperties parentConfigProperties() {
    return new ConfigProperties();
  }

  @Bean
  public ConfigPropertiesForceCreation configPropertiesForceCreation(ConfigProperties configProperties) {
    return new ConfigPropertiesForceCreation(configProperties);
  }

  @Bean
  public Caller caller(ConfigPropertiesForceCreation configPropertiesForceCreation) {
    return new Caller(configPropertiesForceCreation, false);
  }
}
