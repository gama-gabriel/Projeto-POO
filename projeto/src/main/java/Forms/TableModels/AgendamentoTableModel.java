package Forms.TableModels;

import DTO.Agendamento;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoTableModel extends AbstractTableModel {
    private List<Agendamento> dados = new ArrayList<Agendamento>();
    private String[] colunas = {"id", "data/hora", "cancelado", "paciente", "func. responsável", "exame", "resultado"};
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
            case 1: return this.dados.get(linha).getDataHora();
            case 2: return this.dados.get(linha).getCancelado();
            case 3: return this.dados.get(linha).getPaciente().getId();
            case 4: return this.dados.get(linha).getSupervisor().getId();
            case 5: return this.dados.get(linha).getExame().getId();
            case 6: {
                if (dados.get(linha).getResultado() == null) {
                    return null;
                } else {
                    return this.dados.get(linha).getResultado().getId();
                }

            }
            default: return null;
        }
    }
    @Override
    public String getColumnName(int coluna) {
        return getColunas()[coluna];
    }

    public List<Agendamento> getDados() {
        return dados;
    }
    public void setDados(List<Agendamento> dados) {
        this.dados = dados;
    }
    public String[] getColunas() {
        return colunas;
    }
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    public Agendamento retornaObjeto(int linha){
        return dados.get(linha);
    }
}
