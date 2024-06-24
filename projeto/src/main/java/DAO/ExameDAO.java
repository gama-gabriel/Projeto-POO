package DAO;

import DTO.Exame;
import DTO.Funcionario;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();

    public ExameDAO(){
        try{
            String sql = "create table if not exists exames(" +
                    "    id integer primary key autoincrement," +
                    "    nome varchar(30)," +
                    "    disponivel bool," +
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
                        "    (nome, disponivel, descricao, preparo, instrucoes)" +
                        "    values (?,?,?,?,?);";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setBoolean(2, obj.getDisponivel());
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
                        "    set nome = ?, disponivel = ?, descricao = ?, preparo = ?, instrucoes = ?" +
                        "    where id = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, obj.getNome());
                stmt.setBoolean(2, obj.getDisponivel());
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

    public int remover(int id){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from exames where id=?";
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
                    obj.setDisponivel(resultado.getBoolean("disponivel"));
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

    public List<Exame> retornaLista(String busca){
        List<Exame> lista = new ArrayList<Exame>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.length() > 0){
                    stmt = conexao.preparedStatement("select *  from exames "
                            + "where nome like ? order by nome");
                    stmt.setString(1, "%"+ busca + "%");
                } else {
                    stmt = conexao.preparedStatement("select *  from exames "
                            + "order by nome");
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Exame obj = new Exame();
                    obj.setId(resultado.getInt("id"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setDisponivel(resultado.getBoolean("disponivel"));
                    obj.setDescricao(resultado.getString("descricao"));
                    obj.setPreparo(resultado.getString("preparo"));
                    obj.setInstrucoesPos(resultado.getString("instrucoes"));
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
    public List<Exame> retornaListaPaciente(){
        List<Exame> lista = new ArrayList<Exame>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from exames "
                            + "where disponivel = 1 order by nome");
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Exame obj = new Exame();
                    obj.setId(resultado.getInt("id"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setDisponivel(resultado.getBoolean("disponivel"));
                    obj.setDescricao(resultado.getString("descricao"));
                    obj.setPreparo(resultado.getString("preparo"));
                    obj.setInstrucoesPos(resultado.getString("instrucoes"));
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

    public List<Exame> pesquisaDsponivel(String busca){
        List<Exame> lista = new ArrayList<Exame>();
        boolean disponivel = Boolean.parseBoolean(busca);
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from exames "
                        + "where disponivel = ?");
                stmt.setBoolean(1, disponivel);
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Exame obj = new Exame();
                    obj.setId(resultado.getInt("id"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setDisponivel(resultado.getBoolean("disponivel"));
                    obj.setDescricao(resultado.getString("descricao"));
                    obj.setPreparo(resultado.getString("preparo"));
                    obj.setInstrucoesPos(resultado.getString("instrucoes"));
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

    public Exame findByName(String nome){
        Exame obj = new Exame();
        try{
            if(conexao.conectar()){
                String sql = "select *  from exames where nome=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setString(1, nome);
                ResultSet resultado = stmt.executeQuery();
                if(! resultado.isClosed()){
                    obj.setId(resultado.getInt("id"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setDisponivel(resultado.getBoolean("disponivel"));
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

    public List<Exame> pesquisaColuna(String busca, String coluna){
        List<Exame> lista = new ArrayList<Exame>();
        if (coluna == "id") {
            Exame exame = pesquisar(Integer.parseInt(busca));
            lista.add(exame);
            return lista;
        }
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from exames "
                        + "where " + coluna + " like ?");
                stmt.setString(1, "%" + busca + "%");
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Exame obj = new Exame();
                    obj.setId(resultado.getInt("id"));
                    obj.setNome(resultado.getString("nome"));
                    obj.setDisponivel(resultado.getBoolean("disponivel"));
                    obj.setDescricao(resultado.getString("descricao"));
                    obj.setPreparo(resultado.getString("preparo"));
                    obj.setInstrucoesPos(resultado.getString("instrucoes"));
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
}
