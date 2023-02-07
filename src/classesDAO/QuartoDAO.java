package classesDAO;

import java.sql.*; // importa todas as classes necessaria do sql

import classes.Quarto;
import classes.Checkout;
import classes.Checkin;
import conexaoDB.ConexaoDB;

public class QuartoDAO {

    Connection dbconn = null; // variavel de conexao
    PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

    public void save(Quarto quarto) { // funcao que salva os dados no banco

        // insercao dos dados no bd
        String sql = "insert into tb_quartos (qua_nome,qua_descricao) values (?,?)";

       

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = ConexaoDB.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setString(1, quarto.getNome());
            pstm.setString(2, quarto.getDescricao());
            pstm.execute();
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (dbconn != null) {
                    dbconn.close();

                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }
    public void QuartoDisp(Quarto quarto,Checkout checkout) {
        String sql ="update tb_quartos set qua_status='Disponivel' where qua_id=?";

        try {
            dbconn = ConexaoDB.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, checkout.getIdQuarto());
            pstm.execute();

        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (dbconn != null) {
                    dbconn.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }
    public void QuartoInd(Quarto quarto,Checkin checkin) {
        String sql ="update tb_quartos set qua_status='Indisponivel' where qua_id=?";

        try {
            dbconn = ConexaoDB.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1,checkin.getIdQuarto() );
            pstm.execute();

        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (dbconn != null) {
                    dbconn.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }
    public void quaDescartado(Quarto quarto ) {
        String sql ="update tb_quartos set qua_status='Descartado' where qua_id=?";

        try {
            dbconn = ConexaoDB.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, quarto.getIdQuarto());
            pstm.execute();
            int countDelQua = pstm.getUpdateCount();

            if (countDelQua == 0) {
                System.out.println(" O quarto não existe!!");
            } else {
                System.out.println("quarto descartado com sucesso!!");
            }

        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (dbconn != null) {
                    dbconn.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }


    public void editQuarto(Quarto quarto) {
        String sql = "update tb_quartos set qua_nome = ? ,qua_descricao = ?, qua_status = 'Disponivel' where qua_id = ?";

        try {
            dbconn = ConexaoDB.createConnectionToMySQL();
            //inserir os dados
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
           
            pstm.setString(1, quarto.getNome());
            pstm.setString(2, quarto.getDescricao());
            pstm.setObject(3, quarto.getIdQuarto());
            pstm.executeUpdate();
            int linhasBD = pstm.getUpdateCount();

            // verifica se o livro que esta sendo editado existe
            if (linhasBD == 0) {
                System.out.println(" Esse quarto não existe;");
            } else {
                System.out.println("Quarto editado");
            }
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();

                }
                if (dbconn != null) {
                    dbconn.close();
                }
            } catch (Exception error) {

                error.printStackTrace();
            }
        }
    }
}
