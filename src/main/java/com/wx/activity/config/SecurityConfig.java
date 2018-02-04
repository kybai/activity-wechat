package com.wx.activity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ky.bai
 * @date 2018-02-04 17:15
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/weixin/**").permitAll()
                //	字体
                .antMatchers("/**/*.css").permitAll()
                .antMatchers("/**/*.eot").permitAll()
                .antMatchers("/**/*.svg").permitAll()
                .antMatchers("/**/*.ttf").permitAll()
                .antMatchers("/**/*.woff").permitAll()
                .antMatchers("/**/*.woff2").permitAll()
                .antMatchers("/**/*.otf").permitAll()
                // css
                .antMatchers("/**/*.css").permitAll()
                // 按钮
                .antMatchers("/**/*.ico").permitAll()
                // 图片
                .antMatchers("/**/*.png").permitAll()
                .antMatchers("/**/*.gif").permitAll()
                .antMatchers("/**/*.jpg").permitAll()
                // js
                .antMatchers("/**/*.js").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }
}
