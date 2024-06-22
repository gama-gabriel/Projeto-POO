package Forms.Cadastro;
import DTO.Funcionario;
import DTO.Resultado;
import Forms.FuncoesFuncionario.GerenciarResultados;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultadoCadastro extends JFrame {
    public Container c;
    public JLabel title;
    public JTextField descricaoField;
    public JButton cadastrarButton;
    public JPanel p2;
    public ResultadoCadastro(Funcionario logado) {
        setTitle("Cadastro de Resultado");
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
        title = new JLabel("Cadastro de Resultados");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        descricaoLabel.setBounds(244, 60, 100, 30);
        p2.add(descricaoLabel);

        descricaoField = new JTextField();
        descricaoField.setFont(new Font("Inter", Font.PLAIN, 16));
        descricaoField.setBounds(344, 50, 300, 50);
        p2.add(descricaoField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(375, 120, 150, 40);
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
                    Resultado novoResultado = new Resultado();
                    novoResultado.setDescricao(descricaoField.getText());
                    novoResultado.UseService();
                    novoResultado.dao.inserir(novoResultado);
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    c.setVisible(false);
                    dispose();
                    GerenciarResultados telaGerenciarResultados = new GerenciarResultados(logado);
                }
            }
        });

        c.add(p2);
        setVisible(true);
    }

    public boolean validarCampos() {
        String descricao = descricaoField.getText();

        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        new ResultadoCadastro(new Funcionario());
    }
}
