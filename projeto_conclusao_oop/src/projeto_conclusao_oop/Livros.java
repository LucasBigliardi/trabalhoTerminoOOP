/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_conclusao_oop;

/**
 *
 * @author Lucas
 */
public class Livros extends Object{
    
    private String titulo;
    private String autor;
    private String genero;
    private String qtdPaginas;
    private int anoLancamento; 
    
    public Livros(){
        id = -1;
        titulo = "default";
        autor = "default";
        genero = "default";
        qtdPaginas = "default";
        anoLancamento = -1;
    }
    
    
    public Livros(String titulo, String autor, String genero, String qtdPaginas, int anoLancamento){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.qtdPaginas = qtdPaginas;
        this.anoLancamento = anoLancamento;

    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public String getQtdPaginas(){
        return qtdPaginas;
    }
    
    public int getAnoLancamento(){
        return anoLancamento;
    }
}




