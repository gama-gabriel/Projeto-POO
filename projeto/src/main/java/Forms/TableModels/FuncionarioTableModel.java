package Forms.TableModels;

import DAO.FuncionarioDAO;
import DTO.Funcionario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FuncionarioTableModel extends AbstractTableModel {
    private List<Funcionario> dados = new ArrayList<Funcionario>();
    private String[] colunas = {"id", "cpf", "nome", "email", "data de nascimento", "ativo", "cargo"};
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
            case 6: return this.dados.get(linha).getCargo();
            case 7: return this.dados.get(linha).getSenha();
            default: return null;
        }
    }
    @Override
    public String getColumnName(int coluna) {
        return getColunas()[coluna];
    }

    public List<Funcionario> getDados() {
        return dados;
    }
    public void setDados(List<Funcionario> dados) {
        this.dados = dados;
    }
    public String[] getColunas() {
        return colunas;
    }
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    public Funcionario retornaObjeto(int linha){
        return dados.get(linha);
    }
}

