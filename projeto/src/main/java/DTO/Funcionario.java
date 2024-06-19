package DTO;

import DAO.FuncionarioDAO;

public class Funcionario extends Pessoa {
    private int id;
    private String cargo;
    private FuncionarioDAO dao;

    public Funcionario() {
    }

    public Funcionario(int id, String cargo) {
        this.id = id;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    void visualizarAgenda() {}

    void acompanharPacientes() {}

    void listarResultados() {}

    void atualizarRegistros() {}
    public void UseService(){
        this.dao = new FuncionarioDAO();
    }
    public Funcionario autenticacao(String email, String senha){
        UseService();
        Funcionario funcLogado = dao.login(email, senha);
        return funcLogado;
    }
}