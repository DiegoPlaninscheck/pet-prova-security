package br.org.sesisenai.clinipet.security.filtro;

import br.org.sesisenai.clinipet.security.model.entity.PessoaJpa;
import br.org.sesisenai.clinipet.security.utils.CookieUtils;
import br.org.sesisenai.clinipet.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class AutenticacaoFiltro extends OncePerRequestFilter {

    private final JwtUtils jwtUtils = new JwtUtils();
    private final CookieUtils cookieUtils = new CookieUtils();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = cookieUtils.getTokenCookie(request);
//            System.out.println(token);
            jwtUtils.validarToken(token);

//            PessoaJpa pessoa = cookieUtils.getUserCookie(request);

//            System.out.println(pessoa);

//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(pessoa.getUsername(),
//                            pessoa.getPassword(), pessoa.getAuthorities());

//            Cookie jwtCookie = cookieUtils.renovarCookie(request, "token");
//            response.addCookie(jwtCookie);
//            Cookie userCookie = cookieUtils.renovarCookie(request, "user");
//            response.addCookie(userCookie);
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
        if (!(url.equals("/api/login/auth") || url.equals("/api/login") || url.equals("/api/logout"))) {
            throw new RuntimeException();
        }
    }
}

