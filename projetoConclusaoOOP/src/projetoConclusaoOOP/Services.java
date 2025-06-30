/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetoConclusaoOOP;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


// classe utilitaria para lidar com CRUD de tipos diferentes de objetos
// <T> serve para objetos genericos (Autor e Livro)
// <K> serve para tipos genericos (String e int), Date e LocalDate estao sendo tratados de String
public class Services<T> {
    
    // regex para checar se uma string e uma data
    private static Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    
    
    /* -----------------------------------------------------------------------------------------------
    METODOS C R U D
    */
    
    // operacao de CREATE
    public static <T> void create(Connection conn, T object) {
        
        String query = null;
        PreparedStatement stmt = null;
        
        try {
            // insercao de um novo autor
            if(object instanceof Autor) {
                query = "INSERT INTO autores(nome, livros, data_nascimento, data_falecimento) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, ((Autor) object).getNome());
                stmt.setString(2, ((Autor) object).getLivros());
                stmt.setDate(3, Date.valueOf(((Autor) object).getDataNascimento()));
                stmt.setDate(4, Date.valueOf(((Autor) object).getDataFalecimento()));
                stmt.execute();
            
            } else if(object instanceof Livro) {
                // insercao de um novo livro
                query = "INSERT INTO livros(titulo, autor, generos, qtd_paginas, ano_lancamento, estoque) VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);

                stmt.setString(1, ((Livro) object).getTitulo());
                stmt.setInt(2, Autor.getId(((Livro) object).getAutor()));
                stmt.setString(3, ((Livro) object).getGenero());
                stmt.setInt(4, ((Livro) object).getQtdPaginas());
                stmt.setDate(5, Date.valueOf(((Livro) object).getAnoLancamento()));
                stmt.setInt(6, ((Livro) object).getEstoque());

                stmt.execute();

            }
            
            // fechando o statement
            if(stmt != null) {
                stmt.close();
            }
            
        } catch(SQLException ex) {
            System.out.println("Erro ao inserir dados");
            throw new Error(ex.getMessage());
        }
    }
    
    // operacao READ (retorna um objeto em base do banco de dados)
    public static <T> T read(Connection conn, String table, String param, String value) {
        T object = null;
        String query = "SELECT * FROM "+ table +" WHERE "+ param +" = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, value);
            
            ResultSet res = stmt.executeQuery();

            while(res.next()) {
                
                if(table.equals("autores")) {
                    String nome = res.getString("nome");
                    String livros = res.getString("livros");
                    LocalDate dataNascimento = res.getDate("data_nascimento").toLocalDate();
                    LocalDate dataFalecimento = res.getDate("data_falecimento").toLocalDate();

                    object = (T) new Autor(nome, livros, dataNascimento.toString(), dataFalecimento.toString());
                    
                } else if(table.equals("livros")) {
                    String titulo = res.getString("titulo");
                    String autor = res.getString("autor");
                    String generos = res.getString("generos");
                    int qtdPaginas = res.getInt("qtd_paginas");
                    String anoLancamento = res.getString("ano_lancamento");
                    int estoque = res.getInt("estoque");

                    object = (T) new Livro(titulo, autor, generos, qtdPaginas, anoLancamento, estoque);
                    
                } else {
                    throw new Error("Erro ao criar objeto (bloco if else)");
                }
            }
            
            // fechando resultado e statement
            res.close();
            stmt.close();
            
            // retornando o objeto para a posterior leitura encapsulada
            return (T) object;
            
        } catch(SQLException ex) {
            System.out.println("Erro ao criar objeto (catch)");
            throw new Error(ex.getMessage());            
        }
    }
    
    // operacao de UPDATE
    public static <T, K> void update(Connection conn, T object, String param, K value) {
        // lanca um erro se tentar usar id, pois e pk e auto-incremento, entao nao deve ser modificado
        if(param.equals("id")) {
            throw new Error("Nao e possivel fazer alteracoes em id");
        }
        String query = null;
        PreparedStatement stmt = null;
        
        // autalizara o objeto, via parametro, informado em base de uma busca do id do objeto
        try {
            // atualizando autor
            if(object instanceof Autor) {
               query = "UPDATE autores SET "+ param +" = ? WHERE id = ?";
               stmt = conn.prepareStatement(query);
               
               // checa se o valor inserido e uma String
               if(value instanceof String) {
                   String strValue = String.valueOf(value);

                   // checa se o valor inserido bate com o regex de data, para usar Date
                   if(DATE_PATTERN.matcher(strValue).matches()) {
                       stmt.setDate(1, Date.valueOf(strValue));
                   } else {
                       stmt.setString(1, strValue);
                   }
               } else {
                   // se nao e String, sera int, pois sao os unicos tipos no banco de dados
                   stmt.setInt(1, Integer.parseInt(value.toString()));
               }
               stmt.setInt(2, Autor.getId(((Autor) object).getNome()));
               
            } else if(object instanceof Livro) {
               // atualizando livro
               
               query = "UPDATE livros SET "+ param +" = ? WHERE id = ?";
               stmt = conn.prepareStatement(query);
               
               // checa se o valor e String
               if(value instanceof String) {
                   String strValue = String.valueOf(value);

                   // checa se o valor e data em base do regex
                   if(DATE_PATTERN.matcher(strValue).matches()) {
                       stmt.setDate(1, Date.valueOf(strValue));
                   } else {
                       stmt.setString(1, strValue);
                   }
               } else {
                   stmt.setInt(1, Integer.parseInt(value.toString()));
               }
               stmt.setInt(2, Livro.getId(((Livro) object).getTitulo()));

            }
            
            // executando a atualizacao e depois limpando o statement
            stmt.execute();
            stmt.close();
            
        } catch(SQLException ex) {
            System.out.printf("Erro ao fazer update do parametro '%s' e valor '%s'", param, value);
            throw new Error(ex.getMessage());
        }
    }
    
    // operacao de DELETE
    public static <T> void delete(Connection conn, T object) {
        String query = null;
        PreparedStatement stmt = null;
        
        // deletando o autor ou livro em base do id do objeto informado
        try {
            if(object instanceof Autor) {
                query = "DELETE FROM autores WHERE id = ?";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Autor.getId(((Autor) object).getNome()));
                stmt.execute();
                
            } else if(object instanceof Livro) {
                query = "DELETE FROM livros WHERE id = ?";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, Livro.getId(((Livro) object).getTitulo()));
                stmt.execute();
            }
            
        } catch(SQLException ex) {
            System.out.println("Erro na operacao delete");
            throw new Error(ex.getMessage());
        }
    }
}
