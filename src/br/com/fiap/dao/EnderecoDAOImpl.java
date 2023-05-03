package br.com.fiap.dao;

import br.com.fiap.jdbc.AppDBManager;
import br.com.fiap.model.Endereco;
import br.com.fiap.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EnderecoDAOImpl implements EnderecoDAO {

    private EnderecoDAOImpl(){}

    private static EnderecoDAOImpl instance;

    public static EnderecoDAOImpl getInstance(){
        if(instance == null){
            instance = new EnderecoDAOImpl();
        }

        return instance;
    }

    private Connection conexao;

    @Override
    public void insert(Endereco endereco) {
        PreparedStatement stmt = null;

        try {
            conexao = AppDBManager.getInstance().getConexao();
            conexao.setAutoCommit(false);
            //auto commit

            String idEndereco = String.valueOf((int)(Math.random() * (50 - 1)) + 1);

            stmt =
                    conexao.prepareStatement(
                            "INSERT INTO t_endereco (id_endereco, cd_cep, nm_rua, ds_logradouro, ds_complemento, nr_numero, t_usuario_id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)"
                    );

            stmt.setString(1,idEndereco);
            stmt.setInt(2, endereco.getCep());
            stmt.setString(3, endereco.getRua());
            stmt.setString(4, endereco.getLogradouro());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getNumero());
            stmt.setString(7, endereco.getIdUsuario());

            stmt.executeUpdate();

            conexao.commit();
        } catch (SQLException e) {
            try {
                conexao.rollback();
            } catch (SQLException er) {
                System.err.println("Não foi possivel fazer o rollback.");
            }

            System.out.println();
            int code = e.getErrorCode();

            switch (code){
                case 1:
                    System.err.println("Não foi possivel inserir no banco, pode haver 2 endereços para o mesmo usuario");
                    System.err.println(e);
                    break;

                default:
                    System.err.println("Ocorreu um erro ao fazer a operação no banco");
                    System.err.println(e);
                    break;


            }

        } finally {
            try {
                conexao.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao fechar as conexões com o banco");
                System.err.println(e);
            }
        }
    }

    @Override
    public List<Endereco> getAll() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Endereco> lista = new ArrayList<Endereco>();

        try {
            conexao = AppDBManager.getInstance().getConexao();

            stmt = conexao.prepareStatement("SELECT * FROM T_ENDERECO");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getString("id_endereco"));
                endereco.setCep(rs.getInt("cd_cep"));
                endereco.setRua(rs.getString("nm_rua"));
                endereco.setLogradouro(rs.getString("ds_logradouro"));
                endereco.setComplemento(rs.getString("ds_complemento"));
                endereco.setNumero(rs.getInt("nr_numero"));
                endereco.setIdUsuario(rs.getString("t_usuario_id_usuario"));

                lista.add(endereco);
            }
        } catch (SQLException e) {
            try {
                conexao.rollback();
            } catch (SQLException er) {
                System.err.println("Não foi possivel fazer o rollback.");
            }
            System.err.println("Ocorreu um erro ao fazer a operação no banco");
            System.err.println(e);
        } finally {
            try {
                conexao.close();
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.err.println("Ocorreu um erro ao fechar as conexões com o banco");
                System.err.println(e);
            }
        }

        return lista;
    }
}
