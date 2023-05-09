package br.org.sesisenai.clinipet.security.controller;

import br.org.sesisenai.clinipet.security.model.dto.PessoaDTO;
import br.org.sesisenai.clinipet.security.model.entity.PessoaJpa;
import br.org.sesisenai.clinipet.security.utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

@RestController
@RequestMapping("/login")
@CrossOrigin
@AllArgsConstructor
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;

    private final CookieUtils cookieUtils = new CookieUtils();

    @PostMapping
    public ResponseEntity<Object> autenticacao(@RequestBody PessoaDTO pessoaDTO, HttpServletResponse response) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(pessoaDTO.getEmail(), pessoaDTO.getSenha());

            System.out.println("authenticationToken: " + authenticationToken);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            System.out.println("authentication.isAuthenticated(): " + authentication.isAuthenticated());

            if (authentication.isAuthenticated()) {
                PessoaJpa user = (PessoaJpa) authentication.getPrincipal();
                System.out.println("Pessoa Jpa: " + user.toString());

                Cookie jwtCookie = cookieUtils.gerarTokenCookie(user);
                System.out.println("token cookie: " + jwtCookie);
                response.addCookie(jwtCookie);

//                Cookie userCookie = cookieUtils.gerarUserCookie(user);
//                System.out.println("user cookie: " + userCookie);
//                response.addCookie(userCookie);

                System.out.println("retorno: " + ResponseEntity.ok().build());

                return ResponseEntity.ok().body(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> autenticacao(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie cookie = WebUtils.getCookie(request, "token");

            cookie.setMaxAge(0);

            response.addCookie(cookie);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
