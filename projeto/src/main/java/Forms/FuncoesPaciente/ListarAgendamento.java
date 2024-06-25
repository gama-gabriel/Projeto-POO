package Forms.FuncoesPaciente;
import DAO.AgendamentoDAO;
import DAO.FuncionarioDAO;
import DTO.Agendamento;
import DTO.Funcionario;
import DTO.Paciente;
import Forms.Alteracao.AgendamentoAlterar;
import Forms.Cadastro.AgendamentoCadastro;
import Forms.FuncionarioMenuForm;
import Forms.PacienteMenuForm;
import Forms.TableModels.AgendamentoTableModel;
import Forms.utils.RoundedBorder;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ListarAgendamento extends JFrame {
    private Container c;
    private JPanel filtrosPesquisa;
    private JTable tabela;
    private JScrollPane viewportTabela;
    private AgendamentoTableModel modeloTabela;
    private JLabel funcLogado;
    private JButton opcaoPesquisar;
    private JButton opcaoCriar;
    private JButton opcaoAlterar;
    private JButton opcaoDeletar;
    private JTextField pesquisaTexto;
    private JFormattedTextField pesquisaData;
    private AgendamentoDAO dao;
    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
    public ListarAgendamento(Paciente logado) {
        modeloTabela = new AgendamentoTableModel();
        dao = new AgendamentoDAO();
        modeloTabela.setDados(dao.retornaLista("",logado.getId()));
        System.out.println(modeloTabela.getDados().get(0).getSupervisor().getId());

        setTitle("Gerenciamento de agendamentos");
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
        JLabel title = new JLabel("Gerenciamento de agendamentos", SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(400, 40);
        title.setFont(new Font("Inter", Font.BOLD, 22));
        title.setForeground(new Color(43, 37, 93, 255));
        title.setVisible(true);
        p1.add(title);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(1000, 500);
        p2.setLocation(0, 125);

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
                PacienteMenuForm telaMenuFunc = new PacienteMenuForm(logado);
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

        funcLogado = new JLabel(logado.getNome(), SwingConstants.CENTER);
        funcLogado.setSize(150, 25);
        funcLogado.setLocation(800, 55);
        funcLogado.setFont(new Font("Inter", Font.PLAIN, 14));
        funcLogado.setForeground(new Color(43, 37, 93, 255));
        funcLogado.setVisible(true);
        p1.add(funcLogado);


        c.add(p1);

        DefaultTableCellRenderer celulaPadrao = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
                    value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                setForeground(Color.black);
                setHorizontalAlignment(JLabel.CENTER);
                return this;
            }
        };

        DefaultTableCellRenderer celulaDataHora = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object
                    value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
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

        int HEADER_HEIGHT = 32;
        viewportTabela = new JScrollPane();
        viewportTabela.setBackground(new Color(0x555555));
        viewportTabela.setBounds(50, 75, 900, 300);
        viewportTabela.setColumnHeader(new JViewport() {
            @Override public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = HEADER_HEIGHT;
                return d;
            }
        });
        viewportTabela.setForeground(new Color(0x2b255d));
        viewportTabela.setViewportView(tabela);

        filtrosPesquisa = new JPanel(null);
        filtrosPesquisa.setBounds(0, 40, 1000, 835);
        filtrosPesquisa.setBackground(new Color(255, 255, 232));

        JLabel filtrosLabel = new JLabel("Filtros de pesquisa", SwingConstants.CENTER);
        filtrosLabel.setBounds(400, 25, 200, 40);
        filtrosLabel.setForeground(new Color(0x2b255d));
        filtrosLabel.setFont(new Font("Inter", Font.BOLD, 20));
        filtrosPesquisa.add(filtrosLabel);

        JLabel colunaLabel = new JLabel("Coluna");
        colunaLabel.setBounds(425, 100, 100, 30);
        colunaLabel.setForeground(new Color(0x2b255d));
        colunaLabel.setFont(new Font("Inter", Font.BOLD, 16));
        filtrosPesquisa.add(colunaLabel);

        JComboBox colunaPesquisa = new JComboBox(modeloTabela.getColunas());
        colunaPesquisa.setBounds(425,130, 150, 30);
        colunaPesquisa.setBackground(Color.WHITE);
        colunaPesquisa.setFont(new Font("Inter", Font.PLAIN, 16));
        colunaPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colunaPesquisa.getSelectedItem() == "data/hora") {
                    pesquisaTexto.setVisible(false);
                    pesquisaData.setVisible(true);
                } else {
                    pesquisaTexto.setVisible(true);
                    pesquisaData.setVisible(false);
                }
            }
        });
        filtrosPesquisa.add(colunaPesquisa);

        JLabel pesquisaLabel = new JLabel("Valor");
        pesquisaLabel.setBounds(425, 170, 100, 30);
        pesquisaLabel.setForeground(new Color(0x2b255d));
        pesquisaLabel.setFont(new Font("Inter", Font.BOLD, 16));
        filtrosPesquisa.add(pesquisaLabel);

        pesquisaTexto = new JTextField();
        pesquisaTexto.setBounds(425, 200, 150, 30);
        pesquisaTexto.setFont(new Font("Inter", Font.PLAIN, 18));
        filtrosPesquisa.add(pesquisaTexto);

        pesquisaData = new JFormattedTextField(createFormatter("##/##/#### ##:##"));
        pesquisaData.setBounds(415, 200, 170, 30);
        pesquisaData.setFont(new Font("Inter", Font.PLAIN, 18));
        pesquisaData.setBorder(new RoundedBorder(1, 1));
        pesquisaData.setVisible(false);
        filtrosPesquisa.add(pesquisaData);

        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setFont(new Font("Inter", Font.BOLD, 16));
        botaoPesquisar.setBounds(445, 240, 120, 35);
        botaoPesquisar.setContentAreaFilled(false);
        botaoPesquisar.setFocusPainted(false);
        botaoPesquisar.setFont(new Font("Inter", Font.BOLD, 16));
        botaoPesquisar.setForeground(new Color(255, 255, 232));
        botaoPesquisar.setBorder(new RoundedBorder(10, 2));
        botaoPesquisar.setForeground(new Color(43, 37, 93, 191));
        botaoPesquisar.setOpaque(false);
        botaoPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoPesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                botaoPesquisar.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoPesquisar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                botaoPesquisar.setForeground(new Color(43, 37, 93, 191));
            }
        });
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabela.clearSelection();
                String busca = pesquisaTexto.getText();
                String coluna = (String) colunaPesquisa.getSelectedItem();
                try {
                    assert coluna != null;
                    if (coluna.equals("func. responsável")) {
                        coluna = "funcionario";
                    }
                    if (coluna.equals("resultado")) {
                        modeloTabela.setDados(dao.pesquisaResultado(busca));
                    } else if (coluna.equals("data/hora")) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dataHora = LocalDateTime.parse(pesquisaData.getText(), formatter);
                        modeloTabela.setDados(dao.pesquisaData(dataHora));
                    } else if (coluna.equals("cancelado")) {
                        modeloTabela.setDados(dao.pesquisaCancelado(busca));
                    } else {
                        modeloTabela.setDados(dao.pesquisaColuna(busca, coluna));
                    }
                    tabela.setModel(modeloTabela);
                    filtrosPesquisa.setVisible(false);
                    viewportTabela.setVisible(true);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Erro ao pesquisar. Tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println(err.getMessage());
                }
            }
        });
        filtrosPesquisa.setVisible(false);
        filtrosPesquisa.add(botaoPesquisar);


        p2.add(filtrosPesquisa);
        p2.add(viewportTabela);
        p2.setVisible(true);

        c.add(p2);
        setVisible(true);
    }
}
