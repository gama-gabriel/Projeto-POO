package DTO;

import DAO.ExameDAO;
import DAO.ResultadoDAO;

public class Resultado {
    private int id;
    private String descricao;
    public ResultadoDAO dao;
    public void UseService(){
        this.dao = new ResultadoDAO();
    }

    public ResultadoDAO getDao() {
        return dao;
    }

    public void setDao(ResultadoDAO dao) {
        this.dao = dao;
    }

    public Resultado() {
    }

    public Resultado(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
