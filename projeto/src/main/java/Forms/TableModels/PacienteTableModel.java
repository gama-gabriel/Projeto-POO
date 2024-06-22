package Forms.TableModels;

import DAO.PacienteDAO;
import DTO.Paciente;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PacienteTableModel extends AbstractTableModel {
    private List<Paciente> dados = new ArrayList<Paciente>();
    private String[] colunas = {"id", "cpf", "nome", "email", "data de nascimento", "ativo"};
    @Override
    public int getRowCount() {
        return getDados().size();
    }
    @Override
    public int getColumnCount() {
        return getColunas().length;
    }
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return this.dados.get(linha).getId();
            case 1: return this.dados.get(linha).getCpf();
            case 2: return this.dados.get(linha).getNome();
            case 3: return this.dados.get(linha).getEmail();
            case 4: return this.dados.get(linha).getDataNascimento();
            case 5: return this.dados.get(linha).getAtivo();
            case 7: return this.dados.get(linha).getSenha();
            default: return null;
        }
    }
    @Override
    public String getColumnName(int coluna) {
        return getColunas()[coluna];
    }

    public List<Paciente> getDados() {
        return dados;
    }
    public void setDados(List<Paciente> dados) {
        this.dados = dados;
    }
    public String[] getColunas() {
        return colunas;
    }
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    public Paciente retornaObjeto(int linha){
        return dados.get(linha);
    }
}
