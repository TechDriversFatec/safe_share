package com.si.safe_share.resource;

import com.si.safe_share.model.Carrinho;
import com.si.safe_share.model.Cliente;
import com.si.safe_share.model.Pedido;
import com.si.safe_share.repository.CarrinhoRepository;
import com.si.safe_share.repository.ClienteRepository;
import com.si.safe_share.repository.PedidoRepository;
import com.si.safe_share.resource.form.CarrinhoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class RelatorioResource {

    @Autowired
    CarrinhoRepository carrinhoRepository;
//
//    @GetMapping("/relatorio/{id}")
//    public List<Carrinho> buscaPorId(@PathVariable(value = "id") Integer id) {
//        return carrinhoRepository.findByCliente_AndProdutos(id);
//    }

}
