package br.org.sesisenai.clinipet.security.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {

    VETERINARIO("Veterinario"),
    ATENDENTE("Atendente"),
    CLIENTE("Cliente");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public static Perfil perfilOf(String simpleName) {
        return switch (simpleName) {
            case "Veterinario" -> VETERINARIO;
            case "Atendente" -> ATENDENTE;
            case "Cliente" -> CLIENTE;
            default -> null;
        };
    }

    @Override
    public String getAuthority() {
        return this.name();
    }

}
