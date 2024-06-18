package DAO;

import DTO.Exame;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExameDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();

    public ExameDAO(){
        try{
            String sql = "create table if not exists exames(" +
                    "    id int primary key auto_increment," +
                    "    nome varchar(30)," +
                    "    disponivell bool," +
                    "    descricao varchar(250)," +
                    "    preparo varchar(250)," +
                    "    instrucoes varchar(250)" +
                    ");";
            if(conexao.conectar()){
                Statement stmt = conexao.retornaStatement();
                stmt.execute(sql);
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
        }
    }
    public int inserir(Exame obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "insert into exames" +
                        "    (nome, disponivell, descricao, preparo, instrucoes)" +
                        "    values (?,?,?,?,?);";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setBoolean(2, obj.isDisponivel());
                stmt.setString(3, obj.getDescricao());
                stmt.setString(4, obj.getPreparo());
                stmt.setString(5, obj.getInstrucoesPos());
                cont = stmt.executeUpdate();
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return cont;
        }
    }
    public int alterar(Exame obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update exames" +
                        "    set nome = ?, disponivell = ?, descricao = ?, preparo = ?, instrucoes = ?)" +
                        "    where id = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setBoolean(2, obj.isDisponivel());
                stmt.setString(3, obj.getDescricao());
                stmt.setString(4, obj.getPreparo());
                stmt.setString(5, obj.getInstrucoesPos());
                stmt.setInt(6, obj.getId());
                cont = stmt.executeUpdate();
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return cont;
        }
    }
    public int remover(Exame obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from exames where id=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setInt(1, obj.getId());
                cont = stmt.executeUpdate();
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return cont;
        }
    }
    public Exame pesquisar(int codigo){
        Exame obj = new Exame();
        try{
            if(conexao.conectar()){
                String sql = "select *  from exames where id=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setInt(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(! resultado.isClosed()){
                    obj.setId(resultado.getInt("id"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setDisponivel(resultado.getBoolean("disponivell"));
                    obj.setDescricao(resultado.getString("descricao"));
                    obj.setPreparo(resultado.getString("preparo"));
                    obj.setInstrucoesPos(resultado.getString("instrucoes"));
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return obj;
        }
    }
}