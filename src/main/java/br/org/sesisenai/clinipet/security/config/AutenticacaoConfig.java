package br.org.sesisenai.clinipet.security.config;

import br.org.sesisenai.clinipet.security.filter.AutenticacaoFiltro;
import br.org.sesisenai.clinipet.security.service.JpaService;
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
        httpSecurity.authorizeRequests().requestMatchers("/login/**", "/login/auth/**", "/logout/**").permitAll()

                //Atendente
                .requestMatchers(HttpMethod.GET, "/animal", "/cliente",
                        "/agenda", "/veterinario", "/atendente", "/prontuarios", "/servicos").hasAuthority("Atendente")
                .requestMatchers(HttpMethod.POST, "/animal", "/cliente", "/agenda").hasAuthority("Atendente")
                .requestMatchers(HttpMethod.PUT, "/animal", "/cliente", "/agenda").hasAuthority("Atendente")
                .requestMatchers(HttpMethod.DELETE, "/animal", "/cliente", "/agenda").hasAuthority("Atendente")

                //Veterinario
                .requestMatchers(HttpMethod.GET, "/pronturio,", "/servico/**", "/veterinario",
                        "/atendente", "/animal", "/cliente", "/agenda").hasAuthority("Veterinario")
                .requestMatchers(HttpMethod.POST, "/prontuario,", "/servico", "/veterinario", "/atendente").hasAuthority("Veterinario")
                .requestMatchers(HttpMethod.PUT, "/prontuario,", "/servico", "/veterinario", "/atendente",
                        "/animal", "/cliente", "/agenda").hasAuthority("Veterinario")
                .requestMatchers(HttpMethod.DELETE, "/prontuario,", "/servico", "/veterinario", "/atendente",
                        "/animal", "/cliente", "/agenda").hasAuthority("Veterinario")

                //Cliente
                .requestMatchers(HttpMethod.GET, "/prontuario,", "/servico", "/veterinario", "/cliente", "/agenda").hasAuthority("Cliente")

                .requestMatchers(HttpMethod.GET, "/servico", "/veterinario").hasAnyAuthority("Veterinario", "Cliente", "Atendente")
                .anyRequest().authenticated();

        httpSecurity.csrf().disable();

        httpSecurity.cors().configurationSource(corsConfiguration());

        httpSecurity.logout().deleteCookies("token").permitAll();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(new AutenticacaoFiltro(), UsernamePasswordAuthenticationFilter.class);

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
