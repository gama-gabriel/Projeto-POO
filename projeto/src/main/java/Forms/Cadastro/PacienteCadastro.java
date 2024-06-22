package Forms.Cadastro;

import DAO.PacienteDAO;
import DTO.Paciente;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PacienteCadastro extends JFrame {
    public Container c;
   public JLabel title;
    public JFormattedTextField cpfField;
    public JTextField emailField;
    public JTextField nomeField;
    public JFormattedTextField dataNascimentoField;
    public JPasswordField senhaField;
    public JPasswordField confirmarSenhaField;
    public JButton cadastrarButton;
    public JLabel senhaLabel;
    public JLabel confirmarSenhaLabel;
    public JPanel p2;
    public PacienteCadastro() {
        setTitle("Cadastro de Usuário");
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
        title = new JLabel("Cadastro de Pacientes");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        cpfLabel.setBounds(244, 50, 100, 30);
        p2.add(cpfLabel);

        cpfField = new JFormattedTextField(createFormatter("###.###.###-##"));
        cpfField.setFont(new Font("Inter", Font.PLAIN, 16));
        cpfField.setBounds(514, 50, 130, 30);
        p2.add(cpfField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        emailLabel.setBounds(244, 100, 100, 30);
        p2.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Inter", Font.PLAIN, 16));
        emailField.setBounds(344, 100, 300, 30);
        p2.add(emailField);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        nomeLabel.setBounds(244, 150, 100, 30);
        p2.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setFont(new Font("Inter", Font.PLAIN, 16));
        nomeField.setBounds(344, 150, 300, 30);
        p2.add(nomeField);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento:");
        dataNascimentoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        dataNascimentoLabel.setBounds(244, 200, 150, 30);
        p2.add(dataNascimentoLabel);

        dataNascimentoField = new JFormattedTextField(createFormatter("##/##/####"));
        dataNascimentoField.setFont(new Font("Inter", Font.PLAIN, 16));
        dataNascimentoField.setBounds(544, 200, 100, 30);
        p2.add(dataNascimentoField);

        senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        senhaLabel.setBounds(244, 250, 100, 30);
        p2.add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setFont(new Font("Inter", Font.PLAIN, 16));
        senhaField.setBounds(394, 250, 250, 30);
        p2.add(senhaField);

        confirmarSenhaLabel = new JLabel("Confirmar Senha:");
        confirmarSenhaLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        confirmarSenhaLabel.setBounds(244, 300, 150, 30);
        p2.add(confirmarSenhaLabel);

        confirmarSenhaField = new JPasswordField();
        confirmarSenhaField.setFont(new Font("Inter", Font.PLAIN, 16));
        confirmarSenhaField.setBounds(394, 300, 250, 30);
        p2.add(confirmarSenhaField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(new Font("Inter", Font.BOLD, 16));
        cadastrarButton.setBounds(300, 350, 300, 30);
        p2.add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    Paciente novoUsuario = new Paciente();
                    novoUsuario.setCpf(cpfField.getText());
                    novoUsuario.setAtivo(true);
                    novoUsuario.setNome(nomeField.getText());
                    novoUsuario.setEmail(emailField.getText());
                    novoUsuario.setSenha(new String(senhaField.getPassword()));
                    // Novela de colocar data no sql:
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        novoUsuario.setDataNascimento(sdf.parse(dataNascimentoField.getText()));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Data de nascimento inválida, informação não inserida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    novoUsuario.UseService();
                    novoUsuario.dao.inserir(novoUsuario);
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                }
            }
        });

        c.add(p2);

        setVisible(true);
        // System.out.println(title.getX() - cpfLabel.getX());

    }

    private boolean validarCampos() {
        PacienteDAO validador = new PacienteDAO();
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String nome = nomeField.getText();
        String dataNascimento = dataNascimentoField.getText();
        String senha = new String(senhaField.getPassword());
        String confirmarSenha = new String(confirmarSenhaField.getPassword());

        if (cpf.isEmpty() || email.isEmpty() || nome.isEmpty() || dataNascimento.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidDate(dataNascimento)) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(validador.pesquisaCpf(cpf)){
            JOptionPane.showMessageDialog(this, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(validador.pesquisaEmail(email)){
            JOptionPane.showMessageDialog(this, "Email já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    public static void main(String[] args) {
        new PacienteCadastro();
    }
}
