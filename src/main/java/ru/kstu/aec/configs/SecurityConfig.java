package ru.kstu.aec.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import ru.kstu.aec.models.User;
import ru.kstu.aec.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    // не менять на инициализацию в конструкторе!!!

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // бин для энкодера пароля

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder());
    }
    // тут мы просто задаем юзер сервису энкодер для пароля

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin", "/courses", "/course", "/chapters", "/chapter", "/tests", "/test", "/create", "/result").hasAuthority("ADMIN")
                .antMatchers("/creator", "/courses", "/course", "/chapters", "/chapter", "/tests", "/test", "/create", "/result").hasAuthority("TEACHER")
                .antMatchers("/profile", "/courses", "/course", "/chapters", "/chapter", "/tests", "/test", "/create", "/result").hasAuthority("USER")
                .antMatchers("/", "/css/**","/static/**", "/resources/**","/.*.css","/statistics/*", "/statistics/**").permitAll()
                .antMatchers("/login","/registration").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile", true)
                .permitAll()
                .and()
                .logout()
                .deleteCookies("remove")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll();
    }
    /*
     antMatchers() нужен шобы давать доступ к определенным URL, если хош добавить необходимость в правах,
     добавляешь hasAuthority("название роли"), если не хош, то просто permitAll()
     anyRequest() разрешает любые http запросы
     authenticated() чекает аутентифицирован ли юзер
     formLogin() это обозначение для начала конфигурации входа
     loginPage() указывает на URL по которому будет находиться форма входа
     defaultSuccessUrl() очевидно
     logout() это обозначение для конфигурации выхода
     дальше очевидно
     */

    public static Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth;
    }
    // статичный метод для получения аутентификационного токена, содержащего всю инфу о текущем пользователе
    // шобы узнать чо ты можешь получить можешь просто ткнуть точку и IDEA покажет методы

    public static void isTeacher(Model model) {
        int state = 0;
        try {
            boolean isTeacher = ((User)getAuthentication().getPrincipal()).isTeacher();
            if(isTeacher) {
                state = 2;
            }
            else {
                state = 1;
            }
        }
        catch (Exception e) {
            state = 0;
        }
        model.addAttribute("auth", state);
    }
    // метод шобы отправлять в модель является ли юзер учителем или админом или школяром
}
