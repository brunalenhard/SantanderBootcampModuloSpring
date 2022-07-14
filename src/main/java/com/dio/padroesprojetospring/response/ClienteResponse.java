package com.dio.padroesprojetospring.response;

import com.dio.padroesprojetospring.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private String nome;
    private Long id;
    private Endereco endereco;
}
