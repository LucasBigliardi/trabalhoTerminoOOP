/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoConclusaoOOP;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Autor {
    int id;
    String nome;
    String livros;
    LocalDate dataNascimento;
    LocalDate dataFalecimento;
    
    public Autor(String nome, String livros, String dataNascimento, String dataFalecimento) {
        this.nome = nome;
        this.livros = livros;
        this.dataNascimento = LocalDate.parse(dataNascimento);
        this.dataFalecimento = LocalDate.parse(dataFalecimento);
    }
    
    /* -----------------------------------------------------------------------------------------------
    GETTERS E SETTERS
    */
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setLivros(String livros) {
        this.livros = nome;
    }
    public String getLivros() {
        return this.livros;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }
    
    public void setDataFalecimento(LocalDate dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }
    public LocalDate getDataFalecimento() {
        return this.dataFalecimento;
    }
    
    public static int getId(String autor) {
        String query = "SELECT id FROM autores WHERE nome = ?";
        
        try {
            PreparedStatement stmt = ConnMYSQL.getConnection().prepareStatement(query);
            stmt.setString(1, autor);
            ResultSet res = stmt.executeQuery();
            
            while(res.next()) {
                return res.getInt("id");
            }
        } catch(SQLException ex) {
            throw new Error(ex.getMessage());
        }
        
        return -1;
    }
}
