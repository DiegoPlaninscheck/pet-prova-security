package br.org.sesisenai.clinipet.security.model.entity;

import br.org.sesisenai.clinipet.model.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaJpa implements UserDetails {

//    private Pessoa pessoa;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(this.getPessoa().getClass().getSimpleName()));
//
//        return grantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return pessoa.getSenha();
//    }
//
//    @Override
//    public String getUsername() {
//        return pessoa.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

    private Pessoa pessoa;

    private List<Perfil> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private String password;

    private String username;

    public PessoaJpa(Pessoa pessoa){
        this.pessoa = pessoa;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.password = pessoa.getSenha();
        this.username = pessoa.getEmail();
        this.authorities = new ArrayList<>();
        this.authorities.add(Perfil.perfilOf(pessoa.getClass().getSimpleName()));
    }

}
