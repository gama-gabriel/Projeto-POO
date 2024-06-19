package DAO;

import DTO.Funcionario;
import DTO.Paciente;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionarioDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();

    public FuncionarioDAO(){
        try{
            String sql = "create table if not exists funcionario(" +
                    "    id integer primary key autoincrement,"+
                    "    cpf varchar(11) unique," +
                    "    nome varchar(60)," +
                    "    email varchar(50) unique," +
                    "    data_nasc date," +
                    "    ativo bool," +
                    "    cargo varchar(30)," +
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
    public int inserir(Funcionario obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "insert into funcionario" +
                        "    (cpf, nome, email, data_nasc, ativo, cargo, senha)" +
                        "    values (?,?,?,?,?,?,?);";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getCpf());
                stmt.setString(2, obj.getNome());
                stmt.setString(3, obj.getEmail());
                stmt.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
                stmt.setBoolean(5, obj.getAtivo());
                stmt.setString(6, obj.getCargo());
                stmt.setString(7, obj.getSenha());
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
    public int alterar(Funcionario obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update funcionario" +
                        "    set cpf = ?, nome = ?, email = ?, data_nasc = ?, ativo = ?, cargo = ?, senha = ?)" +
                        "    where id = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getCpf());
                stmt.setString(2, obj.getNome());
                stmt.setString(3, obj.getEmail());
                stmt.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
                stmt.setBoolean(5, obj.getAtivo());
                stmt.setString(6, obj.getCargo());
                stmt.setString(7, obj.getSenha());
                stmt.setInt(8, obj.getId());
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
    public int remover(Funcionario obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from funcionario where id=?";
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
    public Funcionario pesquisar(int codigo){
        Funcionario obj = new Funcionario();
        try{
            if(conexao.conectar()){
                String sql = "select *  from funcionario where id=?";
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
                    obj.setCargo(resultado.getString("cargo"));
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
    public List<Funcionario> retornaLista(String busca){
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.length() > 0){
                    stmt = conexao.preparedStatement("select *  from funcionario "
                            + "where nome like ? order by nome");
                    stmt.setString(1, "%"+ busca + "%");
                } else {
                    stmt = conexao.preparedStatement("select *  from funcionario "
                            + "order by nome");
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Funcionario obj = new Funcionario();
                    obj.setId(resultado.getInt("id"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setDataNascimento(resultado.getDate("data_nasc"));
                    obj.setAtivo(resultado.getBoolean("ativo"));
                    obj.setCargo(resultado.getString("cargo"));
                    obj.setSenha(resultado.getString("senha"));
                    lista.add(obj);
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            conexao.desconectar();
            return lista;
        }
    }
    public Funcionario login(String email, String senha){
        Funcionario obj = new Funcionario();
        try{
            if(conexao.conectar()){
                String sql = "select * from funcionario where email = ? and senha = ?";
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
                    obj.setCargo(resultado.getString("cargo"));
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
