package spring.boot.validation.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 */
@Validated
public class ConfigPropertiesForceCreation {

  private static final Logger log = LoggerFactory.getLogger(ConfigProperties.class);

  ConfigProperties configProperties;



  public ConfigPropertiesForceCreation(
     ConfigProperties configProperties
  ) {
    this.configProperties = configProperties;
  }

  public String printCountAndValue(@Valid @NotNull String value) {
    log.warn("Count: {}, Value: {}", ConfigProperties.count, value);
    return null;
  }
}
