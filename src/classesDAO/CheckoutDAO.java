package classesDAO;

// importa todas as classes necessaria do sql
import java.sql.*;

import classes.Checkin;
import classes.Checkout;
import classes.Quarto;
import classes.Recepsionista;
import conexaoDB.ConexaoDB;
public class CheckoutDAO{

    Connection dbconn = null;   // variavel de conexao
    PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

    public void save(Quarto quarto, Recepsionista recepsionista, Checkin checkin, Checkout checkout) { // funcao que salva os dados no banco

        // insercao dos dados no bd
        String sql = "insert into tb_checkouts (out_kin_id,out_qua_id,out_rec_cpf) values (?,?,?)";

       

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = ConexaoDB.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, checkout.getIdCheckin());
            pstm.setObject(2, checkout.getIdQuarto());
            pstm.setObject(3, checkout.getCpfCliente());
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
}

