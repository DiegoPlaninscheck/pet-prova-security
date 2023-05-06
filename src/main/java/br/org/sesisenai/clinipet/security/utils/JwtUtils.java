package br.org.sesisenai.clinipet.security.utils;

import br.org.sesisenai.clinipet.security.model.entity.PessoaJpa;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtils {

    private final String senhaForte = "c127a7b6adb013a5ff879ae71afa62afa4b4ceb72afaa54711dbcde67b6dc325";

    public String gerarToken(PessoaJpa pessoaJpa) {
        return Jwts.builder().setIssuer("CliniPet")
                .setSubject(pessoaJpa.getUsername())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 1800000))
                .signWith(SignatureAlgorithm.HS256, senhaForte).compact();
    }

    public Boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(senhaForte).parseClaimsJws(token);
            System.out.println("Passou");
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String getPessoa(String token){
        return Jwts.parser().setSigningKey(senhaForte).parseClaimsJws(token).getBody().getSubject();
    }
}
