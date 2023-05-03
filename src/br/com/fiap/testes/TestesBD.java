package br.com.fiap.testes;

import br.com.fiap.dao.CartaoDAOImpl;
import br.com.fiap.dao.EnderecoDAOImpl;
import br.com.fiap.dao.InvestimentoDAOImpl;
import br.com.fiap.dao.TransacaoDAOImpl;
import br.com.fiap.model.Cartao;
import br.com.fiap.model.Endereco;
import br.com.fiap.model.Investimento;
import br.com.fiap.model.Transacao;

import java.util.Calendar;
import java.util.List;

public class TestesBD {
    public static void main(String[] args) {

        //INSERTS

        Calendar data = Calendar.getInstance();
        data.set(2023,04,02);

        Cartao card = new Cartao("0","Nome cartao teste2", "debito", 500, "41");

        CartaoDAOImpl.getInstance().insert(card);

        Transacao tr = new Transacao(546456, "gastrogran", "shop2", data, "Gasto", "41", "0");

        TransacaoDAOImpl.getInstance().insert(tr);

        Investimento inv = new Investimento(5000, "Investimento", "Investimento", data, "Investimento", "41", "0", "INVESTIMENTO TESTE");

        InvestimentoDAOImpl.getInstance().insert(inv);

        //Não pode cadastrar mais de um endereço por usuario.
        Endereco address = new Endereco("0",814225,"Rua manuel", "Rua", "e41",520, "11");

        EnderecoDAOImpl.getInstance().insert(address);



        //GETALL


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





        List<Transacao> trList = TransacaoDAOImpl.getInstance().getAll();

        if(trList != null){
            for(Transacao trItem : trList){
                System.out.println("Descricao: " + trItem.getDescricao());
                System.out.println("Codigo Transacao:" + trItem.getCd_transacao());
                System.out.println("Valor Transacao:" + trItem.getValor());
                System.out.println("Tipo de Transacao:" + trItem.getTipoTransacao());
                System.out.println("Tags:" + trItem.getTags());
                System.out.println("Data Transacao:" + trItem.getDataTransacao().getTime());
                System.out.println("Id Usuario:" + trItem.getIdUsuario());
                System.out.println("-------------------");
            }
        }


        List<Investimento> invList = InvestimentoDAOImpl.getInstance().getAll();

        if(invList != null){
            for(Investimento invItem : invList){
                System.out.println("Descricao: " + invItem.getDescricao());
                System.out.println("Nome Investimento: " + invItem.getNomeInvestimento());
                System.out.println("Codigo Transacao:" + invItem.getCd_transacao());
                System.out.println("Valor Transacao:" + invItem.getValor());
                System.out.println("Tipo de Transacao:" + invItem.getTipoTransacao());
                System.out.println("Tags:" + invItem.getTags());
                System.out.println("Data Transacao:" + invItem.getDataTransacao().getTime());
                System.out.println("Id Usuario:" + invItem.getIdUsuario());
                System.out.println("-------------------");
            }
        }




        List<Endereco> endList = EnderecoDAOImpl.getInstance().getAll();

        if(endList != null){
            for(Endereco endItem : endList){
                System.out.println("CEP: " + endItem.getCep());
                System.out.println("Nome da rua: " + endItem.getRua());
                System.out.println("Logradouro:" + endItem.getLogradouro());
                System.out.println("Complemento:" + endItem.getComplemento());
                System.out.println("Numero: " + endItem.getNumero());
                System.out.println("ID endereco:" + endItem.getIdEndereco());
                System.out.println("Id Usuario:" + endItem.getIdUsuario());
                System.out.println("-------------------");
            }
        }


    }
}
