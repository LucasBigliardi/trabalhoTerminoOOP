/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoConclusaoOOP;

import java.sql.Connection;
import java.sql.DriverManager;


// classe de conexao com o mysql, segue o padrao singleton (apenas uma conexao)
public class ConnMYSQL {
    // conexao unica presente na classe
    private static Connection conn = null;

    // metodo para recuperar ou criar a conexao
    public static Connection getConnection() {
        
        // informacoes de acesso do banco de dados
        String PORT = "3306";
        String bd = "biblioteca";
        String user = "root";
        String password = "";
        
        // string de criacao da conexao
        String connectionString = "jdbc:mysql://localhost:"+ PORT +"/"+ bd +"?user="+ user +"&password="+ password;
        
        // tentando fazer a conexao
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // cria a conexao caso ainda nao exista
            if(conn == null) {
                conn = DriverManager.getConnection(connectionString);
            }
            
            // retorna a conexao
            return conn;
            
        } catch (Exception ex) {
            System.out.println("Erro: erro ao criar conexao com o banco de dados");
            throw new Error(ex.getMessage());
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
            System.out.println("Erro ao fechar a conexao");
            throw new Error(ex.getMessage());
        }
    }
}
