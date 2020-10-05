package com.si.safe_share.resource;

import com.si.safe_share.model.LogCompartilhamento;
import com.si.safe_share.repository.LogCompartilhamentoRepository;
import com.si.safe_share.resource.form.LogCompartilhamentoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class LogCompartilhamentoResource {
    @Autowired
    LogCompartilhamentoRepository logCompartilhamentoRepository;

    @PostMapping("/log-compartilhamento")
    public LogCompartilhamento salva(@RequestBody LogCompartilhamentoForm logCompartilhamentoForm) {
        LogCompartilhamento logCompartilhamento = logCompartilhamentoForm.toModel(logCompartilhamentoForm);
        return logCompartilhamentoRepository.save(logCompartilhamento);
    }

    @GetMapping("/log-compartilhamento/{id}")
    public Optional<LogCompartilhamento> buscaPorId(@PathVariable(value="id") Integer id){
        return logCompartilhamentoRepository.findById(id);
    }

    @DeleteMapping("/log-compartilhamento/{id}")
    public void apagaPorId(@PathVariable(value="id") Integer id){
        Optional<LogCompartilhamento> logCompartilhamento = logCompartilhamentoRepository.findById(id);
        if (logCompartilhamento.isPresent()){
            logCompartilhamentoRepository.delete(logCompartilhamento.get());
        }
    }

    @PutMapping("/log-compartilhamento/{id}")
    public LogCompartilhamento atualiza(@PathVariable(value="id") Integer id,
                                        @RequestBody LogCompartilhamentoForm logCompartilhamentoForm){

        Optional<LogCompartilhamento> logCompartilhamentoAntigoOpt = logCompartilhamentoRepository.findById(id);
        LogCompartilhamento logCompartilhamentoAntigo = new LogCompartilhamento();
        LogCompartilhamento logCompartilhamentoNovo = logCompartilhamentoForm.toModel(logCompartilhamentoForm);

        logCompartilhamentoAntigo.setCliente(logCompartilhamentoNovo.getCliente());
        logCompartilhamentoAntigo.setEmpresa(logCompartilhamentoNovo.getEmpresa());
        logCompartilhamentoAntigo.setDadoCompartilhado(logCompartilhamentoNovo.getDadoCompartilhado());
        logCompartilhamentoAntigo.setDataDeInicio(logCompartilhamentoNovo.getDataDeInicio());
        logCompartilhamentoAntigo.setDataFinal(logCompartilhamentoNovo.getDataFinal());

        LogCompartilhamento logCompartilhamentoAtualizado = logCompartilhamentoAntigo;
        return logCompartilhamentoRepository.save(logCompartilhamentoAtualizado);
    }

    @GetMapping("/log-compartilhamentos")
    public List<LogCompartilhamento> lista(){
        return logCompartilhamentoRepository.findAll();
    }
}
