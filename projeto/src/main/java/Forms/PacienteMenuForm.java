package Forms;

import DTO.Paciente;
import Forms.FuncoesPaciente.CancelarAgendamento;
import Forms.FuncoesPaciente.ListarAgendamento;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Forms.FuncoesPaciente.MarcarAgendamento;

public class PacienteMenuForm extends JFrame {
    private Container c;
    private JLabel pacienteLogado;

    public PacienteMenuForm(Paciente logado) {
        setTitle("Menu Paciente");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        // Painel superior
        JPanel p1 = new JPanel(null);
        p1.setBackground(new Color(255, 255, 232));
        p1.setSize(900, 125);
        p1.setLocation(0, 0);
        JLabel title = new JLabel("Menu - Paciente", SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(300, 40);
        title.setFont(new Font("Inter", Font.BOLD, 30));
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

        JLabel labelLogado = new JLabel("Paciente:", SwingConstants.CENTER);
        labelLogado.setSize(150, 25);
        labelLogado.setLocation(700, 35);
        labelLogado.setFont(new Font("Inter", Font.PLAIN, 14));
        labelLogado.setForeground(new Color(43, 37, 93, 255));
        labelLogado.setVisible(true);
        p1.add(labelLogado);

        pacienteLogado = new JLabel(logado.getNome(), SwingConstants.CENTER);
        pacienteLogado.setSize(150, 25);
        pacienteLogado.setLocation(700, 55);
        pacienteLogado.setFont(new Font("Inter", Font.PLAIN, 14));
        pacienteLogado.setForeground(new Color(43, 37, 93, 255));
        pacienteLogado.setVisible(true);
        p1.add(pacienteLogado);

        c.add(p1);

        // Painel inferior
        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 400);
        p2.setLocation(0, 125);
        p2.setVisible(true);

        adicionarOpcao(p2, "Marcar consulta", "forms/utils/marca-consulta.png", 50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                MarcarAgendamento exameForm = new MarcarAgendamento(logado);
            }
        });
        adicionarOpcao(p2, "Listar consultas", "forms/utils/listar-consulta.png", 150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                ListarAgendamento exameForm = new ListarAgendamento(logado);
            }
        });
        adicionarOpcao(p2, "Cancelar consultas", "forms/utils/cancelar.png", 250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.setVisible(false);
                dispose();
                CancelarAgendamento exameForm = new CancelarAgendamento(logado);
            }
        });

        c.add(p2);

        setVisible(true);
    }

    private void adicionarOpcao(JPanel panel, String texto, String caminhoImagem, int y, ActionListener action) {
        int panelWidth = panel.getWidth();

        JLabel label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(caminhoImagem)));
        label.setSize(36, 36);
        int labelX = (panelWidth - 80 - 250) / 2;
        label.setLocation(labelX, y);
        panel.add(label);

        JButton button = new JButton(texto);
        button.setBorder(new RoundedBorder(20, 2));
        button.setForeground(new Color(43, 37, 93, 191));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Inter", Font.BOLD, 16));
        button.setSize(250, 45);
        int buttonX = labelX + 50;
        button.setLocation(buttonX, y);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                button.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                button.setForeground(new Color(43, 37, 93, 191));
            }
        });

        button.addActionListener(action);

        panel.add(button);
    }

    public static void main(String[] args) {
        Paciente pac = new Paciente();
        pac.setNome("Paciente Exemplo");
        new PacienteMenuForm(pac);
    }
}