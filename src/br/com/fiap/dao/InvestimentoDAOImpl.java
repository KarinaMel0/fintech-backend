package br.com.fiap.dao;

import br.com.fiap.jdbc.AppDBManager;

import br.com.fiap.model.Investimento;
import br.com.fiap.model.Transacao;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class InvestimentoDAOImpl implements InvestimentoDAO{

    private InvestimentoDAOImpl() {}

    private static InvestimentoDAOImpl instance;

    public static InvestimentoDAOImpl getInstance() {
        if (instance == null) {
            instance = new InvestimentoDAOImpl();
        }

        return instance;
    }
    private Connection conexao;

    @Override
    public void insert(Investimento investimento) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = AppDBManager.getInstance().getConexao();
            conexao.setAutoCommit(false);
            //auto commit

            String randomNumber = String.valueOf((int)(Math.random() * (10000 - 1)) + 1);



            stmt =
                    conexao.prepareStatement(
                            "INSERT INTO t_transacao (cd_transacao, vl_valor, ds_descricao, ds_tags, dt_data_transacao, ds_tipo_transacao, t_usuario_id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)"
                    );

            stmt.setString(1, randomNumber);
            stmt.setInt(2, investimento.getValor());
            stmt.setString(3, investimento.getDescricao());
            stmt.setString(4, investimento.getTags());
            java.sql.Date dataTransacao = new java.sql.Date(investimento.getDataTransacao().getTimeInMillis());
            stmt.setDate(5, dataTransacao);
            stmt.setString(6, investimento.getTipoTransacao());
            stmt.setString(7, investimento.getIdUsuario());




            stmt.executeUpdate();

            stmt =
                    conexao.prepareStatement(
                            "INSERT INTO T_INVESTIMENTO (nm_nome_investimento, t_transacao_cd_transacao) VALUES (?, ?)"
                    );

            stmt.setString(1, investimento.getNomeInvestimento());
            stmt.setString(2, randomNumber);

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
    public List<Investimento> getAll() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Investimento> lista = new ArrayList<Investimento>();

        try {
            conexao = AppDBManager.getInstance().getConexao();

            stmt = conexao.prepareStatement(" \n" +
                    "SELECT * FROM t_investimento LEFT JOIN t_transacao TR ON TR.cd_transacao = t_investimento.t_transacao_cd_transacao");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Calendar dtTrans = Calendar.getInstance();
                java.sql.Date time = rs.getDate("dt_data_transacao");

                if(time != null){
                    dtTrans.setTimeInMillis(time.getTime());
                }
                Investimento invest = new Investimento();
                invest.setCd_transacao(rs.getString("cd_transacao"));
                invest.setValor(rs.getInt("vl_valor"));
                invest.setDescricao(rs.getString("ds_descricao"));
                invest.setTags(rs.getString("ds_tags"));
                invest.setTipoTransacao(rs.getString("ds_tipo_transacao"));
                invest.setIdUsuario(rs.getString("t_transacao_cd_transacao"));
                invest.setDataTransacao(dtTrans);
                invest.setNomeInvestimento(rs.getString("nm_nome_investimento"));

                lista.add(invest);
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
