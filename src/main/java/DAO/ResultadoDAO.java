package DAO;

import DTO.Resultado;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultadoDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();

    public ResultadoDAO(){
        try{
            String sql = "create table if not exists resultado(" +
                    "    id int primary key auto_increment," +
                    "    descricao varchar(300)" +
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
    public int inserir(Resultado obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "insert into resultado" +
                        "    (descricao)" +
                        "    values (?);";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getDescricao());
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
    public int alterar(Resultado obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update resultado" +
                        "    set descricao = ?)" +
                        "    where id = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getDescricao());
                stmt.setInt(2, obj.getId());
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
    public int remover(Resultado obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from resultado where id=?";
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
    public Resultado pesquisar(int codigo){
        Resultado obj = new Resultado();
        try{
            if(conexao.conectar()){
                String sql = "select *  from resultado where id=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setInt(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(! resultado.isClosed()){
                    obj.setId(resultado.getInt("id"));
                    obj.setDescricao(resultado.getString("descricao"));
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