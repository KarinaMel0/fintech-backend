package br.com.fiap.dao;

import br.com.fiap.jdbc.AppDBManager;
import br.com.fiap.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransacaoDAOImpl implements TransacaoDAO{

    private TransacaoDAOImpl() {}

    private static TransacaoDAOImpl instance;

    public static TransacaoDAOImpl getInstance() {
        if (instance == null) {
            instance = new TransacaoDAOImpl();
        }

        return instance;
    }

    private Connection conexao;

    @Override
    public void insert(Transacao transacao) {
        PreparedStatement stmt = null;

        try {
            conexao = AppDBManager.getInstance().getConexao();
            conexao.setAutoCommit(false);
            //auto commit



            stmt =
                    conexao.prepareStatement(
                            "INSERT INTO t_transacao (cd_transacao, vl_valor, ds_descricao, ds_tags, dt_data_transacao, ds_tipo_transacao, t_usuario_id_usuario) VALUES (SQ_ID_TRAN.NEXTVAL, ?, ?, ?, ?, ?, ?)"
                    );

            stmt.setInt(1, transacao.getValor());
            stmt.setString(2, transacao.getDescricao());
            stmt.setString(3, transacao.getTags());
            java.sql.Date dataTransacao = new java.sql.Date(transacao.getDataTransacao().getTimeInMillis());
            stmt.setDate(4, dataTransacao);
            stmt.setString(5, transacao.getTipoTransacao());
            stmt.setString(6, transacao.getIdUsuario());




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
    public List<Transacao> getAll() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Transacao> lista = new ArrayList<Transacao>();

        try {
            conexao = AppDBManager.getInstance().getConexao();

            stmt = conexao.prepareStatement("SELECT * FROM T_TRANSACAO");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Calendar dtTrans = Calendar.getInstance();
                java.sql.Date time = rs.getDate("dt_data_transacao");

                if(time != null){
                    dtTrans.setTimeInMillis(time.getTime());
                }
                Transacao TR = new Transacao();
                TR.setCd_transacao(rs.getString("cd_transacao"));
                TR.setValor(rs.getInt("vl_valor"));
                TR.setDescricao(rs.getString("ds_descricao"));
                TR.setTags(rs.getString("ds_tags"));
                TR.setTipoTransacao(rs.getString("ds_tipo_transacao"));
                TR.setIdUsuario(rs.getString("t_usuario_id_usuario"));
                TR.setDataTransacao(dtTrans);

                lista.add(TR);
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
