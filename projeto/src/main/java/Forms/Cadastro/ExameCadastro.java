package Forms.Cadastro;
import DTO.Exame;
import DTO.Funcionario;
import Forms.FuncoesFuncionario.GerenciarExames;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExameCadastro extends JFrame {
    public Container c;
    public JLabel title;
    public JTextField nomeField;
    public JTextField descricaoField;
    public JTextField preparoField;
    public JTextField instrucoesField;
    public JButton cadastrarButton;
    public JPanel p2;
    public ExameCadastro(Funcionario logado) {
        setTitle("Cadastro de Exame");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        JPanel p1 = new JPanel(new GridBagLayout());
        p1.setBackground(new Color(255, 255, 232));
        p1.setSize(900, 125);
        p1.setLocation(0, 0);
        title = new JLabel("Cadastro de Exames");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        nomeLabel.setBounds(244, 50, 100, 30);
        p2.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setFont(new Font("Inter", Font.PLAIN, 16));
        nomeField.setBounds(344, 50, 300, 30);
        p2.add(nomeField);

        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        descricaoLabel.setBounds(244, 100, 100, 30);
        p2.add(descricaoLabel);

        descricaoField = new JTextField();
        descricaoField.setFont(new Font("Inter", Font.PLAIN, 16));
        descricaoField.setBounds(344, 100, 300, 50);
        p2.add(descricaoField);

        JLabel preparoLabel = new JLabel("Preparo:");
        preparoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        preparoLabel.setBounds(244, 170, 100, 30);
        p2.add(preparoLabel);

        preparoField = new JTextField();
        preparoField.setFont(new Font("Inter", Font.PLAIN, 16));
        preparoField.setBounds(344, 170, 300, 50);
        p2.add(preparoField);

        JLabel instrucoesLabel = new JLabel("Instruções:");
        instrucoesLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        instrucoesLabel.setBounds(244, 240, 100, 30);
        p2.add(instrucoesLabel);

        instrucoesField = new JTextField();
        instrucoesField.setFont(new Font("Inter", Font.PLAIN, 16));
        instrucoesField.setBounds(344, 240, 300, 50);
        p2.add(instrucoesField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(375, 310, 150, 40);
        cadastrarButton.setContentAreaFilled(false);
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setFont(new Font("Inter", Font.BOLD, 16));
        cadastrarButton.setForeground(new Color(255, 255, 232));
        cadastrarButton.setBorder(new RoundedBorder(10, 2));
        cadastrarButton.setForeground(new Color(43, 37, 93, 191));
        cadastrarButton.setOpaque(false);
        cadastrarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cadastrarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cadastrarButton.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cadastrarButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                cadastrarButton.setForeground(new Color(43, 37, 93, 191));
            }
        });
        p2.add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    Exame novoExame = new Exame();
                    novoExame.setNome(nomeField.getText());
                    novoExame.setDisponivel(true);
                    novoExame.setDescricao(descricaoField.getText());
                    novoExame.setPreparo(preparoField.getText());
                    novoExame.setInstrucoesPos(instrucoesField.getText());
                    novoExame.UseService();
                    novoExame.dao.inserir(novoExame);
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    c.setVisible(false);
                    dispose();
                    GerenciarExames telaGerenciarExames = new GerenciarExames(logado);
                }
            }
        });

        c.add(p2);
        setVisible(true);
    }

    public boolean validarCampos() {
        String descricao = descricaoField.getText();
        String preparo = preparoField.getText();
        String nome = nomeField.getText();
        String instrucoes = instrucoesField.getText();

        if (descricao.isEmpty() || preparo.isEmpty() || nome.isEmpty() || instrucoes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        new ExameCadastro(new Funcionario());
    }
}
