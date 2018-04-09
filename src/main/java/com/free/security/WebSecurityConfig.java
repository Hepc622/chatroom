package com.free.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author HPC
 * @create 2018-04-08 11:08
 * @desc security 的java代码配置信息类
 *  这个配置在你的应用程序中创建一个springSecurityFilterChain 的Servlet的过滤器
 *  springSecurityFilterChain负责所有安全
 *  configureGlobal方法的名字不重要，
 *  然而，重要的是只在一个被@EnableWebSecurity, @EnableGlobalMethodSecurity，
 *  或者@EnableGlobalAuthentication注解的类中配置
 *  AuthenticationManagerBuilder，否则会有不可预知的后果。
 **/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 内存验证
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("samuel.gold@domain.com").password("thisisthepassword").roles("USER");
    }

    /**
     * 配置security的配置
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //关闭CSRF
        http
            .authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/login")// 这个loginProcessingUrl一定要与form表单的action一致
                .loginPage("/pages/page-login.html")
                .successForwardUrl("/pages/index.html")
                .permitAll();
        http.logout()
            .logoutSuccessUrl("/pages/page-login.html")
            .invalidateHttpSession(true);//让session失效
    }

//    @Bean
//    public SpringAuthenticationProvider springAuthenticationProvider() {
//        return new SpringAuthenticationProvider();
//    }
//
//    @Bean
//    public SpringDataUserDetailsService springDataUserDetailsService() {
//        return new SpringDataUserDetailsService();
//    }
//
//    /**
//     * 添加加密方式
//     * @return
//     */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
