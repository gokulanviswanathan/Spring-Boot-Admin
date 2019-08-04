package com.hotelmgmt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * The Class WebSecurityConfiguration.
 * 
 * @author Gokulan
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
	http.formLogin().permitAll().and().requestMatchers().antMatchers("/login", "/logout", "/actuator/**", "/").and()
		.csrf().disable().authorizeRequests().antMatchers("/").authenticated().and().logout()
		.clearAuthentication(true).invalidateHttpSession(true).logoutSuccessHandler(new LogoutSuccessHandler() {

		    @Override
		    public void onLogoutSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
			    throws IOException, ServletException {
			// TODO Auto-generated method stub

		    }
		});
    }
}
