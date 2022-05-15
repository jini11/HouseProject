package com.mycom.HouseProject.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // 보안체크를 하지 않는다는 의미 --> 실제 홈페이지에서는 위험(여기선 테스트를 위해)
                .authorizeRequests()
                    .antMatchers("/", "/account/register","/css/**", "/image/**", "/api/**", "/house/**", "/board/**").permitAll() //해당 경로들은 접근 허용
                    .anyRequest().authenticated() //다른 모든 요청은 인증된 유저만 접근 허용
                    .and()
                .formLogin()
                    .usernameParameter("userid")
                    .loginPage("/account/login")
                    .permitAll()
                    .and()
                .logout()
                /*    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID","remember-me")*/
                    .permitAll();
                /*    .and()
                .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .and()
                .and() .rememberMe()
                    .alwaysRemember(false)
                    .tokenValiditySeconds(43200)
                    .rememberMeParameter("remember-me");*/

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select userid, password, enabled " // 띄어쓰기 주의
                        + "from user "
                        + "where userid = ?")
                .authoritiesByUsernameQuery("select u.userid, r.name "
                        + "from user_role ur inner join user u on ur.user_id = u.id "
                        + "inner join role r on ur.role_id = r.id "
                        + "where u.userid = ?");
    }
    // authentication --> 로그인 / authroization --> 권한

    @Bean
    public static PasswordEncoder passwordEncoder() { // static 없으면 BeanCurrentlyInCreationException 오류 발생
        return new BCryptPasswordEncoder();
    }
}

