package com.dio.padroesprojetospring.service;

import com.dio.padroesprojetospring.model.Cliente;
import com.dio.padroesprojetospring.model.Endereco;
import com.dio.padroesprojetospring.repository.ClienteRepository;
import com.dio.padroesprojetospring.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Data
public class ClienteService {
    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;
    private CepService cepService;

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public Cliente save(Cliente cliente) {
        salvarClienteComCep(cliente);
        return cliente;
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente clienteById = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        salvarClienteComCep(cliente);
        return cliente;
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco()
                .getCep();
        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = cepService.consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
