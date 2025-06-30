/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoConclusaoOOP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
/**
 *
 * @author Lucas
 */
public class Livro {
    
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int qtdPaginas;
    private LocalDate anoLancamento;
    private int estoque;
    
    // Valores padrão
    public Livro() {
        this.id = -1;
        this.titulo = "default";
        this.autor = "default";
        this.genero = "default";
        this.qtdPaginas = -1;
        this.anoLancamento = LocalDate.parse("1001-01-01");
        this.estoque = -1;
    }
    
    public Livro(String titulo, String autor, String genero, int qtdPaginas, String anoLancamento, int estoque) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.qtdPaginas = qtdPaginas;
        this.anoLancamento = LocalDate.parse(anoLancamento);
        this.estoque = estoque;
    }
    
    /* -----------------------------------------------------------------------------------------------
    GETTERS E SETTERS
    */
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTitulo(){
        return titulo;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getAutor(){
        return autor;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getGenero(){
        return genero;
    }
    
    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }
    public int getQtdPaginas(){
        return qtdPaginas;
    }
    
    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public LocalDate getAnoLancamento(){
        return anoLancamento;
    }
    
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    public int getEstoque() {
        return this.estoque;
    }
    
    
 
    public static int getId(String titulo) {
        String query = "SELECT id FROM livros WHERE titulo = ?";
        
        try {
            PreparedStatement stmt = ConnMYSQL.getConnection().prepareStatement(query);
            stmt.setString(1, titulo);
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




