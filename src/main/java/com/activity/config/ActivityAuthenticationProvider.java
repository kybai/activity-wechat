package com.activity.config;

import com.activity.utils.Constants;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Create by ky.bai on 2017-12-05 9:00
 */
public class ActivityAuthenticationProvider extends DaoAuthenticationProvider {

    public ActivityAuthenticationProvider(UserDetailsService userDetailsService) {
        super();
        setUserDetailsService(userDetailsService);
        setHideUserNotFoundExceptions(false);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;
        if (getSaltSource() != null) {
            salt = getSaltSource().getSalt(userDetails);
        }

        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(Constants.LOGIN_FAILURE_NOT_EMPTY);
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if ("".equals(presentedPassword)) {
                this.logger.debug("Authentication failed: password does not empty value");
                throw new BadCredentialsException(Constants.LOGIN_FAILURE_NOT_EMPTY);
            } else if (!getPasswordEncoder().isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException(Constants.LOGIN_FAILURE_ERROR);
            }
        }
    }
}
