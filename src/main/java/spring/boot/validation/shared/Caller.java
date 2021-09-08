package spring.boot.validation.shared;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
public class Caller {

  ConfigPropertiesForceCreation configPropertiesForceCreation;
  private boolean call = true;

  public Caller(ConfigPropertiesForceCreation configPropertiesForceCreation) {
    this.configPropertiesForceCreation = configPropertiesForceCreation;
  }

  public Caller(ConfigPropertiesForceCreation configPropertiesForceCreation, boolean call) {
    this.configPropertiesForceCreation = configPropertiesForceCreation;
    this.call = call;
  }

  @PostConstruct
  public void postConstruct() {
    if(call) {
      configPropertiesForceCreation.printCountAndValue("Test");
      configPropertiesForceCreation.printCountAndValue(null);
    }
  }
}
