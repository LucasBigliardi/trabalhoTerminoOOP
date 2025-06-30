/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetoConclusaoOOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lucas
 */
public class Main {
    
    public static void main(String[] args) {
        
        Connection conn = ConnMYSQL.getConnection();
        
        Autor autor = new Autor("Gustavo Luiz Gregorio", "Nada", "2004-07-27", "1001-01-01");
        Livro livro = new Livro("Teste livro", "Gustavo Luiz Gregorio", "Drama", 220, "2020-05-06", 10);
        
        Livro liv = Services.read(conn, "livros", "titulo", "Teste livro");
        
        System.out.println(liv.getTitulo());

    }    
}
