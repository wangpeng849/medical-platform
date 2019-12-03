package com.ibo.medical_platform.config;

import com.ibo.medical_platform.service.UserService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
                .permitAll()
                //放行登录
                .antMatchers("/login")
                .permitAll()
                //拥有权限才可访问
                .antMatchers(HttpMethod.GET,"/user/**").hasAuthority("getAllUser")
                //拥有任一权限即可访问
                .antMatchers(HttpMethod.GET, "/vipUser/**").hasAnyAuthority("1","2")
                .antMatchers("/sys/**").hasAnyRole("ROLE_ADMIN")
                //角色类似，hasRole(),hasAnyRole()
                .and()
                .formLogin()
                //未登录跳转页面,设置了authenticationentrypoint后无需设置未登录跳转页面
//                .loginPage("/public/unlogin")
//                //处理登录post请求接口，无需自己实现
                .loginProcessingUrl("/public/login")
                //登录失败转发接口
                .failureForwardUrl("/failed")
//                .usernameParameter("id") //修改用户名的表单name，默认为username
//                .passwordParameter("password")//修改密码的表单name，默认为password
                .and()
                //自定义登出
                .logout()
                //自定义登出api，无需自己实现
                .logoutUrl("/public/logout")
                .logoutSuccessUrl("/public/logoutSuccess")
                ;
    }

}