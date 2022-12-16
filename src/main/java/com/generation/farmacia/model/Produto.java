package com.generation.farmacia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O atributo tipo é obrigató! ")
    @Size(min = 2, max = 100, message = "O Atributo nome deve conter no mínimo 05 e no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O atributo tipo é obrigató! ")
    @Size(min = 10, max = 500, message = "O Atributo descrição deve conter no mínimo 05 e no máximo 100 caracteres")
    private String  descricao;

    @NotNull
    private BigDecimal preco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
