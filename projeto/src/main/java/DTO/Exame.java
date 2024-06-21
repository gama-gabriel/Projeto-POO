package DTO;

import DAO.ExameDAO;
import DAO.PacienteDAO;

public class Exame {
    private int id;
    private String nome;
    private boolean disponivel;
    private String descricao;
    private String preparo;
    private String instrucoesPos;
    public ExameDAO dao;

    public Exame() {
    }
    public void UseService(){
        this.dao = new ExameDAO();
    }

    public Exame(int id, String nome, boolean disponivel, String descricao, String preparo, String instrucoesPos) {
        this.id = id;
        this.nome = nome;
        this.disponivel = disponivel;
        this.descricao = descricao;
        this.preparo = preparo;
        this.instrucoesPos = instrucoesPos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public String getInstrucoesPos() {
        return instrucoesPos;
    }

    public void setInstrucoesPos(String instrucoesPos) {
        this.instrucoesPos = instrucoesPos;
    }
}
