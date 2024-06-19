package DTO;

import DAO.PacienteDAO;

public class Paciente extends Pessoa {
    private int id;

    public  PacienteDAO dao;

    public Paciente() {
    }

    public Paciente(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void UseService(){
        this.dao = new PacienteDAO();
    }

    public Paciente autenticacao(String email, String senha){
        UseService();
        Paciente PacLogado = dao.login(email, senha);
        return PacLogado;
    }

    void marcarConsulta() {}

    void cancelarConsulta() {}



}
