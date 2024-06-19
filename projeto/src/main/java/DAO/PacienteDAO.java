package DAO;

import DTO.Paciente;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class PacienteDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();

    public PacienteDAO(){
        try{
            String sql = "create table if not exists paciente(" +
                    "    id integer primary key autoincrement," +
                    "    cpf varchar(11)," +
                    "    nome varchar(60)," +
                    "    email varchar(50) unique," +
                    "    data_nasc date," +
                    "    ativo bool," +
                    "    senha varchar(50)" +
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
    public int inserir(Paciente obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "insert into paciente" +

                        "    (cpf, nome, email, data_nasc, ativo, senha)" +
                        "    values (?,?,?,?,?,?);";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getCpf());
                stmt.setString(2, obj.getNome());
                stmt.setString(3, obj.getEmail());
                stmt.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
                stmt.setBoolean(5, obj.getAtivo());
                stmt.setString(6, obj.getSenha());
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
    public int alterar(Paciente obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update paciente" +
                        "    set cpf = ?, nome = ?, email = ?, data_nasc = ?, ativo = ?, senha = ?)" +
                        "    where id = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getCpf());
                stmt.setString(2, obj.getNome());
                stmt.setString(3, obj.getEmail());
                stmt.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
                stmt.setBoolean(5, obj.getAtivo());
                stmt.setString(6, obj.getSenha());
                stmt.setInt(7, obj.getId());
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
    public int remover(Paciente obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from paciente where id=?";
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
    public Paciente pesquisar(int codigo){
        Paciente obj = new Paciente();
        try{
            if(conexao.conectar()){
                String sql = "select *  from paciente where id=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setInt(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(! resultado.isClosed()){
                    obj.setId(resultado.getInt("id"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    java.sql.Date sqlDate = resultado.getDate("data_nasc");
                    obj.setDataNascimento(new Date(sqlDate.getTime()));
                    obj.setAtivo(resultado.getBoolean("ativo"));
                    obj.setSenha(resultado.getString("senha"));
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

    public boolean pesquisaCpf(String cpf){
        boolean encontrei = false;
        try{
            if(conexao.conectar()){
                String sql = "select count(*) as total from paciente where cpf=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, cpf);
                ResultSet resultado = stmt.executeQuery();
                int count = resultado.getInt("total");
                if( count > 0){
                    encontrei = true;
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return encontrei;
        }
    }

    public boolean pesquisaEmail(String email){
        boolean encontrei = false;
        try{
            if(conexao.conectar()){
                String sql = "select count(*) as total from paciente where email=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, email);
                ResultSet resultado = stmt.executeQuery();
                int count = resultado.getInt("total");
                if( count > 0){
                    encontrei = true;
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        finally{
            conexao.desconectar();
            return encontrei;
        }
    }

    public Paciente login(String email, String senha){
        Paciente obj = new Paciente();
        try{
            if(conexao.conectar()){
                String sql = "select * from paciente where email = ? and senha = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, senha);
                ResultSet resultado = stmt.executeQuery();
                if( !resultado.isClosed()) {
                    obj.setId(resultado.getInt("id"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    java.sql.Date sqlDate = resultado.getDate("data_nasc");
                    obj.setDataNascimento(new Date(sqlDate.getTime()));
                    obj.setAtivo(resultado.getBoolean("ativo"));
                    obj.setSenha(resultado.getString("senha"));
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
    };
}