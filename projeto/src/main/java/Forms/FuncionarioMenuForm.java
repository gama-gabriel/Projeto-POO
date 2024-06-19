package Forms;
import DTO.Funcionario;
import Forms.utils.RoundedBorder;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class FuncionarioMenuForm extends JFrame {
    private Container c;

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
        labelLogado.setFont(new Font("Inter", Font.PLAIN, 16));
        labelLogado.setForeground(new Color(43, 37, 93, 255));
        labelLogado.setVisible(true);
        p1.add(labelLogado);

        funcLogado = new JLabel(logado.getNome(), SwingConstants.CENTER);
        funcLogado.setSize(150, 25);
        funcLogado.setLocation(700, 55);
        funcLogado.setFont(new Font("Inter", Font.BOLD, 16));
        funcLogado.setForeground(new Color(43, 37, 93, 255));
        funcLogado.setVisible(true);
        p1.add(funcLogado);


        c.add(p1);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 400);
        p2.setLocation(0, 125);
        p2.setVisible(true);

        c.add(p2);
        setVisible(true);
    }
}
