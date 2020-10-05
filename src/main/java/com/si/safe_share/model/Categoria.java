package com.si.safe_share.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Categoria {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String descricao;

    public Categoria(String descricao) {
        this.descricao = descricao;
    }
}


