package br.org.sesisenai.clinipet.security.controller;

import br.org.sesisenai.clinipet.security.model.dto.PessoaDTO;
import br.org.sesisenai.clinipet.security.model.entity.PessoaJpa;
import br.org.sesisenai.clinipet.security.utils.CookieUtils;
import br.org.sesisenai.clinipet.security.utils.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            if (authentication.isAuthenticated()) {
                PessoaJpa user = (PessoaJpa) authentication.getPrincipal();

                Cookie jwtCookie = cookieUtils.gerarTokenCookie(user);
                System.out.println(jwtCookie);
                response.addCookie(jwtCookie);

                Cookie userCookie = cookieUtils.gerarUserCookie(user);
                System.out.println(userCookie);
                response.addCookie(userCookie);

                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
