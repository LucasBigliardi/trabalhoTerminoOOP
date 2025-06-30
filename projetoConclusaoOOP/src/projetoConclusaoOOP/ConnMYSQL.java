/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoConclusaoOOP;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author gustavo
 */
public class ConnMYSQL {
    private static Connection conn = null;

    // metodo para recuperar ou criar a conexao
    public static Connection getConnection() {
        try {
            String PORT = "3306";
            String bd = "biblioteca";
            String user = "root";
            String password = "";
            String connectionString = "jdbc:mysql://localhost:"+ PORT +"/"+ bd +"?user="+ user +"&password="+ password;
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // cria a conexao caso ainda nao exista
            if(conn == null) {
                conn = DriverManager.getConnection(connectionString);
            }
            
            return conn;
            
        } catch (Exception ex) {
            System.out.println("Erro: erro ao criar conexao com o banco de dados");
            System.out.println(ex.getMessage());
            return conn;
        }
    }
    
    // metodo para fechar a conexao
    public static boolean closeConnection() {
        try {
            // checa se existe conexao para fechar 
            if(conn != null) {
                getConnection().close();
                System.out.println("Sucesso ao fechar a conexao");
                return true;
            }
            
            return false;
            
        } catch(Exception ex) {
            System.out.println("Erro: ");
            System.out.println(ex);
            return false;
        }
    }
}
