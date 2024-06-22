package DAO;

import DTO.Resultado;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultadoDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();

    public ResultadoDAO(){
        try{
            String sql = "create table if not exists resultado(" +
                    "    id integer primary key autoincrement," +
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
                        "    set descricao = ?" +
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

    public int remover(int id){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "delete from resultado where id=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setInt(1, id);
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

    public List<Resultado> retornaLista(String busca){
        List<Resultado> lista = new ArrayList<Resultado>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.length() > 0){
                    stmt = conexao.preparedStatement("select *  from resultado "
                            + "where descricao like ? order by descricao");
                    stmt.setString(1, "%"+ busca + "%");
                } else {
                    stmt = conexao.preparedStatement("select *  from resultado "
                            + "order by descricao");
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Resultado obj = new Resultado();
                    obj.setId(resultado.getInt("id"));
                    obj.setDescricao(resultado.getString("descricao"));
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

    public List<Resultado> pesquisaColuna(String busca, String coluna){
        List<Resultado> lista = new ArrayList<Resultado>();
        if (coluna == "id") {
            Resultado resultado = pesquisar(Integer.parseInt(busca));
            lista.add(resultado);
            return lista;
        }
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from resultado "
                        + "where " + coluna + " like ?");
                stmt.setString(1, "%" + busca + "%");
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Resultado obj = new Resultado();
                    obj.setId(resultado.getInt("id"));
                    obj.setDescricao(resultado.getString("descricao"));
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