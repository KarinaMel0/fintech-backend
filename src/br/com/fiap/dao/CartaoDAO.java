package br.com.fiap.dao;

import br.com.fiap.model.Cartao;

import java.util.List;

public interface CartaoDAO {

    void insert(Cartao cartao);

    List<Cartao> getAll();

}

