package DTO;

import DAO.PacienteDAO;

public class Paciente extends Pessoa {
    private int id;

    private PacienteDAO dao;

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

<<<<<<< HEAD


}
=======
}
>>>>>>> 91d6fc24992f69e150166587789f4b30b5d2a034
