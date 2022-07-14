package com.dio.padroesprojetospring.mapper;

import com.dio.padroesprojetospring.model.Cliente;
import com.dio.padroesprojetospring.request.ClienteRequest;
import com.dio.padroesprojetospring.response.ClienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponse clienteToClienteResponse(Cliente cliente);

    @Mapping(source = "cep", target = "endereco.cep")
    Cliente clienteRequestToCliente(ClienteRequest clienteRequest);

}
