package Forms.TableModels;
import DTO.Resultado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ResultadoTableModel extends AbstractTableModel {
    private List<Resultado> dados = new ArrayList<Resultado>();
    private String[] colunas = {"id", "descrição"};
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
            case 1: return this.dados.get(linha).getDescricao();
            default: return null;
        }
    }
    @Override
    public String getColumnName(int coluna) {
        return getColunas()[coluna];
    }

    public List<Resultado> getDados() {
        return dados;
    }
    public void setDados(List<Resultado> dados) {
        this.dados = dados;
    }
    public String[] getColunas() {
        return colunas;
    }
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    public Resultado retornaObjeto(int linha){
        return dados.get(linha);
    }
}
