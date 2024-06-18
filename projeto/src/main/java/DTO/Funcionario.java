package DTO;

public class Funcionario extends Pessoa {
    private int id;
    private String cargo;

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

}