package Forms;

import DTO.Paciente;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PacienteMenuForm extends JFrame {
    private Container c;
    private JLabel funcLogado;

    public PacienteMenuForm(Paciente logado) {
        setTitle("Menu Paciente");
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
        JLabel title = new JLabel("Menu - Paciente", SwingConstants.CENTER);
        title.setLocation(300, 40);
        title.setSize(300, 40);
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        title.setVisible(true);
        p1.add(title);

        JLabel labelLogado = new JLabel("Paciente:", SwingConstants.CENTER);
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

        adicionarOpcao(p2, "Marcar consulta", "forms/utils/marca-consulta.png", 50);
        adicionarOpcao(p2, "Listar consultas", "forms/utils/listar-consulta.png", 150);
        adicionarOpcao(p2, "Cancelar consultas", "forms/utils/cancelar.png", 250);

        c.add(p2);

        setVisible(true);
    }

    private void adicionarOpcao(JPanel panel, String texto, String caminhoImagem, int y) {
        int panelWidth = panel.getWidth();

        JLabel label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(caminhoImagem)));
        label.setSize(36, 36);
        int labelX = (panelWidth - 36 - 250) / 2;
        label.setLocation(labelX, y);
        panel.add(label);

        JButton button = new JButton(texto);
        button.setBorder(new RoundedBorder(20, 2));
        button.setForeground(new Color(43, 37, 93, 191));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                button.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                button.setForeground(new Color(43, 37, 93, 191));
            }
        });
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Inter", Font.BOLD, 16));
        button.setSize(250, 45);
        int buttonX = labelX + 50;
        button.setLocation(buttonX, y);

        panel.add(button);
    }

    static Paciente pac = new Paciente();

    public static void main(String[] args) {
        new PacienteMenuForm(pac);
    }
}
