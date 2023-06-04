package com.example.springsecurity.config;

//import com.example.springsecurity.security.AuthenticationProvider;
import com.example.springsecurity.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// @EnableWebSecurity //указываем, что этот класс будет конфигурировать (делать настройки для) спринг секьюрити
// Сначала сработает этот конфиг, в нем сработает метод configure, в котором указано, что производить конфигурацию
// надо на основе класса AuthenticationProvider
// public class SecurityConfig extends WebSecurityConfiguration {
@Configuration
public class SecurityConfig {
    private final PersonDetailsService personDetailsService;

    // Определяем метод хэширования паролей хэш-функцией BCrypt
    @Bean
    public PasswordEncoder getPasswordEncode(){
        return new BCryptPasswordEncoder();
    }

    // HttpSecurity отвечет за объект аутентификации
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Начало настройки доступа //
        // Конфигурируем работу SS
        // Так можно отключить csrf токены (защиту от межсайтовой подделки запросов), но делать этого не стоит
        // http.csrf().disable();
        http
                // Указываем, что все страницы должны быть защищены аутентификацией
                .authorizeHttpRequests()
                // Указываем на то, что страница /admin доступна пользователю с ролью ADMIN ("ROLE_" - отбрасывается, не указываем)
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Указываем, что указанные страницы доступны всем пользователям. Добавляем в этот список также таблицы стилей, JS, папки с картинками и тд (т.к. в момент, когда у пользователя не будет роли - ему все это будет недоступно)
                .requestMatchers("/authentication", "/registration", "/error", "/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "product", "/product/info/{id}", "/product/search").permitAll()
                // Указываем, что все остальные страницы доступны user и admin
                .anyRequest().hasAnyRole("USER", "ADMIN")

                // Указываем, что для всех остальных страниц необходимо вызывать метод authenticated(), который открывает форму аутентификации. использовали до внедрения ролей
                // .anyRequest().authenticated() использовали до внедрения ролей
                // Конец настройки доступа //
                .and() // указываем, что дальше настраивается аутентификация и соединяем ее с настройкой доступа
                // Начало настройки аутентификации //
                // Указываем какой url запрос будет отправляться при заходе на защищенные страницы
                .formLogin().loginPage("/authentication")
                // Указываем на какой адрес будут отправляться данные с формы. Нам уже не нужно будет создавать метод в контроллере и обрабатывать данные с формы. Мы задали url, который используется по умолчанию для обработки формы аутентификации по средствам SS. SS. будет ждать объект с формы аутентиф и затем сверять логин и пароль с данными в БД
                .loginProcessingUrl("/process_login")
                // Указываем на какой url необходимо направить пользователя после успешной аутентификации. Вторым аргументом указывается true чтобы перенаправление шло в любом случае после успешной аутентификации
                .defaultSuccessUrl("/person_account", true)
                // Указываем, куда перенаправлять пользователя при неудачной аутентификации. В запрос будет передан объект error, который будет проверятся на форме и при наличии данного объекта в запросе выводится сообщение "Неправильный логин или пароль".
                .failureUrl("/authentication?error")
                // Добавляем логаут //
                // Указываем по какому url будет осуществлён логаут и куда направит после успешного логаута
                // Всё это реализуется стандартными средствами и методами SS.
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/authentication");
        return http.build();
    }

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    // Для кастомной аутентификации //
    // private final AuthenticationProvider authenticationProvider;
    //
    // public SecurityConfig(AuthenticationProvider authenticationProvider) { //тут выбрасывает ошибку bean, принудительно отключаем ее
    //     this.authenticationProvider = authenticationProvider;
    // }

    // Указываем, что аутентификация приложения будет осуществляться с помощью базовых настроек SS: authenticationManagerBuilder
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    // Это для персонализированных настроек - authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    //добавляем хэширование пароля при аутентификации
        authenticationManagerBuilder.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncode());
    }
}