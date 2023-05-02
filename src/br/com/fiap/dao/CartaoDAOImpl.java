package br.com.fiap.dao;

import br.com.fiap.jdbc.AppDBManager;
import br.com.fiap.model.Cartao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAOImpl implements CartaoDAO {

  private CartaoDAOImpl() {}

  private static CartaoDAOImpl instance;

  public static CartaoDAOImpl getInstance() {
    if (instance == null) {
      instance = new CartaoDAOImpl();
    }

    return instance;
  }

  private Connection conexao;

  @Override
  public void insert(Cartao cartao) {
    PreparedStatement stmt = null;

    try {
      conexao = AppDBManager.getInstance().getConexao();
      conexao.setAutoCommit(false);
      //auto commit
      stmt =
        conexao.prepareStatement(
          "INSERT INTO t_cartao (id_cartao, nm_nome_cartao, ds_tipo_cartao, vl_valor_cartao, t_usuario_id_usuario) VALUES (SQ_CID.NEXTVAL, ?, ?, ?, ?)"
        );

      stmt.setString(1, cartao.getNomeCartao());
      stmt.setString(2, cartao.getTipoCartao());
      stmt.setInt(3, cartao.getValorCartao());
      stmt.setString(4, cartao.getIdUsuario());


      stmt.executeUpdate();

      conexao.commit();
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
      } catch (SQLException e) {
        System.err.println("Ocorreu um erro ao fechar as conexões com o banco");
        System.err.println(e);
      }
    }
  }

  @Override
  public List<Cartao> getAll() {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Cartao> lista = new ArrayList<Cartao>();

    try {
      conexao = AppDBManager.getInstance().getConexao();


      stmt = conexao.prepareStatement("SELECT * FROM T_CARTAO");


      rs = stmt.executeQuery();
      while (rs.next()) {
        lista.add(
                new Cartao(
                        rs.getString("id_cartao"),
                        rs.getString("nm_nome_cartao"),
                        rs.getString("ds_tipo_cartao"),
                        rs.getInt("vl_valor_cartao"),
                        rs.getString("t_usuario_id_usuario")
                ));
      }

    } catch (SQLException e) {
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
