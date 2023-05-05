package br.org.sesisenai.clinipet.security.utils;

import br.org.sesisenai.clinipet.security.model.entity.PessoaJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CookieUtils {

    private final JwtUtils jwtUtils = new JwtUtils();

    public Cookie gerarTokenCookie(PessoaJpa pessoaJpa) {
        String token = jwtUtils.gerarToken(pessoaJpa);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

    public String getTokenCookie(HttpServletRequest request) throws Exception {
        try {
            Cookie cookie = WebUtils.getCookie(request, "token");
            return cookie.getValue();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Cookie gerarUserCookie(PessoaJpa pessoaJpa) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = URLEncoder.encode(
                    objectMapper.writeValueAsString(pessoaJpa),
                    StandardCharsets.UTF_8);
            Cookie cookie = new Cookie("user", userJson);
            cookie.setPath("/");
            cookie.setMaxAge(1800);
            return cookie;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Cookie renovarCookie(HttpServletRequest request, String nome){
        Cookie cookie = WebUtils.getCookie(request, nome);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

    public PessoaJpa getUserCookie(HttpServletRequest request) throws Exception {
        try {
            Cookie cookie = WebUtils.getCookie(request, "user");
            System.out.println(cookie);
            String jsonUser = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonUser, PessoaJpa.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
