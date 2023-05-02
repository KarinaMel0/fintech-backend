package br.com.fiap.view;

import java.lang.ClassNotFoundException;
import java.sql.*;

public class ConexaoBancoDados {

  public static void main(String[] args) {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      Connection conexao = DriverManager.getConnection(
        "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
        "RM96666",
        "020501"
      );

      System.out.println("Conectado!");

      //            // Statement para SQL estático
      //            Statement stmt = conexao.createStatement();
      //            ResultSet results = stmt.executeQuery("SELECT * FROM T_USUARIO");
      //
      //            while(results.next()){
      //
      //                System.out.println(results.getString("nm_usuario"));
      //            }

      //            // INSERIR DADOS NO BANCO
      //            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO t_usuario (nm_usuario,id_usuario, ds_email, ds_senha, ds_telefone, vl_valor_carteira, dt_data_nascimento) VALUES (?,SQ_UID.NEXTVAL,?,?,?,?, ?)");
      //            stmt.setString(1, "Teste6");
      //            stmt.setString(2, "Teste2@teste2.com");
      //            stmt.setString(3, "T23123");
      //            stmt.setString(4, "11980987303");
      //            stmt.setInt(5, 999999999);
      //
      //            java.sql.Date dataNascimento = new java.sql.Date(new java.util.Date(2001, 05, 02).getTime());
      //            stmt.setDate(6, dataNascimento);
      //
      //            stmt.executeUpdate();
      //

      //            //ATUALIZAR DADOS NO BANCO
      //            PreparedStatement stmt = conexao.prepareStatement("UPDATE T_USUARIO SET vl_valor_carteira = ? WHERE id_usuario = ?" );
      //            stmt.setInt(1, 99);
      //            stmt.setInt(2, 22);
      //
      //            stmt.executeUpdate();

      //            // APAGAR DADO DO BANCO
      //            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM T_USUARIO WHERE id_usuario = ?" );
      //
      //            stmt.setInt(1,20);
      //            stmt.executeUpdate();
      //
      //            stmt.setInt(1,22);
      //            stmt.executeUpdate();
      //
      //            stmt.setInt(1,26);
      //            stmt.executeUpdate();
      //

      //BUSCAR DADOS NO BANCO

      PreparedStatement busca = conexao.prepareStatement(
        "SELECT * FROM T_USUARIO WHERE id_usuario = ?"
      );

      boolean concluiu = busca.execute();

      if (concluiu) {
        System.out.println("concluiu");

        ResultSet rt = busca.getResultSet();

        while (rt.next()) {
          System.out.println("ID: " + rt.getString("id_usuario"));
          System.out.println("NOME: " + rt.getString("nm_usuario"));
          System.out.println(
            "VALOR CARTEIRA: " + rt.getString("vl_valor_carteira")
          );
          System.out.println("---------------");
        }
      }

      //Fecha a conexão
      conexao.close();
    } catch (SQLException e) {
      System.err.print(e);
    } catch (ClassNotFoundException e) {
      System.err.print("O driver não foi encontrado");
    }
  }
}
