package com.ibo.medical_platform.article.config;

import com.ibo.medical_platform.article.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http     //关闭跨站检测
                .csrf().disable()
                .authorizeRequests()
                //无需验证放行
                .antMatchers("/public/**")
                //放行登录
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                //拥有权限才可访问
//                .antMatchers(HttpMethod.GET,"/sys/**").hasAuthority("admin")
                //拥有任一权限即可访问
                .antMatchers(HttpMethod.GET, "/vipUser/**").hasAnyAuthority("vipuser")
                //角色类似，hasRole(),hasAnyRole()
                .antMatchers("/sys/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                //未登录跳转页面,设置了authenticationentrypoint后无需设置未登录跳转页面
//                .loginPage("/login")
               //处理登录post请求接口，无需自己实现
                .loginProcessingUrl("/login")
                .successForwardUrl("/login/suc")
                //登录失败转发接口
                .failureForwardUrl("/failed")
                .and()
                //自定义登出
                .logout()
                //自定义登出api，无需自己实现
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccess")
                ;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}