package Forms.TableModels;
import DTO.Exame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ExameTableModel extends AbstractTableModel {
    private List<Exame> dados = new ArrayList<Exame>();
    private String[] colunas = {"id", "nome", "disponível", "descrição", "preparo", "instruções"};
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
            case 1: return this.dados.get(linha).getNome();
            case 2: return this.dados.get(linha).getDisponivel();
            case 3: return this.dados.get(linha).getDescricao();
            case 4: return this.dados.get(linha).getPreparo();
            case 5: return this.dados.get(linha).getInstrucoesPos();
            default: return null;
        }
    }
    @Override
    public String getColumnName(int coluna) {
        return getColunas()[coluna];
    }

    public List<Exame> getDados() {
        return dados;
    }
    public void setDados(List<Exame> dados) {
        this.dados = dados;
    }
    public String[] getColunas() {
        return colunas;
    }
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    public Exame retornaObjeto(int linha){
        return dados.get(linha);
    }
}