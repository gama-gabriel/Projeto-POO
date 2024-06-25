package Forms.FuncoesFuncionario;

import DAO.AgendamentoDAO;
import DTO.Agendamento;
import DTO.Paciente;
import Forms.PacienteMenuForm;
import Forms.TableModels.AgendamentoTableModel;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CancelarAgendamento extends JFrame {
    private Container c;
    private JTable tabela;
    private JScrollPane viewportTabela;
    private AgendamentoTableModel modeloTabela;
    private JLabel pacienteLogado;

    public CancelarAgendamento(Paciente logado) {
        modeloTabela = new AgendamentoTableModel();
        AgendamentoDAO dao = new AgendamentoDAO();
        modeloTabela.setDados(dao.retornaLista("", logado.getId()));

        setTitle("Cancelar Agendamento");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        JPanel p1 = new JPanel(null);
        p1.setBackground(new Color(255, 255, 232));
        p1.setSize(1000, 125);
        p1.setLocation(0, 0);
        JLabel title = new JLabel("Cancelar Agendamento", SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(400, 40);
        title.setFont(new Font("Inter", Font.BOLD, 22));
        title.setForeground(new Color(43, 37, 93, 255));
        title.setVisible(true);
        p1.add(title);

        JButton botaoVoltar = new JButton("voltar");
        botaoVoltar.setFont(new Font("Inter", Font.PLAIN, 16));
        botaoVoltar.setBounds(50, 30, 200, 65);
        botaoVoltar.setContentAreaFilled(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setFont(new Font("Inter", Font.BOLD, 16));
        botaoVoltar.setForeground(new Color(255, 255, 232));
        botaoVoltar.setForeground(new Color(43, 37, 93, 191));
        botaoVoltar.setBorder(new RoundedBorder(0, 0));
        botaoVoltar.setOpaque(false);
        botaoVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                botaoVoltar.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoVoltar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                botaoVoltar.setForeground(new Color(43, 37, 93, 191));
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                new PacienteMenuForm(logado);
            }
        });
        p1.add(botaoVoltar);

        JLabel labelLogado = new JLabel("Paciente:", SwingConstants.CENTER);
        labelLogado.setSize(150, 25);
        labelLogado.setLocation(800, 35);
        labelLogado.setFont(new Font("Inter", Font.PLAIN, 14));
        labelLogado.setForeground(new Color(43, 37, 93, 255));
        labelLogado.setVisible(true);
        p1.add(labelLogado);

        pacienteLogado = new JLabel(logado.getNome(), SwingConstants.CENTER);
        pacienteLogado.setSize(150, 25);
        pacienteLogado.setLocation(800, 55);
        pacienteLogado.setFont(new Font("Inter", Font.PLAIN, 14));
        pacienteLogado.setForeground(new Color(43, 37, 93, 255));
        pacienteLogado.setVisible(true);
        p1.add(pacienteLogado);

        c.add(p1);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(1000, 500);
        p2.setLocation(0, 125);

        DefaultTableCellRenderer celulaPadrao = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setForeground(Color.black);
                setHorizontalAlignment(JLabel.CENTER);
                return this;
            }
        };

        DefaultTableCellRenderer celulaDataHora = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setForeground(Color.black);
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime original = LocalDateTime.parse(getText());
                    String novo = original.format(formatter);
                    setText(novo);
                } catch (Exception err) {
                    System.out.println(err.getMessage());
                }
                setHorizontalAlignment(JLabel.CENTER);
                return this;
            }
        };

        tabela = new JTable();
        tabela.setModel(modeloTabela);
        tabela.setRowHeight(25);
        tabela.setRowMargin(10);
        tabela.setFont(new Font("Inter", Font.PLAIN, 12));
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            if (i == 1) {
                tabela.getColumnModel().getColumn(i).setCellRenderer(celulaDataHora);
                continue;
            }
            tabela.getColumnModel().getColumn(i).setCellRenderer(celulaPadrao);
        }
        tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(20);

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tabela.getSelectedRow();
                    Agendamento agendamento = modeloTabela.getAgendamentoAt(row);

                    int resposta = JOptionPane.showConfirmDialog(c, "Tem certeza que deseja cancelar o agendamento do exame " + agendamento.getExame().getNome() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(c, "Agendamento cancelado com sucesso!", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                        // Lógica de cancelamento do agendamento aqui
                    } else {
                        JOptionPane.showMessageDialog(c, "Operação de cancelamento cancelada.", "Cancelamento", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        viewportTabela = new JScrollPane(tabela);
        viewportTabela.setBounds(50, 75, 900, 300);
        p2.add(viewportTabela);

        c.add(p2);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CancelarAgendamento(new Paciente());
    }
}
