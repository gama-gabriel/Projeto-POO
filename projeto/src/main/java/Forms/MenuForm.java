package Forms;

import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends javax.swing.JFrame {
    private Container c;
    private JButton pacienteBtn;
    private JButton funcBtn;
    private JLabel pacienteImg;
    private JLabel funcImg;


    public MenuForm() {
        setTitle("Menu");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        JPanel p1 = new JPanel(new GridBagLayout());
        p1.setBackground(new Color(255, 255, 232));
        p1.setSize(900, 125);
        p1.setLocation(0, 0);
        JLabel title = new JLabel("Menu principal");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        ClassLoader cldr = this.getClass().getClassLoader();
        java.net.URL pacienteImageURL = cldr.getResource("forms/utils/paciente.png");
        pacienteImg = new JLabel(new ImageIcon(pacienteImageURL));
        pacienteImg.setLocation(175, 25);

        pacienteImg.setSize(200, 100);
        p2.add(pacienteImg);

        pacienteBtn = new JButton("Entrar como paciente");
        pacienteBtn.setBorder(new RoundedBorder(20, 2));
        pacienteBtn.setForeground(new Color(43, 37, 93, 191));
        pacienteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pacienteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                pacienteBtn.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                pacienteBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                pacienteBtn.setForeground(new Color(43, 37, 93, 191));
            }
        });
        pacienteBtn.setContentAreaFilled(false);
        pacienteBtn.setFocusPainted(false);
        pacienteBtn.setFont(new Font("Inter", Font.BOLD, 16));
        pacienteBtn.setSize(250, 45);
        pacienteBtn.setLocation(150, 120);
        p2.add(pacienteBtn);

        java.net.URL funcImageURL = cldr.getResource("Forms/utils/funcionario.png");
        funcImg = new JLabel(new ImageIcon(funcImageURL));
        funcImg.setLocation(525, 20);
        funcImg.setSize(200, 100);
        p2.add(funcImg);

        funcBtn = new JButton("Entrar como funcionário");
        funcBtn.setBorder(new RoundedBorder(20, 2));
        funcBtn.setForeground(new Color(43, 37, 93, 191));
        funcBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                funcBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                funcBtn.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                funcBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                funcBtn.setForeground(new Color(43, 37, 93, 191));
            }
        });
        funcBtn.setContentAreaFilled(false);
        funcBtn.setFocusPainted(false);
        funcBtn.setFont(new Font("Inter", Font.BOLD, 16));
        funcBtn.setSize(250, 45);
        funcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                FuncionarioLoginForm telaLoginFunc = new FuncionarioLoginForm();
            }
        });
        funcBtn.setLocation(500, 120);
        p2.add(funcBtn);

        c.add(p2);
        pacienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                PacienteLogin telaPaciente = new PacienteLogin();
            }
        });


        setVisible(true);
    }
}