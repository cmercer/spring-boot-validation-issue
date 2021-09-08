package spring.boot.validation.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import spring.boot.validation.childcontext.ChildConfig;
import spring.boot.validation.parentcontext.ParentConfig;
import spring.boot.validation.web.WebConfig;

@SpringBootApplication
@EnableConfigurationProperties
public class DemoApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.parent(ParentConfig.class).web(WebApplicationType.NONE).bannerMode(Banner.Mode.OFF)
				.child(ChildConfig.class).web(WebApplicationType.NONE).bannerMode(Banner.Mode.OFF)
				.sibling(WebConfig.class).web(WebApplicationType.SERVLET).bannerMode(Banner.Mode.OFF)
				.run(args);

	}

}
