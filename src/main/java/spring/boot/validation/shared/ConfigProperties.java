package spring.boot.validation.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 *
 */
@ConfigurationProperties("spring.boot.validation.issue")
@Validated
public class ConfigProperties {

  private static final Logger log = LoggerFactory.getLogger(ConfigProperties.class);

  private String name;

  public static int count = 0;

  public ConfigProperties() {
    log.warn("Started {}", getClass().getSimpleName());
  }

  @NotNull
  public String getName() {
    count++;
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCount() {
    return count;
  }
}
