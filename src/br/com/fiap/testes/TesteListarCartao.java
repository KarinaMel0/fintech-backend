package br.com.fiap.testes;

import br.com.fiap.dao.CartaoDAOImpl;
import br.com.fiap.model.Cartao;

import java.util.List;

public class TesteListarCartao {
    public static void main(String[] args) {

        List<Cartao> cartoes = CartaoDAOImpl.getInstance().getAll();

        if (cartoes != null) {
            for (Cartao us : cartoes) {
                System.out.println("Nome: " + us.getNomeCartao());
                System.out.println("ID Cartao:" + us.getIdCartao());
                System.out.println("Valor Cartao:" + us.getValorCartao());
                System.out.println("Tipo do cartao:" + us.getTipoCartao());
                System.out.println("Id Usuario:" + us.getIdUsuario());
                System.out.println("-------------------");
            }
        }
    }
}
