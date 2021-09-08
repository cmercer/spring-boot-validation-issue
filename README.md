# spring-boot-validation-issue
Demo application for spring-boot-validation issues

# Summary
ValidationAutoConfiguration.methodValidationPostProcessor should use SearchStrategy.CURRENT.

# Details
BeanPostProcessors only apply to the applicationContext they are created in.
If you have shared classpath with a parent and child context created something like this.

```
new SpringApplicationBuilder()
    .parent(ParentConfig.class).web(WebApplicationType.NONE).bannerMode(Banner.Mode.OFF)
    .child(WebConfig.class).web(WebApplicationType.SERVLET).bannerMode(Banner.Mode.OFF)
    .run(args);

```
The child web context will not validate method parameters, such as in this example

```
@GetMapping
@ResponseBody
public String get(@NotNull @RequestParam String echo) {
    return "Hello " + echo + "!";
}
```
The demo application excludes ValidationAutoConfiguration from autoconfiguration and adds ValidationFixAutoConfiguration.
The issue is a bit confusing, but you can change the @EnableAutoConfiguration in ParentConfig, ChildConfig and WebConfig
to switch between disabling the two version of validation auto configuration.

spring.boot.validation.autoconfig.ValidationFixAutoConfiguration

org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration

You may need to also change the Caller configuration in ParentConfig and ChildConfig to see the issue.
