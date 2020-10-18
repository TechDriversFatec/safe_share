package com.si.safe_share.resource;

import com.si.safe_share.model.Cliente;
import com.si.safe_share.model.ConfiguracaoCompartilhamento;
import com.si.safe_share.repository.ClienteRepository;
import com.si.safe_share.repository.ConfiguracaoCompartilhamentoRepository;
import com.si.safe_share.resource.form.ConfiguracaoCompartilhamentoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ConfiguracaoCompartilhamentoResource {
    @Autowired
    ConfiguracaoCompartilhamentoRepository configuracaoCompartilhamentoRepository;
    
    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/configuracaoCompartilhamento")
    @Transactional
    public ConfiguracaoCompartilhamento salva(
            @RequestBody ConfiguracaoCompartilhamentoForm configuracaoCompartilhamentoForm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidAlgorithmParameterException {
        Optional<Cliente> cliente = clienteRepository.findById(configuracaoCompartilhamentoForm.getCliente());
        ConfiguracaoCompartilhamento configuracaoCompartilhamento = ConfiguracaoCompartilhamento.builder()
                        .cliente(cliente.get())
                        .compartilha_dados_pessoais(configuracaoCompartilhamentoForm.getCompartilha_dados_pessoais())
                        .compartilha_dados_compras(configuracaoCompartilhamentoForm.getCompartilha_dados_compras())
                        .build();

        String encryptionKey           = "ABCDEFGHIJKLMNOP";
        String characterEncoding       = "UTF-8";
        String cipherTransformation    = "AES/CBC/PKCS5PADDING";
        String aesEncryptionAlgorithem = "AES";

        if(configuracaoCompartilhamentoForm.getCompartilha_dados_pessoais() == false){
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);

            byte[] email = cipher.doFinal(cliente.get().getEmail().getBytes("UTF8"));
            byte[] endereco = cipher.doFinal(cliente.get().getEndereco().getBytes("UTF8"));
            byte[] telefone = cipher.doFinal(cliente.get().getTelefone().getBytes("UTF8"));
            byte[] nome = cipher.doFinal(cliente.get().getNome().getBytes("UTF8"));
            byte[] sobrenome = cipher.doFinal(cliente.get().getSobrenome().getBytes("UTF8"));
            byte[] cpf = cipher.doFinal(cliente.get().getCpf().getBytes("UTF8"));

            Base64.Encoder encoder = Base64.getEncoder();

            String emailCriptografado = encoder.encodeToString(email);
            String enderecoCriptografado = encoder.encodeToString(endereco);
            String telefoneCriptografado = encoder.encodeToString(telefone);
            String nomeCriptografado = encoder.encodeToString(nome);
            String sobrenomeCriptografado = encoder.encodeToString(sobrenome);
            String cpfCriptografado = encoder.encodeToString(cpf);

            cliente.get().setEmail(emailCriptografado);
            cliente.get().setCpf(cpfCriptografado);
            cliente.get().setCpf(nomeCriptografado);
            cliente.get().setNome(nomeCriptografado);
            cliente.get().setSobrenome(sobrenomeCriptografado);
            cliente.get().setTelefone(telefoneCriptografado);
            cliente.get().setEndereco(enderecoCriptografado);

            Cliente clienteAtualizado = clienteRepository.save(cliente.get());

        }else if(configuracaoCompartilhamentoForm.getCompartilha_dados_pessoais() == true){
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();

            byte[] emailCriptografado = decoder.decode(cliente.get().getEmail().getBytes("UTF8"));
            byte[] cpfCriptografado = decoder.decode(cliente.get().getCpf().getBytes("UTF8"));
            byte[] nomeCriptografado = decoder.decode(cliente.get().getNome().getBytes("UTF8"));
            byte[] sobrenomeCriptografado = decoder.decode(cliente.get().getSobrenome().getBytes("UTF8"));
            byte[] telefoneCriptografado = decoder.decode(cliente.get().getTelefone().getBytes("UTF8"));
            byte[] enderecoCriptografado = decoder.decode(cliente.get().getEndereco().getBytes("UTF8"));

            String emailDecriptado = new String(cipher.doFinal(emailCriptografado), "UTF-8");
            String cpfDecriptado = new String(cipher.doFinal(cpfCriptografado), "UTF-8");
            String nomeDecriptado = new String(cipher.doFinal(nomeCriptografado), "UTF-8");
            String sobrenomeDecriptado = new String(cipher.doFinal(sobrenomeCriptografado), "UTF-8");
            String telefoneDecriptado = new String(cipher.doFinal(telefoneCriptografado), "UTF-8");
            String enderecoDecriptado = new String(cipher.doFinal(enderecoCriptografado), "UTF-8");

            cliente.get().setEmail(emailDecriptado);
            cliente.get().setCpf(cpfDecriptado);
            cliente.get().setNome(nomeDecriptado);
            cliente.get().setSobrenome(sobrenomeDecriptado);
            cliente.get().setTelefone(telefoneDecriptado);
            cliente.get().setEndereco(enderecoDecriptado);

            clienteRepository.save(cliente.get());
        }

        return configuracaoCompartilhamentoRepository.save(configuracaoCompartilhamento);
    }

    @GetMapping("/configuracaoCompartilhamento/{id}")
    public Optional<ConfiguracaoCompartilhamento> buscaPorId(@PathVariable(value = "id") Integer id) {
        return configuracaoCompartilhamentoRepository.findById(id);
    }

//    @DeleteMapping("/configuracaoCompartilhamento/{id}")
//    public void apagaPorId(@PathVariable(value = "id") Integer id) {
//        Optional<ConfiguracaoCompartilhamento> configuracaoCompartilhamento = configuracaoCompartilhamentoRepository.findById(id);
//        if (configuracaoCompartilhamento.isPresent()) {
//            configuracaoCompartilhamentoRepository.delete(configuracaoCompartilhamento.get());
//        }
//    }

//    @PutMapping("/configuracaoCompartilhamento/{id}")
//    public ConfiguracaoCompartilhamento atualiza(@PathVariable(value = "id") Integer id,
//                                                 @RequestBody ConfiguracaoCompartilhamentoForm configuracaoCompartilhamentoForm) {
//
//        Optional<ConfiguracaoCompartilhamento> configuracaoCompartilhamentoAntigaOpt = configuracaoCompartilhamentoRepository.findById(id);
//        ConfiguracaoCompartilhamento configuracaoCompartilhamentoAntiga = configuracaoCompartilhamentoAntigaOpt.get();
//        ConfiguracaoCompartilhamento configuracaoCompartilhamentoNova = configuracaoCompartilhamentoForm.toModel(configuracaoCompartilhamentoForm);
//        ConfiguracaoCompartilhamento configuracaoCompartilhamentoAtualizada = configuracaoCompartilhamentoForm.toModelUpdated(configuracaoCompartilhamentoAntiga, configuracaoCompartilhamentoNova);
//        return configuracaoCompartilhamentoRepository.save(configuracaoCompartilhamentoAtualizada);
//    }

    @GetMapping("/configuracao-compartilhamentos")
    public List<ConfiguracaoCompartilhamento> lista() {
        return configuracaoCompartilhamentoRepository.findAll();
    }
}
