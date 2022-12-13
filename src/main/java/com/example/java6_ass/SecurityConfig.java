package com.example.java6_ass;

import com.example.java6_ass.entity.Account;
import com.example.java6_ass.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    AccountService accountService;


   //cung cấp dữ liệu đăng nhập
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                Account user = accountService.getByUsername(username);
                String password = encoder.encode(user.getPassword());
                String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId()).collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username).password(password).roles(roles).build();
            }catch (NoSuchElementException e){
                throw new UsernameNotFoundException("username"+ "not found");
            }
        });
    }
   //phân quyền sử dụng
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable(); // vô hiệu hóa

        // phần quyền
        http.authorizeRequests().antMatchers("/rest/authorities").hasRole("DIRE") // chỉ admin mới vào được
                .antMatchers("/admin/**").hasAnyRole("DIRE","STAF") // 1 trong 2 nhan vien, admin đều vào được
                .antMatchers("/order/**").authenticated() // đăng nhập thành công là vào được
                .anyRequest().permitAll(); // url còn lại k cần đăng nhập

        http.formLogin().loginPage("/security/loginForm") // địa chỉ dẫn đến form login form login
                .loginProcessingUrl("/security/login") // địa địa formlogin gửi tới
               .defaultSuccessUrl("/security/login/success", false) // địa chỉ login thành công
                .failureUrl("/security/login/error"); // địa chỉ login thất bại

        http.rememberMe().rememberMeParameter("remember");
        // trang k được quyền truy cập

        http.exceptionHandling().accessDeniedPage("/security/unauthoried");
//
        http.logout().logoutUrl("/security/logout").logoutSuccessUrl("/security/logout/success");
    }
    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }


}
