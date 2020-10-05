package com.si.safe_share.resource;

import com.si.safe_share.model.Cliente;
import com.si.safe_share.repository.ClienteRepository;
import com.si.safe_share.resource.form.ClienteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class ClienteResource {
    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/cliente")
    public Cliente salva(@RequestBody ClienteForm clienteForm) {
        Cliente cliente = clienteForm.toModel(clienteForm);
        return clienteRepository.save(cliente);
    }

    @GetMapping("/cliente/{id}")
    public Optional<Cliente> buscaPorId(@PathVariable(value="id") Integer id){
        return clienteRepository.findById(id);
    }

    @DeleteMapping("/cliente/{id}")
    public void apagaPorId(@PathVariable(value="id") Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()){
            clienteRepository.delete(cliente.get());
        }
    }

    @PutMapping("/cliente/{id}")
    public Cliente atualiza(@PathVariable(value="id") Integer id,
                              @RequestBody ClienteForm clienteForm){
        Optional<Cliente> clienteAntigoOpt = clienteRepository.findById(id);
        Cliente clienteAntigo = clienteAntigoOpt.get();
        Cliente clienteNovo = clienteForm.toModel(clienteForm);
        Cliente clienteAtualizado = clienteForm.toModelUpdated(clienteAntigo, clienteNovo);
        return clienteRepository.save(clienteAtualizado);
    }

    @GetMapping("/clientes")
    public List<Cliente> lista(){
        return clienteRepository.findAll();
    }
}
