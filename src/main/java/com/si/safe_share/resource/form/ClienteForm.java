package com.si.safe_share.resource.form;

import com.si.safe_share.model.Cliente;
import lombok.Getter;

@Getter
public class ClienteForm {
    private String cpf;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String endereco;
    private String email;
    private String senha;

    public Cliente toModel(ClienteForm clienteForm) {
        Cliente cliente = Cliente.builder()
                .nome(clienteForm.getNome())
                .sobrenome(clienteForm.getSobrenome())
                .cpf(clienteForm.getCpf())
                .email(clienteForm.getEmail())
                .senha(clienteForm.getSenha())
                .telefone(clienteForm.getTelefone())
                .endereco(clienteForm.getEndereco())
                .build();
        return cliente;
    }

    public Cliente toModelUpdated(Cliente clienteAntigo, Cliente clienteNovo) {
        clienteAntigo.setNome(clienteNovo.getNome());
        clienteAntigo.setSobrenome(clienteNovo.getSobrenome());
        clienteAntigo.setEndereco(clienteNovo.getEndereco());
        clienteAntigo.setTelefone(clienteNovo.getTelefone());
        clienteAntigo.setEmail(clienteNovo.getEmail());
        clienteAntigo.setCpf(clienteNovo.getCpf());
        clienteAntigo.setSenha(clienteNovo.getSenha());
        Cliente clienteAtualizado = clienteAntigo;
        return clienteAtualizado;
    }

}
