package DTO;

public class Paciente extends Pessoa {
    private int id;

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

    void marcarConsulta() {}

    void cancelarConsulta() {}

}
