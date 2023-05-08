package br.org.sesisenai.clinipet.security.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {

    VETERINARIO("VETERINARIO"),
    ATENDENTE("ATENDENTE"),
    CLIENTE("CLIENTE");

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
        return this.descricao;
    }

}
