package classesDAO;

import java.sql.*; // importa todas as classes necessaria do sql

import classes.Recepsionista;
import conexaoDB.ConexaoDB;

public class RecepsionistaDAO {

    Connection dbconn = null; // variavel de conexao
    PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

    public void save(Recepsionista recepsionista) { // funcao que salva os dados no banco

        // insercao dos dados no bd
        String sql = "insert into tb_recepcionistas (rec_nome,rec_cpf) values (?,?)";

       

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = ConexaoDB.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setString(1, recepsionista.getNome());
            pstm.setObject(2, recepsionista.getCpf());
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
    public void recDescartado(Recepsionista recepsionista) {
        String sql ="update tb_recepcionistas set rec_status='Descartado' where rec_cpf=?";

        try {
            dbconn = ConexaoDB.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, recepsionista.getCpf());
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

    public void editHospede(Recepsionista recepsionista) {
        String sql = "update tb_recepcionistas set rec_nome = ? , rec_status = 'Ativo' where rec_cpf= ?";

        try {
            dbconn = ConexaoDB.createConnectionToMySQL();
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setString(1, recepsionista.getNome());
            pstm.setObject(2, recepsionista.getCpf());
            pstm.executeUpdate();
            int linhasBD = pstm.getUpdateCount();

            if (linhasBD == 0) {
                System.out.println(" Esse hospede não existe");
            } else {
                System.out.println("Hóspede editado");
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
