package com.si.safe_share.resource;

import com.si.safe_share.model.Pedido;
import com.si.safe_share.repository.PedidoRepository;
import com.si.safe_share.resource.form.PedidoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class PedidoResource {
    @Autowired
    PedidoRepository pedidoRepository;

    @PostMapping("/pedido")
    public Pedido salva(@RequestBody PedidoForm pedidoForm) {
        Pedido pedido = pedidoForm.toModel(pedidoForm);
        return pedidoRepository.save(pedido);
    }

    @GetMapping("/pedido/{id}")
    public Optional<Pedido> buscaPorId(@PathVariable(value="id") Integer id){
        return pedidoRepository.findById(id);
    }

    @DeleteMapping("/pedido/{id}")
    public void apagaPorId(@PathVariable(value="id") Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()){
            pedidoRepository.delete(pedido.get());
        }
    }

    @PutMapping("/pedido/{id}")
    public Pedido atualiza(@PathVariable(value="id") Integer id,
                              @RequestBody PedidoForm pedidoForm){
        Optional<Pedido> pedidoAntigoOpt = pedidoRepository.findById(id);
        Pedido pedidoAntigo = pedidoAntigoOpt.get();
        Pedido pedidoNovo = pedidoForm.toModel(pedidoForm);

        Pedido pedidoAtualizado = pedidoForm.toModelUpdated(pedidoAntigo, pedidoNovo);

        return pedidoRepository.save(pedidoAtualizado);

    }

    @GetMapping("/pedidos")
    public List<Pedido> lista(){
        return pedidoRepository.findAll();
    }
}
