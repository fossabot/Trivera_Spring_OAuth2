package com.springclass.springsecurity.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * <p>
 * A controller used to demonstrate how to display a different page depending on the user's role after login. The idea
 * is to tell Spring Security to use /default as the <a href=
 * "http://static.springsource.org/spring-security/site/docs/3.1.x/reference/appendix-namespace.html#nsa-form-login-default-target-url"
 * >default-target-url</a> and then programmatically determine where to send the user afterwards.
 * </p>
 * <p>
 * This has advantages over implementing {@link AuthenticationSuccessHandler} in that it is in no way tied to Spring
 * Security. Despite using Spring MVC for this, we could just have easily processed the url /default with another type
 * of controller like a Servlet or Struts Action so it is not required to use Spring MVC to use this approach.
 * </p>
 * <p>
 * Below is a snippet showing how to configure Spring Security to conditionally go to a different page after login if
 * the user went directly to the login page. If the user went first to a secured page, then Spring Security will send
 * the user to the secured page after login instead of /default.
 * </p>
 *
 * <pre>
 * &lt;http use-expressions="true" auto-config="true">
 *     &lt;intercept-url pattern="/login/form"
 *             access="permitAll"/>
 *     &lt;form-login login-page="/login/form"
 *             login-processing-url="/login"
 *             username-parameter="username"
 *             password-parameter="password"
 *             authentication-failure-url="/login/form?error"
 *             default-target-url="/default"/>
 * &lt;/http>
 * </pre>
 * <p>
 * By setting <a href=
 * "http://static.springsource.org/spring-security/site/docs/3.1.x/reference/appendix-namespace.html#nsa-form-login-always-use-default-target"
 * >always-use-default-target</a> to true, Spring Security will always send the user to this page and thus the first page
 * after login will always differ by role.
 * </p>
 *
 * <pre>
 * &lt;http use-expressions="true" auto-config="true">
 *     &lt;intercept-url pattern="/login*"
 *             access="permitAll"/>
 *     &lt;form-login login-page="/login/form"
 *             login-processing-url="/login"
 *             username-parameter="username"
 *             password-parameter="password"
 *             authentication-failure-url="/login/form?error"
 *             default-target-url="/default"
 *             always-use-default-target="true"/>
 * &lt;/http>
 * </pre>
 *
 * @author Rob Winch
 *
 */
@RestController
public class DefaultController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * http://localhost:8080/default/
     *
     * @param principal
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/default")
    public void defaultAfterLogin(Principal principal,
                                  final HttpServletRequest request,
                                  final HttpServletResponse response)
    throws IOException{
        logger.info("Principal: {}", principal);

        if (request.isUserInRole("ROLE_ADMIN")) {
            response.sendRedirect("/events/");
        }
        response.sendRedirect("/");
    }

} // The End...
