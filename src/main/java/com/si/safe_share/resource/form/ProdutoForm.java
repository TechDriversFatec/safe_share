package com.si.safe_share.resource.form;

import com.si.safe_share.model.Categoria;
import com.si.safe_share.model.Produto;
import com.si.safe_share.repository.CategoriaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
public class ProdutoForm {
    private String descricao;
    private BigDecimal valor;
    private Integer categoria;

    @Autowired
    CategoriaRepository categoriaRepository;

    public Produto toModel(ProdutoForm produtoForm) {
        final Optional<Categoria> categoriaOpt = categoriaRepository.findById(produtoForm.getCategoria());
        Categoria categoria = categoriaOpt.get();
        Produto produto = Produto.builder()
                .descricao(produtoForm.getDescricao())
                .valor(produtoForm.valor)
                .categoria(categoria)
                .build();
        return produto;
    }

    public Produto toModelUpdated(Produto produtoAntigo, Produto produtoNovo) {
        produtoAntigo.setDescricao(produtoNovo.getDescricao());
        produtoAntigo.setValor(produtoNovo.getValor());
        produtoAntigo.setCategoria(produtoNovo.getCategoria());
        Produto produtoAtualizado = new Produto();
        produtoAtualizado = produtoAntigo;
        return produtoAntigo;
    }

}
