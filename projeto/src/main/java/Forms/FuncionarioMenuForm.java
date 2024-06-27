package Forms;
import DTO.Funcionario;
import Forms.FuncoesFuncionario.*;
import Forms.utils.RoundedBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FuncionarioMenuForm extends JFrame {
    private Container c;
    private JButton gerenciarPacientes;
    private JButton gerenciarFuncionarios;
    private JButton gerenciarExames;
    private JButton gerenciarResultados;
    private JButton gerenciarAgendamentos;
    private JLabel funcLogado;

    public FuncionarioMenuForm(Funcionario logado) {
        setTitle("Menu funcionário");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        JPanel p1 = new JPanel(null);
        p1.setBackground(new Color(255, 255, 232));
        p1.setSize(900, 125);
        p1.setLocation(0, 0);
        JLabel title = new JLabel("Menu - Funcionários", SwingConstants.CENTER);
        title.setLocation(250, 40);
        title.setSize(400, 40);
        title.setFont(new Font("Inter", Font.BOLD, 22));
        title.setForeground(new Color(43, 37, 93, 255));
        title.setVisible(true);
        p1.add(title);

        JButton botaoVoltar = new JButton("voltar");
        botaoVoltar.setFont(new Font("Inter", Font.PLAIN, 16));
        botaoVoltar.setBounds(50, 30, 80, 65);
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
                MenuForm menu = new MenuForm();
            }
        });
        p1.add(botaoVoltar);

        JLabel labelLogado = new JLabel("Funcionário:", SwingConstants.CENTER);
        labelLogado.setSize(150, 25);
        labelLogado.setLocation(700, 35);
        labelLogado.setFont(new Font("Inter", Font.PLAIN, 14));
        labelLogado.setForeground(new Color(43, 37, 93, 255));
        labelLogado.setVisible(true);
        p1.add(labelLogado);

        funcLogado = new JLabel(logado.getNome(), SwingConstants.CENTER);
        funcLogado.setSize(150, 25);
        funcLogado.setLocation(700, 55);
        funcLogado.setFont(new Font("Inter", Font.PLAIN, 14));
        funcLogado.setForeground(new Color(43, 37, 93, 255));
        funcLogado.setVisible(true);
        p1.add(funcLogado);
        c.add(p1);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 400);
        p2.setLocation(0, 125);
        p2.setVisible(true);

        ClassLoader cldr = this.getClass().getClassLoader();
        java.net.URL pacienteImageURL = cldr.getResource("forms/utils/gp.png");
        JLabel gp = new JLabel(new ImageIcon(pacienteImageURL));
        gp.setLocation(300, 4);
        gp.setSize(32, 32);
        p2.add(gp);


        gerenciarPacientes = new JButton("Gerenciar pacientes");
        gerenciarPacientes.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarPacientes.setBounds(340, 0, 220, 40);
        gerenciarPacientes.setContentAreaFilled(false);
        gerenciarPacientes.setFocusPainted(false);
        gerenciarPacientes.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarPacientes.setForeground(new Color(255, 255, 232));
        gerenciarPacientes.setBorder(new RoundedBorder(10, 3));
        gerenciarPacientes.setForeground(new Color(43, 37, 93, 191));
        gerenciarPacientes.setOpaque(false);
        gerenciarPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gerenciarPacientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
                gerenciarPacientes.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                gerenciarPacientes.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                gerenciarPacientes.setForeground(new Color(43, 37, 93, 191));
            }
        });
        gerenciarPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                GerenciarPacientes telaGerenciarPacientes = new GerenciarPacientes(logado);
            }
        });

        p2.add(gerenciarPacientes);

        java.net.URL funcImageURL = cldr.getResource("forms/utils/gf.png");
        JLabel gf = new JLabel(new ImageIcon(funcImageURL));
        gf.setLocation(300, 69);
        gf.setSize(32, 32);
        p2.add(gf);

        gerenciarFuncionarios = new JButton("Gerenciar funcionários");
        gerenciarFuncionarios.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarFuncionarios.setBounds(340, 65, 220, 40);
        gerenciarFuncionarios.setContentAreaFilled(false);
        gerenciarFuncionarios.setFocusPainted(false);
        gerenciarFuncionarios.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarFuncionarios.setForeground(new Color(255, 255, 232));
        gerenciarFuncionarios.setBorder(new RoundedBorder(10, 3));
        gerenciarFuncionarios.setForeground(new Color(43, 37, 93, 191));
        gerenciarFuncionarios.setOpaque(false);
        gerenciarFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gerenciarFuncionarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
                gerenciarFuncionarios.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                gerenciarFuncionarios.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                gerenciarFuncionarios.setForeground(new Color(43, 37, 93, 191));
            }
        });
        p2.add(gerenciarFuncionarios);
        gerenciarFuncionarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                GerenciarFuncionarios telaGerenciarFuncionarios = new GerenciarFuncionarios(logado);
            }
        });

        java.net.URL exImageURL = cldr.getResource("forms/utils/ge.png");
        JLabel ge = new JLabel(new ImageIcon(exImageURL));
        ge.setLocation(300, 134);
        ge.setSize(32, 32);
        p2.add(ge);

        gerenciarExames = new JButton("Gerenciar exames");
        gerenciarExames.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarExames.setBounds(340, 130, 220, 40);
        gerenciarExames.setContentAreaFilled(false);
        gerenciarExames.setFocusPainted(false);
        gerenciarExames.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarExames.setForeground(new Color(255, 255, 232));
        gerenciarExames.setBorder(new RoundedBorder(10, 3));
        gerenciarExames.setForeground(new Color(43, 37, 93, 191));
        gerenciarExames.setOpaque(false);
        gerenciarExames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gerenciarExames.setCursor(new Cursor(Cursor.HAND_CURSOR));
                gerenciarExames.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                gerenciarExames.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                gerenciarExames.setForeground(new Color(43, 37, 93, 191));
            }
        });
        gerenciarExames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                GerenciarExames telaGerenciarExames = new GerenciarExames(logado);
            }
        });

        p2.add(gerenciarExames);

        java.net.URL rImageURL = cldr.getResource("forms/utils/gr.png");
        JLabel gr = new JLabel(new ImageIcon(rImageURL));
        gr.setLocation(300, 199);
        gr.setSize(32, 32);
        p2.add(gr);

        gerenciarResultados = new JButton("Gerenciar resultados");
        gerenciarResultados.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarResultados.setBounds(340, 195, 220, 40);
        gerenciarResultados.setContentAreaFilled(false);
        gerenciarResultados.setFocusPainted(false);
        gerenciarResultados.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarResultados.setHorizontalAlignment(SwingConstants.CENTER);
        gerenciarResultados.setVerticalAlignment(SwingConstants.CENTER);
        gerenciarResultados.setForeground(new Color(255, 255, 232));
        gerenciarResultados.setBorder(new RoundedBorder(10, 3));
        gerenciarResultados.setForeground(new Color(43, 37, 93, 191));
        gerenciarResultados.setOpaque(false);
        gerenciarResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gerenciarResultados.setCursor(new Cursor(Cursor.HAND_CURSOR));
                gerenciarResultados.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                gerenciarResultados.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                gerenciarResultados.setForeground(new Color(43, 37, 93, 191));
            }
        });
        gerenciarResultados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                GerenciarResultados telaGerenciarResultados = new GerenciarResultados(logado);
            }
        });

        p2.add(gerenciarResultados);

        java.net.URL aImageURL = cldr.getResource("forms/utils/ga.png");
        JLabel ga = new JLabel(new ImageIcon(aImageURL));
        ga.setLocation(300, 264);
        ga.setSize(32, 32);
        p2.add(ga);

        gerenciarAgendamentos = new JButton("Gerenciar agendamentos");
        gerenciarAgendamentos.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarAgendamentos.setBounds(340, 260, 220, 40);
        gerenciarAgendamentos.setContentAreaFilled(false);
        gerenciarAgendamentos.setFocusPainted(false);
        gerenciarAgendamentos.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarAgendamentos.setHorizontalAlignment(SwingConstants.CENTER);
        gerenciarAgendamentos.setVerticalAlignment(SwingConstants.CENTER);
        gerenciarAgendamentos.setForeground(new Color(255, 255, 232));
        gerenciarAgendamentos.setBorder(new RoundedBorder(10, 3));
        gerenciarAgendamentos.setForeground(new Color(43, 37, 93, 191));
        gerenciarAgendamentos.setOpaque(false);
        gerenciarAgendamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gerenciarAgendamentos.setCursor(new Cursor(Cursor.HAND_CURSOR));
                gerenciarAgendamentos.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                gerenciarAgendamentos.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                gerenciarAgendamentos.setForeground(new Color(43, 37, 93, 191));
            }
        });
        gerenciarAgendamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                GerenciarAgendamentos telaGerenciarAgendamentos = new GerenciarAgendamentos(logado);
            }
        });
        p2.add(gerenciarAgendamentos);


        c.add(p2);
        setVisible(true);
    }
}
