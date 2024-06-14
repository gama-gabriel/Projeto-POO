/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ConexaoSQLite {
    Connection conexao;

    public Connection getConexao() {
        return conexao;
    }
    public boolean conectar(){
       try{
           String url = "jdbc:sqlite:projeto/db/projeto.db";
           this.conexao = DriverManager.getConnection(url); 
           return true;
       } 
       catch(Exception err){
           System.out.println(err.getMessage());
           return false;
       }
    }
    public boolean desconectar(){
        try{
           if(conexao != null){
               conexao.close();
           }
           return true;
       } 
       catch(SQLException err){
           return false;
       }
    }
    
    public Statement retornaStatement() throws SQLException{
        return conexao.createStatement();
    }
    
    public PreparedStatement preparedStatement(String sql) throws SQLException{
        return conexao.prepareStatement(sql);
    }
    
}
