package br.org.sesisenai.clinipet.security.config;

import br.org.sesisenai.clinipet.security.filter.AutenticacaoFiltro;
import br.org.sesisenai.clinipet.security.service.JpaService;
import br.org.sesisenai.clinipet.security.utils.CookieUtils;
import br.org.sesisenai.clinipet.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class AutenticacaoConfig {

    @Autowired
    private JpaService jpaService;

    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jpaService).passwordEncoder(new BCryptPasswordEncoder());
//        authenticationManagerBuilder.userDetailsService(jpaService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("httpSecurity: " + httpSecurity);
        httpSecurity.authorizeRequests().requestMatchers("/login/**", "/login/auth/**", "/logout/**", "/logout").permitAll()

                //ROTAS LIVRES
                .requestMatchers(HttpMethod.GET, "/servico", "/servico/**", "/veterinario").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/login", "/login", "/logout").permitAll()

                //ANIMAIS
                .requestMatchers(HttpMethod.GET, "/animal/**").hasAnyAuthority("ATENDENTE", "VETERINARIO")
                .requestMatchers(HttpMethod.POST, "/animal").hasAuthority("ATENDENTE")
                .requestMatchers(HttpMethod.PUT, "/animal/**").hasAnyAuthority("ATENDENTE", "VETERINARIO")
                .requestMatchers(HttpMethod.DELETE, "/animal").hasAnyAuthority("ATENDENTE", "VETERINARIO")

                //CLIENTES
                .requestMatchers(HttpMethod.GET, "/cliente/**", "/cliente").hasAnyAuthority("ATENDENTE", "VETERINARIO")
                .requestMatchers(HttpMethod.POST, "/cliente").hasAuthority("ATENDENTE")
                .requestMatchers(HttpMethod.PUT, "/cliente/**", "/cliente").hasAnyAuthority("ATENDENTE", "VETERINARIO")
                .requestMatchers(HttpMethod.DELETE, "/cliente").hasAnyAuthority("ATENDENTE", "VETERINARIO")

                //AGENDAS
                .requestMatchers(HttpMethod.GET, "/agenda", "/agenda/**").hasAnyAuthority("ATENDENTE", "VETERINARIO")
                .requestMatchers(HttpMethod.POST, "/agenda").hasAuthority("ATENDENTE")
                .requestMatchers(HttpMethod.PUT, "/agenda").hasAnyAuthority("ATENDENTE", "VETERINARIO")
                .requestMatchers(HttpMethod.DELETE, "/agenda").hasAnyAuthority("ATENDENTE", "VETERINARIO")

                //ATENDENTES
                .requestMatchers(HttpMethod.GET, "/atendente").hasAuthority("ATENDENTE")
                .requestMatchers(HttpMethod.POST, "/atendente").hasAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.PUT, "/atendente").hasAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.DELETE, "/atendente").hasAuthority("VETERINARIO")

                //PRONTUARIOS
                .requestMatchers(HttpMethod.POST, "/pontuario").hasAnyAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.PUT, "/pontuario").hasAnyAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.DELETE, "/pontuario").hasAnyAuthority("VETERINARIO")

                //SERVICOS
                .requestMatchers(HttpMethod.POST, "/servico").hasAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.PUT, "/servico").hasAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.DELETE, "/servico").hasAuthority("VETERINARIO")

                //VETERINARIOS
                .requestMatchers(HttpMethod.POST, "/veterinario").hasAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.PUT, "/veterinario", "/veterinario/**").hasAuthority("VETERINARIO")
                .requestMatchers(HttpMethod.DELETE, "/veterinario").hasAuthority("VETERINARIO")

                .anyRequest().authenticated();

        httpSecurity.csrf().disable();

        httpSecurity.cors().configurationSource(corsConfiguration());

        httpSecurity.logout().logoutUrl("/logout").deleteCookies("token", "user").permitAll();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(new AutenticacaoFiltro(new JwtUtils(), new CookieUtils(), jpaService), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8081"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
