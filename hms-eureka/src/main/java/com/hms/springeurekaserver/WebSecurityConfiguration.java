package com.hms.springeurekaserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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
	http.formLogin().permitAll().and().requestMatchers().antMatchers("/login", "/admin", "/logout", "/admin/logout")
		.and().csrf().disable().authorizeRequests().antMatchers("/admin").authenticated().and().logout()
		.logoutUrl("/logout").logoutUrl("/admin/logout").clearAuthentication(true).invalidateHttpSession(true)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }
}
