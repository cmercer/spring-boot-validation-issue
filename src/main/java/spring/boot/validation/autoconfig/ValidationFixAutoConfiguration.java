package spring.boot.validation.autoconfig;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.boot.validation.beanvalidation.FilteredMethodValidationPostProcessor;
import org.springframework.boot.validation.beanvalidation.MethodValidationExcludeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

/**
 * {@link EnableAutoConfiguration Auto-configuration} to configure the validation
 * infrastructure.
 *
 * @author Stephane Nicoll
 * @author Madhura Bhave
 * @since 1.5.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ExecutableValidator.class)
@ConditionalOnResource(resources = "classpath:META-INF/services/javax.validation.spi.ValidationProvider")
@Import(PrimaryDefaultValidatorPostProcessor.class)
public class ValidationFixAutoConfiguration {
  @Bean
  @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
  @ConditionalOnMissingBean(Validator.class)
  public static LocalValidatorFactoryBean defaultValidator() {
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
    factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
    return factoryBean;
  }

  @Bean
  @ConditionalOnMissingBean(search = SearchStrategy.CURRENT)
  public static MethodValidationPostProcessor methodValidationPostProcessor(Environment environment,
                                                                            @Lazy Validator validator, ObjectProvider<MethodValidationExcludeFilter> excludeFilters) {
    FilteredMethodValidationPostProcessor processor = new FilteredMethodValidationPostProcessor(
        excludeFilters.orderedStream());
    boolean proxyTargetClass = environment.getProperty("spring.aop.proxy-target-class", Boolean.class, true);
    processor.setProxyTargetClass(proxyTargetClass);
    processor.setValidator(validator);
    return processor;
  }
}
