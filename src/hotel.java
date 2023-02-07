import java.math.BigInteger;
import java.util.Scanner;

import classes.Checkin;
import classes.Checkout;
import classes.Quarto;
import classes.Recepsionista;
import classesDAO.CkeckinDAO;
import classesDAO.CheckoutDAO;
import classesDAO.QuartoDAO;
import classesDAO.RecepsionistaDAO;
public class hotel {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        Quarto qua = new Quarto();
        Recepsionista rec = new Recepsionista();
        QuartoDAO quaDAO = new QuartoDAO();
        RecepsionistaDAO recDAO = new RecepsionistaDAO();
        Checkin kin = new Checkin();
        CkeckinDAO  kinDAO = new CkeckinDAO();
        Checkout out = new Checkout();
        CheckoutDAO  outDAO = new CheckoutDAO();

    System.out.println("Qual ação voce deseja realizar? ");
    System.out.println("1- Cadastrar Quarto");
    System.out.println("2- Cadastrar Cliente");
    System.out.println("3- Realizar chekin");
    System.out.println("4- Realizar checkout");
    System.out.println("5- Deletar Quarto");
    System.out.println("6- Deletar usuario");
    int aux = sc.nextInt();

    if (aux == 1) {

    System.out.println("Digite o Nome do quarto");
      String nome = sc.next();
      System.out.println("Digite a descrição");
      String desc = sc.next();

      qua.setNome(nome);
      qua.setDescricao(desc);
      quaDAO.save(qua); // salva as informacoes no banco
        
    } else  if (aux == 2) {

        System.out.println("Digite o Nome do Cliente");
          String nome = sc.next();
      
     
    
          System.out.println("Digite o cpf");
          BigInteger cpf = sc.nextBigInteger();
    
          rec.setNome(nome);
          rec.setCpf(cpf);
          recDAO.save(rec); // salva as informacoes no banco
            
        } else  if (aux == 3) {

            System.out.println("Digite o id do quarto");
            int id = sc.nextInt();
   

              System.out.println("Digite o cpf do cliene");
              BigInteger cpf = sc.nextBigInteger();
        
              kin.setCpfCliente(cpf);
              kin.setIdQuarto(id);
              kinDAO.save(qua, rec, kin); // salva as informacoes no banco
              quaDAO.QuartoInd(qua, kin);
                
            }  else  if (aux == 4) {

                System.out.println("Digite o id do checkin");
                  int kinId = sc.nextInt();

                System.out.println("Digite o id do quarto");
                int id = sc.nextInt();
       
    
                  System.out.println("Digite o cpf do cliene");
                  BigInteger cpf = sc.nextBigInteger();

                  out.setIdCheckin(kinId);
                  out.setCpfCliente(cpf);
                  out.setIdQuarto(id);
                  outDAO.save(qua, rec, kin,out); // salva as informacoes no banco
                  quaDAO.QuartoDisp(qua, out);
                    
                }  else  if (aux == 5) {

                  System.out.println("Digite o id do quarto");
                    int delQua = sc.nextInt();

                  qua.setIdQuarto(delQua);
                  quaDAO.quaDescartado(qua);
                      
                  } else  if (aux == 6) {

                    System.out.println("Digite o cpf do cliente");
                      BigInteger delRec = sc.nextBigInteger();
  
                  rec.setCpf(delRec);
                  recDAO.recDescartado(rec);
                        
                    } else if (aux == 7){

                    System.out.println("Digite o id do quarto para editar");
                    BigInteger isbnold = sc.nextBigInteger();
                    sc.nextLine();
        
                    System.out.println("Digite nome atual do quarto");
                    String nome = sc.nextLine();
        
                    System.out.println("Digite a descrição atual do quarto");
                    String desc = sc.nextLine();
        
                    qua.setNome(nome);
                    qua.setDescricao(desc);
                    quaDAO.quaDAO(editQuarto());
                    }
    }
}
