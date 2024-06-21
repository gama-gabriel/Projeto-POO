package Forms.FuncoesFuncionario;
import DAO.ResultadoDAO;
import DTO.*;
import Forms.TableModels.ResultadoTableModel;
import Forms.utils.RoundedBorder;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

public class GerenciarResultados extends JFrame {
    private Container c;
    private JPanel filtrosPesquisa;
    private JTable tabela;
    private JScrollPane viewportTabela;
    private ResultadoTableModel modeloTabela;
    private JLabel funcLogado;
    private JButton opcaoPesquisar;
    private JButton opcaoCriar;
    private JButton opcaoAlterar;
    private JButton opcaoDeletar;
    private JTextField pesquisaTexto;
    private JFormattedTextField pesquisaData;
    private ResultadoDAO dao;
    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }
    public GerenciarResultados(Funcionario logado) {
        modeloTabela = new ResultadoTableModel();
        dao = new ResultadoDAO();
        modeloTabela.setDados(dao.retornaLista(""));

        setTitle("Gerenciamento de resultados");
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
        JLabel title = new JLabel("Gerenciamento de resultados", SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(400, 40);
        title.setFont(new Font("Inter", Font.BOLD, 22));
        title.setForeground(new Color(43, 37, 93, 255));
        title.setVisible(true);
        p1.add(title);

        JLabel labelLogado = new JLabel("Funcionário:", SwingConstants.CENTER);
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

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(1000, 500);
        p2.setLocation(0, 125);

        JComboBox lista = new JComboBox(new String[] {"a","b","c"});
        lista.setBounds(100, 50, 200, 30);
        lista.setBackground(new Color(0x999999));

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

        opcaoPesquisar = new JButton("Pesquisar registros");
        opcaoPesquisar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoPesquisar.setBounds(50, 0, 200, 40);
        opcaoPesquisar.setContentAreaFilled(false);
        opcaoPesquisar.setFocusPainted(false);
        opcaoPesquisar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoPesquisar.setForeground(new Color(255, 255, 232));
        opcaoPesquisar.setBorder(new RoundedBorder(10, 3));
        opcaoPesquisar.setForeground(new Color(43, 37, 93, 191));
        opcaoPesquisar.setOpaque(false);
        opcaoPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                opcaoPesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                opcaoPesquisar.setForeground(new Color(43, 37, 93, 255));
            }
public void mouseExited(java.awt.event.MouseEvent evt) {
                opcaoPesquisar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                opcaoPesquisar.setForeground(new Color(43, 37, 93, 191));
            }
        });
        opcaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabela.clearSelection();
                viewportTabela.setVisible(false);
                filtrosPesquisa.setVisible(true);
            }
        });
        p2.add(opcaoPesquisar);

        opcaoCriar = new JButton("Criar novo registro");
        opcaoCriar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoCriar.setBounds(283, 0, 200, 40);
        opcaoCriar.setContentAreaFilled(false);
        opcaoCriar.setFocusPainted(false);
        opcaoCriar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoCriar.setForeground(new Color(255, 255, 232));
        opcaoCriar.setBorder(new RoundedBorder(10, 3));
        opcaoCriar.setForeground(new Color(43, 37, 93, 191));
        opcaoCriar.setOpaque(false);
        opcaoCriar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                opcaoCriar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                opcaoCriar.setForeground(new Color(43, 37, 93, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                opcaoCriar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                opcaoCriar.setForeground(new Color(43, 37, 93, 191));
            }
        });
        opcaoCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                ResultadoCadastro telaCadastroResultado = new ResultadoCadastro(logado);
            }
        });
        p2.add(opcaoCriar);

        opcaoAlterar = new JButton("Alterar registro");
        opcaoAlterar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoAlterar.setBounds(516, 0, 200, 40);
        opcaoAlterar.setContentAreaFilled(false);
        opcaoAlterar.setFocusPainted(false);
        opcaoAlterar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoAlterar.setForeground(new Color(255, 255, 232));
        opcaoAlterar.setBorder(new RoundedBorder(10, 3));
        opcaoAlterar.setForeground(new Color(43, 37, 93, 191));
        opcaoAlterar.setOpaque(false);
        opcaoAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                opcaoAlterar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                opcaoAlterar.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                opcaoAlterar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                opcaoAlterar.setForeground(new Color(43, 37, 93, 191));
            }
        });
        opcaoAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione a linha que deseja alterar", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) tabela.getValueAt(row, 0);
                Resultado alterado = dao.pesquisaColuna(String.valueOf(id), "id").get(0);
                c.setVisible(false);
                dispose();
                ResultadoAlterar telaAterar = new ResultadoAlterar(logado, alterado);
                System.out.println(id);
            }
        });
        p2.add(opcaoAlterar);

        opcaoDeletar = new JButton("Excluir registro");
        opcaoDeletar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoDeletar.setBounds(750, 0, 200, 40);
        opcaoDeletar.setContentAreaFilled(false);
        opcaoDeletar.setFocusPainted(false);
        opcaoDeletar.setFont(new Font("Inter", Font.BOLD, 16));
        opcaoDeletar.setForeground(new Color(255, 255, 232));
        opcaoDeletar.setBorder(new RoundedBorder(10, 3));
        opcaoDeletar.setForeground(new Color(43, 37, 93, 191));
        opcaoDeletar.setOpaque(false);
        opcaoDeletar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                opcaoDeletar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                opcaoDeletar.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                opcaoDeletar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                opcaoDeletar.setForeground(new Color(43, 37, 93, 191));
            }
        });
        opcaoDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabela.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione a linha que deseja excluir", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = (int) tabela.getValueAt(row, 0);
                int dialogButton = JOptionPane.showConfirmDialog (null, String.format("Tem certeza que quer excluir o resultado de id %d?", id),"Confirmação",JOptionPane.YES_NO_OPTION);
                if(dialogButton == JOptionPane.YES_OPTION) {
                    viewportTabela.setVisible(false);
                    tabela.clearSelection();
                    dao.remover(id);
                    modeloTabela.setDados(dao.retornaLista(""));
                    tabela.setModel(modeloTabela);
                    viewportTabela.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Resultado excluído!", "Sucesso", JOptionPane.DEFAULT_OPTION);

                }
                System.out.println(id);
            }
        });
        p2.add(opcaoDeletar);

        tabela = new JTable();
        tabela.setModel(modeloTabela);
        tabela.setRowHeight(25);
        tabela.setRowMargin(10);
        tabela.setFont(new Font("Inter", Font.PLAIN, 12));
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(celulaPadrao);
        }
        tabela.getColumnModel().getColumn(0).setPreferredWidth(20);

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
                if (colunaPesquisa.getSelectedItem() == "data de nascimento") {
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
        pesquisaTexto.setBounds(425, 200, 150, 25);
        pesquisaTexto.setFont(new Font("Inter", Font.PLAIN, 16));
        filtrosPesquisa.add(pesquisaTexto);

        pesquisaData = new JFormattedTextField(createFormatter("##/##/####"));
        pesquisaData.setBounds(425, 200, 150, 25);
        pesquisaData.setFont(new Font("Inter", Font.PLAIN, 16));
        pesquisaData.setBorder(new RoundedBorder(1, 1));
        pesquisaData.setVisible(false);
        filtrosPesquisa.add(pesquisaData);

        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setFont(new Font("Inter", Font.BOLD, 16));
        botaoPesquisar.setBounds(450, 240, 100, 30);
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
                    if (coluna.equals("descrição")) {
                        coluna = "descricao";
                    }
                    modeloTabela.setDados(dao.pesquisaColuna(busca, coluna));
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
