package com.si.safe_share.resource;

import com.si.safe_share.model.Produto;
import com.si.safe_share.repository.ProdutoRepository;
import com.si.safe_share.resource.form.ProdutoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class ProdutoResource {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/produto")
    public Produto salva(@RequestBody ProdutoForm produtoForm) {
        Produto produto = produtoForm.toModel(produtoForm);
        return produtoRepository.save(produto);
    }

    @GetMapping("/produto/{id}")
    public Optional<Produto> buscaPorId(@PathVariable(value="id") Integer id){
        return produtoRepository.findById(id);
    }

    @DeleteMapping("/produto/{id}")
    public void apagaPorId(@PathVariable(value="id") Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()){
            produtoRepository.delete(produto.get());
        }
    }

    @PutMapping("/produto/{id}")
    public Produto atualiza(@PathVariable(value="id") Integer id,
                            @RequestBody ProdutoForm produtoform){
        Optional<Produto> produtoAntigoOpt = produtoRepository.findById(id);
        Produto produtoAntigo = produtoAntigoOpt.get();
        Produto produtoNovo = produtoform.toModel(produtoform);
        Produto produtoAtualizado = produtoform.toModelUpdated(produtoAntigo, produtoNovo);
        return produtoRepository.save(produtoAtualizado);
    }

    @GetMapping("/produtos")
    public List<Produto> lista(){
        return produtoRepository.findAll();
    }
}
