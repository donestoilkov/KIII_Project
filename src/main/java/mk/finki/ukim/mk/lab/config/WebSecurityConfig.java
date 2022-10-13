package mk.finki.ukim.mk.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider;


    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernamePasswordAuthenticationProvider = customUsernamePasswordAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/balloons","/register").permitAll()
                .antMatchers("/balloons/add","/balloons/edit-form/**","/balloons/delete/**","/balloons/add-form").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().defaultSuccessUrl("/balloons")
                .and().logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder.encode("admin"))
//                .authorities("ROLE_ADMIN")
//                .and()
//                .withUser("stoilkovdone")
//                .password(passwordEncoder.encode("stoilkovdone"))
//                .authorities("ROLE_USER");
        auth.authenticationProvider(customUsernamePasswordAuthenticationProvider);
    }
}
