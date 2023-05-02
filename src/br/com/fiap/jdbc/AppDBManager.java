package br.com.fiap.jdbc;

import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppDBManager {

  private static AppDBManager instance;

  private AppDBManager() {}

  public static AppDBManager getInstance() {
    if (instance == null) {
      instance = new AppDBManager();
    }

    return instance;
  }

  public Connection getConexao() {
    Connection conexao = null;

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      conexao =
        DriverManager.getConnection(
          "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
          "RM96992",
          "180502"
        );
      conexao.setAutoCommit(false);

      System.out.println("CONECTOU");
    } catch (SQLException error) {
      System.err.print(error);
    } catch (ClassNotFoundException error) {
      System.err.print(error);
    }

    return conexao;
  }
}
