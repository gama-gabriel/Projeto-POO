package Forms;
import DTO.Funcionario;
import Forms.FuncoesFuncionario.GerenciarExames;
import Forms.FuncoesFuncionario.GerenciarFuncionarios;
import Forms.FuncoesFuncionario.GerenciarPacientes;
import Forms.utils.RoundedBorder;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class FuncionarioMenuForm extends JFrame {
    private Container c;
    private JButton gerenciarPacientes;
    private JButton gerenciarFuncionarios;
    private JButton gerenciarExames;
    private JButton gerenciarResultados;
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
        title.setLocation(300, 40);
        title.setSize(300, 40);
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        title.setVisible(true);
        p1.add(title);

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

        gerenciarPacientes = new JButton("<html><div style='text-align: center;'>Gerenciar<br>pacientes</div></html>");
        gerenciarPacientes.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarPacientes.setBounds(60, 105, 150, 70);
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

        gerenciarFuncionarios = new JButton("<html><div style='text-align: center;'>Gerenciar<br>funcionários</div></html>");
        gerenciarFuncionarios.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarFuncionarios.setBounds(270, 105, 150, 70);
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

        gerenciarExames = new JButton("<html><div style='text-align: center;'>Gerenciar<br>exames</div></html>");
        gerenciarExames.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarExames.setBounds(480, 105, 150, 70);
        gerenciarExames.setContentAreaFilled(false);
        gerenciarExames.setFocusPainted(false);
        gerenciarExames.setFont(new Font("Inter", Font.BOLD, 16));
//        gerenciarExames.setHorizontalAlignment(SwingConstants.CENTER);
//        gerenciarExames.setVerticalAlignment(SwingConstants.CENTER);
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

        gerenciarResultados = new JButton("<html><div style='text-align: center;'>Gerenciar<br>resultados</div></html>");
        gerenciarResultados.setFont(new Font("Inter", Font.BOLD, 16));
        gerenciarResultados.setBounds(690, 105, 150, 70);
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
        p2.add(gerenciarResultados);

        c.add(p2);
        setVisible(true);
    }
}
