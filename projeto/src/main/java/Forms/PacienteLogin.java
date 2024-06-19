package Forms;

import DAO.PacienteDAO;
import DTO.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacienteLogin extends JFrame {
    private Container c;

    public PacienteLogin() {
        setTitle("Paciente");
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
        JLabel title = new JLabel("Login de Pacientes");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        emailLabel.setBounds(200, 50, 100, 30);
        p2.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Inter", Font.PLAIN, 16));
        emailField.setBounds(300, 50, 300, 30);
        p2.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        senhaLabel.setBounds(200, 100, 100, 30);
        p2.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setFont(new Font("Inter", Font.PLAIN, 16));
        senhaField.setBounds(300, 100, 300, 30);
        p2.add(senhaField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Inter", Font.BOLD, 16));
        loginBtn.setBounds(300, 150, 300, 30);
        p2.add(loginBtn);

        JLabel cadastroLabel = new JLabel("<html><u>Cadastre-se</u></html>");
        cadastroLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        cadastroLabel.setForeground(Color.BLUE);
        cadastroLabel.setBounds(400, 200, 100, 30);
        cadastroLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        p2.add(cadastroLabel);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                Paciente aux = new Paciente();

                Paciente PacLogado = aux.autenticacao(email, senha);

                if (PacLogado.getId() != 0) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                }
            }
        });
        cadastroLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(null, "Abrir tela de cadastro");
            }
        });

        c.add(p2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new PacienteLogin();
    }
}