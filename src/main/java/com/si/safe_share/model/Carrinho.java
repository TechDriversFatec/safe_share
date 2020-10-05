package com.si.safe_share.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Carrinho {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<Produto> produtos;

    @ManyToOne
    private Cliente cliente;

    public Carrinho(Set<Produto> produtos, Cliente cliente) {
        this.produtos = produtos;
        this.cliente = cliente;
    }
}