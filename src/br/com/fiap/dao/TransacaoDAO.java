package br.com.fiap.dao;

import br.com.fiap.model.Transacao;

import java.util.List;

public interface TransacaoDAO {
        void insert(Transacao cartao);

        List<Transacao> getAll();


}
