/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetoConclusaoOOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;


public class Main {
    
    public static void main(String[] args) {
        
        // recuperando a conexao ao banco de dados
        Connection conn = ConnMYSQL.getConnection();
        
        // criando a janela do jframe usando a biblioteGui
        JFrame frame = new JFrame("Biblioteca GUI");
        frame.setContentPane(new GBiblioteca().getRootPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }    
}
