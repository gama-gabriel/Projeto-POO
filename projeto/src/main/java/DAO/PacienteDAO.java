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
                        "    set cpf = ?, nome = ?, email = ?, data_nasc = ?, ativo = ?, senha = ?" +
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
    public int remover(int id){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from paciente where id=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setInt(1,id);
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

    public List<Paciente> retornaLista(String busca){
        List<Paciente> lista = new ArrayList<Paciente>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.length() > 0){
                    stmt = conexao.preparedStatement("select *  from paciente "
                            + "where nome like ? order by nome");
                    stmt.setString(1, "%"+ busca + "%");
                } else {
                    stmt = conexao.preparedStatement("select *  from paciente "
                            + "order by nome");
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Paciente obj = new Paciente();
                    obj.setId(resultado.getInt("id"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setDataNascimento(resultado.getDate("data_nasc"));
                    obj.setAtivo(resultado.getBoolean("ativo"));
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

    public List<Paciente> pesquisaData(Date busca){
        List<Paciente> lista = new ArrayList<Paciente>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from paciente "
                        + "where data_nasc = ?");
                stmt.setDate(1, new java.sql.Date(busca.getTime()));
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Paciente obj = new Paciente();
                    obj.setId(resultado.getInt("id"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setDataNascimento(resultado.getDate("data_nasc"));
                    obj.setAtivo(resultado.getBoolean("ativo"));
                    obj.setSenha(resultado.getString("senha"));
                    lista.add(obj);
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
            System.out.println("erro sql");
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            System.out.println("erro java");
        }
        finally{
            conexao.desconectar();
            return lista;
        }
    }

    public List<Paciente> pesquisaAtivo(String busca){
        List<Paciente> lista = new ArrayList<Paciente>();
        boolean ativo = Boolean.parseBoolean(busca);
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from paciente "
                        + "where ativo = ?");
                stmt.setBoolean(1, ativo);
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Paciente obj = new Paciente();
                    obj.setId(resultado.getInt("id"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setDataNascimento(resultado.getDate("data_nasc"));
                    obj.setAtivo(resultado.getBoolean("ativo"));
                    obj.setSenha(resultado.getString("senha"));
                    lista.add(obj);
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
            System.out.println("erro sql");
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            System.out.println("erro java");
        }
        finally{
            conexao.desconectar();
            return lista;
        }
    }

    public List<Paciente> pesquisaColuna(String busca, String coluna){
        List<Paciente> lista = new ArrayList<Paciente>();
        if (coluna == "id") {
            Paciente paciente = pesquisar(Integer.parseInt(busca));
            lista.add(paciente);
            return lista;
        }
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from paciente "
                        + "where " + coluna + " like ?");
                stmt.setString(1,"%" + busca + "%");
                System.out.println(busca);
                System.out.println(coluna);
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Paciente obj = new Paciente();
                    obj.setId(resultado.getInt("id"));
                    obj.setCpf(resultado.getString("cpf"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setEmail(resultado.getString("email"));
                    obj.setDataNascimento(resultado.getDate("data_nasc"));
                    obj.setAtivo(resultado.getBoolean("ativo"));
                    obj.setSenha(resultado.getString("senha"));
                    lista.add(obj);
                }
            }
        }
        catch(SQLException err){
            System.err.println(err.getMessage());
            System.out.println("erro sql");
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            System.out.println("erro java");
        }
        finally{
            conexao.desconectar();
            return lista;
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