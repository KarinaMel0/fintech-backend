package br.com.fiap.dao;

import br.com.fiap.model.Endereco;

import java.util.List;

public interface EnderecoDAO {

    void insert(Endereco endereco);

    List<Endereco> getAll();

}
