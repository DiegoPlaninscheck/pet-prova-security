package br.org.sesisenai.clinipet.security.filter;

import br.org.sesisenai.clinipet.security.service.JpaService;
import br.org.sesisenai.clinipet.security.utils.CookieUtils;
import br.org.sesisenai.clinipet.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class AutenticacaoFiltro extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    private CookieUtils cookieUtils;

    private JpaService jpaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        try {
            String token = cookieUtils.getTokenCookie(request);
            System.out.println("token: " + token);
            Boolean valido = jwtUtils.validarToken(token);

            if(valido){
                UserDetails pessoa = jpaService.loadUserByUsername(jwtUtils.getPessoa(token));

                System.out.println("pessoa da JpaService: " + pessoa.toString());

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(pessoa.getUsername(), null, pessoa.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            Cookie jwtCookie = cookieUtils.renovarCookie(request, "token");
            response.addCookie(jwtCookie);
            Cookie userCookie = cookieUtils.renovarCookie(request, "user");
            response.addCookie(userCookie);
        }catch (Exception e) {
            e.printStackTrace();
            try {
                validarUrl(request.getRequestURI());
            } catch (Exception exception) {
                exception.printStackTrace();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private void validarUrl(String url) throws Exception {
        System.out.println(url);
        if (!(url.equals("/api/login/auth") || url.equals("/api/login") || url.equals("/api/logout")
                || url.equals("/logout"))) {
            throw new RuntimeException();
        }
    }
}

