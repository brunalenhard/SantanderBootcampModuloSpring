package com.dio.padroesprojetospring.controller;

import com.dio.padroesprojetospring.mapper.ClienteMapper;
import com.dio.padroesprojetospring.model.Cliente;
import com.dio.padroesprojetospring.request.ClienteRequest;
import com.dio.padroesprojetospring.response.ClienteResponse;
import com.dio.padroesprojetospring.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
    private ClienteService clienteService;
    private ClienteMapper clienteMapper;

    @PostMapping
    public Cliente save(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.clienteRequestToCliente(clienteRequest);
        return clienteService.save(cliente);
    }

    @GetMapping
    public Page<ClienteResponse> findAll(Pageable pageable) {
        return clienteService.findAll(pageable)
                .map(clienteMapper::clienteToClienteResponse);
    }

    @GetMapping("/{id}")
    public ClienteResponse findById(Long id) {
        Cliente cliente = clienteService.findById(id);
        return clienteMapper.clienteToClienteResponse(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clienteService.delete(id);
    }

    @PutMapping("/{id}")
    public ClienteResponse update(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        Cliente clienteToUpdate = clienteMapper.clienteRequestToCliente(clienteRequest);
        Cliente clienteUpdate = clienteService.update(id, clienteToUpdate);
        return clienteMapper.clienteToClienteResponse(clienteUpdate);
    }
}
