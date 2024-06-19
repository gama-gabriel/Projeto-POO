package Forms;
import Forms.utils.RoundedBorder;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class FuncionarioLoginForm extends JFrame {
    private Container c;
    private JButton pacienteBtn;
    private JButton funcBtn;
    private JLabel funcImg;
    private JLabel labelLogin;
    private JTextArea textoLogin;
    private JLabel labelSenha;
    private JPasswordField textoSenha;
    private JButton botaoLogar;
    private JButton botaoSemCadastro;

    public FuncionarioLoginForm() {
        setTitle("Login funcionário");
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
        JLabel title = new JLabel("Login de funcionários");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        labelLogin = new JLabel("Login (e-mail):");
        labelLogin.setSize(200, 40);
        labelLogin.setLocation(325, 50);
        labelLogin.setFont(new Font("Inter", Font.BOLD, 16));
        labelLogin.setForeground(new Color(43, 37, 93, 255));
        labelLogin.setVisible(true);
        p2.add(labelLogin);

        textoLogin = new JTextArea() {
            @Override
            public void paint(Graphics g) {
                Dimension size = getSize();
                g.setColor(getBackground());
                g.fillRect(0, 0, size.width, size.height);
                super.paint(g);
            }
        };
        textoLogin.setSize(250, 30);
        textoLogin.setLocation(325, 90);
        textoLogin.setOpaque(false);
        textoLogin.setFont(new Font("Inter", Font.PLAIN, 14));
        textoLogin.setForeground(new Color(43, 37, 93, 255));
        textoLogin.setMargin(new Insets(5, 2, 5, 5));
        textoLogin.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textoLogin.setBackground(new Color(0,0,0, 12));
            }

            @Override
            public void focusLost(FocusEvent e) {
                textoLogin.setBackground(new Color(0,0,0,0));
            }
        });
        textoLogin.setVisible(true);
        p2.add(textoLogin);

        JTextArea border = new JTextArea();
        border.setSize(252, 32);
        border.setLocation(324, 89);
        border.setForeground(new Color(43, 37, 93, 255));
        border.setBorder(new RoundedBorder(1,1));
        border.setEnabled(false);
        border.setVisible(true);
        p2.add(border);

        labelSenha = new JLabel("Senha:");
        labelSenha.setSize(200, 40);
        labelSenha.setLocation(325, 130);
        labelSenha.setFont(new Font("Inter", Font.BOLD, 16));
        labelSenha.setForeground(new Color(43, 37, 93, 255));
        labelSenha.setVisible(true);
        p2.add(labelSenha);

        textoSenha = new JPasswordField() {
            @Override
            public void paint(Graphics g) {
                Dimension size = getSize();
                g.setColor(getBackground());
                g.fillRect(0, 0, size.width, size.height);
                super.paint(g);
            }
        };
        textoSenha.setSize(250, 30);
        textoSenha.setLocation(325, 170);
        textoSenha.setOpaque(false);
        textoSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(), // No outer border
            new EmptyBorder(5, 2, 5, 5)   // Margin of 10 pixels on all sides
        ));
        textoSenha.setFont(new Font("Inter", Font.PLAIN, 14));
        textoSenha.setForeground(new Color(43, 37, 93, 255));
        textoSenha.setMargin(new Insets(5, 2, 10, 5));
        textoSenha.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textoSenha.setBackground(new Color(0,0,0, 12));
            }

            @Override
            public void focusLost(FocusEvent e) {
                textoSenha.setBackground(new Color(0,0,0,0));
            }
        });
        textoSenha.setVisible(true);
        p2.add(textoSenha);

        JTextArea border2 = new JTextArea();
        border2.setSize(252, 32);
        border2.setLocation(324, 169);
        border2.setForeground(new Color(43, 37, 93, 255));
        border2.setBorder(new RoundedBorder(1,1));
        border2.setEnabled(false);
        border2.setVisible(true);
        p2.add(border2);

        botaoSemCadastro = new JButton();
        botaoSemCadastro.setContentAreaFilled(false);
        botaoSemCadastro.setFocusPainted(false);
        botaoSemCadastro.setSize(140, 30);
        botaoSemCadastro.setLocation(325, 205);
        botaoSemCadastro.setText("Não estou cadastrado");
        botaoSemCadastro.setFont(new Font("Inter", Font.PLAIN, 14));
        botaoSemCadastro.setForeground(new Color(43, 37, 93, 191));
        botaoSemCadastro.setBorder((javax.swing.BorderFactory.createEmptyBorder()));
        botaoSemCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoSemCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
                botaoSemCadastro.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoSemCadastro.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                botaoSemCadastro.setForeground(new Color(43, 37, 93, 233));
            }
        });
        botaoSemCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                FuncionarioLoginForm telaLoginFunc = new FuncionarioLoginForm();
            }
        });
        botaoSemCadastro.setVisible(true);
        p2.add(botaoSemCadastro);

        botaoLogar = new JButton();
        botaoLogar.setContentAreaFilled(false);
        botaoLogar.setFocusPainted(false);
        botaoLogar.setSize(250, 50);
        botaoLogar.setLocation(325, 255);
        botaoLogar.setText("Entrar");
        botaoLogar.setFont(new Font("Inter", Font.BOLD, 16));
        botaoLogar.setForeground(new Color(255, 255, 232));
        botaoLogar.setBorder(new RoundedBorder(10, 3));
        botaoLogar.setForeground(new Color(43, 37, 93, 191));
        botaoLogar.setOpaque(false);
        botaoLogar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoLogar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                botaoLogar.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoLogar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                botaoLogar.setForeground(new Color(43, 37, 93, 191));
            }
        });
        botaoLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                FuncionarioLoginForm telaLoginFunc = new FuncionarioLoginForm();
            }
        });
        botaoLogar.setVisible(true);
        p2.add(botaoLogar);

        c.add(p2);
        setVisible(true);
    }
}