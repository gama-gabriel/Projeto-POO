package DAO;

import DTO.Agendamento;
import DTO.Exame;
import DTO.Funcionario;
import DTO.Paciente;
import DTO.Resultado;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AgendamentoDAO {
    private ConexaoSQLite conexao = new ConexaoSQLite();

    public AgendamentoDAO(){
        try{
            String sql = "create table if not exists agendamento(" +
                    "    id integer primary key autoincrement," +
                    "    data datetime," +
                    "    cancelado bool," +
                    "    paciente int," +
                    "    funcionario int," +
                    "    exame int," +
                    "    resultado int," +
                    "    foreign key (paciente) references paciente(id)," +
                    "    foreign key (funcionario) references funcionario(id)," +
                    "    foreign key (exame) references exames(id)," +
                    "    foreign key (resultado) references resultado(id)" +
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

    public int inserir(Agendamento obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "insert into agendamento" +
                        "    (data, cancelado, paciente, funcionario, exame, resultado)" +
                        "    values (?,?,?,?,?,?);";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setTimestamp(1, new java.sql.Timestamp(obj.getDataHora().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
                stmt.setBoolean(2, obj.getCancelado());
                stmt.setInt(3, obj.getPaciente().getId());
                stmt.setInt(4, obj.getSupervisor().getId());
                stmt.setInt(5, obj.getExame().getId());
                if (obj.getResultado() != null) {
                    stmt.setInt(6, obj.getResultado().getId());
                } else {
                    stmt.setNull(6, java.sql.Types.INTEGER);
                }
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
    public int alterar(Agendamento obj){
        int cont = 0;
        try{
            if(conexao.conectar()){
                String sql = "update agendamento" +
                        "    set data = ?, cancelado = ?, paciente = ?, funcionario = ?, exame = ?, resultado = ? " +
                        "    where id = ?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setTimestamp(1, new java.sql.Timestamp(obj.getDataHora().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
                stmt.setBoolean(2, obj.getCancelado());
                stmt.setInt(3, obj.getPaciente().getId());
                stmt.setInt(4, obj.getSupervisor().getId());
                stmt.setInt(5, obj.getExame().getId());
                if (obj.getResultado() != null) {
                    stmt.setInt(6, obj.getResultado().getId());
                } else {
                    stmt.setNull(6, java.sql.Types.INTEGER);
                }
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
                String sql = "delete from agendamento where id=?";
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

    public Agendamento pesquisar(int codigo){
        Agendamento obj = new Agendamento();
        try{
            if(conexao.conectar()){
                String sql = "select *  from agendamento where id=?";
                PreparedStatement stmt = conexao.preparedStatement(sql);
                stmt.setInt(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                if(! resultado.isClosed()){
                    obj.setId(resultado.getInt("id"));
                    Date date = resultado.getTimestamp("data");
                    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    obj.setDataHora(dateTime);
                    obj.setCancelado(resultado.getBoolean("cancelado"));
                    obj.setPaciente(new PacienteDAO().pesquisar(resultado.getInt("paciente")));
                    obj.setSupervisor(new FuncionarioDAO().pesquisar(resultado.getInt("funcionario")));
                    obj.setExame(new ExameDAO().pesquisar(resultado.getInt("exame")));
                    if (resultado.getInt("resultado") != 0) {
                        obj.setResultado(new ResultadoDAO().pesquisar(resultado.getInt("resultado")));
                    }
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

    public List<Agendamento> retornaLista(String busca){
        List<Agendamento> lista = new ArrayList<Agendamento>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                if(busca.length() > 0){
                    stmt = conexao.preparedStatement("select *  from agendamento "
                            + "where id like ? order by id");
                    stmt.setString(1, "%"+ busca + "%");
                } else {
                    stmt = conexao.preparedStatement("select *  from agendamento "
                            + "order by id");
                }
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Agendamento obj = new Agendamento();
                    obj.setId(resultado.getInt("id"));
                    obj.setCancelado(resultado.getBoolean("cancelado"));
                    Date date = resultado.getTimestamp("data");
                    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    obj.setDataHora(dateTime);
                    obj.setExame(new ExameDAO().pesquisar(resultado.getInt("exame")));
                    obj.setPaciente(new PacienteDAO().pesquisar(resultado.getInt("paciente")));
                    obj.setSupervisor(new FuncionarioDAO().pesquisar(resultado.getInt("funcionario")));
                    if (resultado.getInt("resultado") != 0) {
                        obj.setResultado(new ResultadoDAO().pesquisar(resultado.getInt("resultado")));
                    }

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


    public List<Agendamento> pesquisaData(LocalDateTime busca){
        List<Agendamento> lista = new ArrayList<Agendamento>();
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from agendamento "
                        + "where data = ?");
                stmt.setTimestamp(1, new java.sql.Timestamp(busca.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Agendamento obj = new Agendamento();
                    obj.setId(resultado.getInt("id"));
                    obj.setCancelado(resultado.getBoolean("cancelado"));
                    Date date = resultado.getTimestamp("data");
                    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    obj.setDataHora(dateTime);
                    obj.setExame(new ExameDAO().pesquisar(resultado.getInt("exame")));
                    obj.setPaciente(new PacienteDAO().pesquisar(resultado.getInt("paciente")));
                    obj.setSupervisor(new FuncionarioDAO().pesquisar(resultado.getInt("funcionario")));
                    if (resultado.getInt("resultado") != 0) {
                        obj.setResultado(new ResultadoDAO().pesquisar(resultado.getInt("resultado")));
                    }

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

    public List<Agendamento> pesquisaCancelado(String busca){
        List<Agendamento> lista = new ArrayList<Agendamento>();
        boolean ativo = Boolean.parseBoolean(busca);
        try{
            if(conexao.conectar()){
                PreparedStatement stmt;
                stmt = conexao.preparedStatement("select *  from agendamento "
                        + "where cancelado = ?");
                stmt.setBoolean(1, ativo);
                ResultSet resultado = stmt.executeQuery();
                while(resultado.next()){
                    Agendamento obj = new Agendamento();
                    obj.setId(resultado.getInt("id"));
                    obj.setCancelado(resultado.getBoolean("cancelado"));
                    Date date = resultado.getTimestamp("data");
                    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    obj.setDataHora(dateTime);
                    obj.setExame(new ExameDAO().pesquisar(resultado.getInt("exame")));
                    obj.setPaciente(new PacienteDAO().pesquisar(resultado.getInt("paciente")));
                    obj.setSupervisor(new FuncionarioDAO().pesquisar(resultado.getInt("funcionario")));
                    if (resultado.getInt("resultado") != 0) {
                        obj.setResultado(new ResultadoDAO().pesquisar(resultado.getInt("resultado")));
                    }

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

    public List<Agendamento> pesquisaColuna(String busca, String coluna){
        List<Agendamento> lista = new ArrayList<Agendamento>();
        String[] numCol = {"exame", "paciente", "funcionario", "resultado"};
        if (coluna == "id") {
            Agendamento func = pesquisar(Integer.parseInt(busca));
            lista.add(func);
            return lista;
        }
        if (Arrays.asList(numCol).contains(coluna)) {
            try {
                int num = Integer.parseInt(busca);
                if (conexao.conectar()) {
                    PreparedStatement stmt;
                    stmt = conexao.preparedStatement("select *  from agendamento "
                            + "where " + coluna + " = ?");
                    stmt.setInt(1, num);
                    System.out.println(busca);
                    System.out.println(coluna);
                    ResultSet resultado = stmt.executeQuery();
                    while (resultado.next()) {
                        Agendamento obj = new Agendamento();
                        obj.setId(resultado.getInt("id"));
                        obj.setCancelado(resultado.getBoolean("cancelado"));
                        Date date = resultado.getTimestamp("data");
                        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                        obj.setDataHora(dateTime);
                        obj.setExame(new ExameDAO().pesquisar(resultado.getInt("exame")));
                        obj.setPaciente(new PacienteDAO().pesquisar(resultado.getInt("paciente")));
                        obj.setSupervisor(new FuncionarioDAO().pesquisar(resultado.getInt("funcionario")));
                        if (resultado.getInt("resultado") != 0) {
                            obj.setResultado(new ResultadoDAO().pesquisar(resultado.getInt("resultado")));
                        }
                        lista.add(obj);
                    }
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } finally {
                conexao.desconectar();
                return lista;
            }
        } else {
            try {
                if (conexao.conectar()) {
                    PreparedStatement stmt;
                    stmt = conexao.preparedStatement("select *  from agendamento "
                            + "where " + coluna + " like ?");
                    stmt.setString(1, "%" + busca + "%");
                    System.out.println(busca);
                    System.out.println(coluna);
                    ResultSet resultado = stmt.executeQuery();
                    while (resultado.next()) {
                        Agendamento obj = new Agendamento();
                        obj.setId(resultado.getInt("id"));
                        obj.setCancelado(resultado.getBoolean("cancelado"));
                        Date date = resultado.getTimestamp("data");
                        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                        obj.setDataHora(dateTime);
                        obj.setExame(new ExameDAO().pesquisar(resultado.getInt("exame")));
                        obj.setPaciente(new PacienteDAO().pesquisar(resultado.getInt("paciente")));
                        obj.setSupervisor(new FuncionarioDAO().pesquisar(resultado.getInt("funcionario")));
                        if (resultado.getInt("resultado") != 0) {
                            obj.setResultado(new ResultadoDAO().pesquisar(resultado.getInt("resultado")));
                        }
                        lista.add(obj);
                    }
                }
            } catch (SQLException err) {
                System.err.println(err.getMessage());
                System.out.println("erro sql");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println("erro java");
            } finally {
                conexao.desconectar();
                return lista;
            }
        }
    }
}