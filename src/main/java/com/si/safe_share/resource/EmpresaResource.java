package com.si.safe_share.resource;

import com.si.safe_share.model.Empresa;
import com.si.safe_share.repository.EmpresaRepository;
import com.si.safe_share.resource.form.EmpresaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/api")
class EmpresaResource {

    //    Injeção de dependência
    @Autowired
    EmpresaRepository empresaRepository;

    @PostMapping("/empresa")
    public Empresa salva(@RequestBody EmpresaForm empresaForm) {
        Empresa empresa = empresaForm.toModel(empresaForm);
        return empresaRepository.save(empresa);
    }

    @GetMapping("/empresa/{id}")
    public Optional<Empresa> buscaPorId(@PathVariable(value="id") Integer id){
        return empresaRepository.findById(id);
    }

    @DeleteMapping("/empresa/{id}")
    public void apagaPorId(@PathVariable(value="id") Integer id){
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()){
            empresaRepository.delete(empresa.get());
        }
    }

    @PutMapping("/empresa/{id}")
    public Empresa atualiza(@PathVariable(value="id") Integer id,
                              @RequestBody EmpresaForm empresaForm){
        Optional<Empresa> empresaAntigaOpt = empresaRepository.findById(id);
        Empresa empresaAntiga = empresaAntigaOpt.get();
        Empresa empresaNova = empresaForm.toModel(empresaForm);
        Empresa empresaAtualizada = empresaForm.toModelUpdated(empresaAntiga, empresaNova);
        return empresaRepository.save(empresaAtualizada);
    }

    @GetMapping("/empresas")
    public List<Empresa> lista(){
        return empresaRepository.findAll();
    }
}