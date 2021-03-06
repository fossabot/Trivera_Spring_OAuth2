package com.springclass.springsecurity.web.configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * <p>
 * Here we leverage Spring's {@link EnableWebMvc} support. This allows more powerful configuration but still be
 * concise about it.
 * Note that this class is loaded via the WebAppInitializer
 * </p>
 *
 * @author Mick Knutson
 * @since chapter 01.00
 */
//@Configuration
//@EnableWebMvc

//@ComponentScan(basePackages = {
//        "com.springclass.springsecurity.web.configuration",
//        "com.springclass.springsecurity.web.controllers",
//        "com.springclass.springsecurity.web.model"
//})
public class WebMvcConfig //extends WebMvcConfigurerAdapter
{

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

//    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(0) //Set to 0 in order to send cache headers that prevent caching
                .setCachePeriod(0)//Set to 0 in order to send cache headers that prevent caching
        ;

        // Add WebJars for Bootstrap & jQuery
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/").resourceChain(true);
        }

        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);

        }
    }

//    @Override
//    public void addViewControllers(final ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//
//        registry.addViewController("/login/form")
//                .setViewName("login");
//        registry.addViewController("/errors/403")
//                .setViewName("/errors/403");
//
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

} // The End...
