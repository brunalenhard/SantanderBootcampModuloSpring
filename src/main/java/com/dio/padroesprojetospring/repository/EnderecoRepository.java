package com.dio.padroesprojetospring.repository;

import com.dio.padroesprojetospring.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, String> {
}
