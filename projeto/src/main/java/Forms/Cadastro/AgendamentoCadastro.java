package Forms.Cadastro;

import DAO.ExameDAO;
import DAO.FuncionarioDAO;
import DAO.PacienteDAO;
import DTO.Agendamento;
import DTO.Funcionario;
import DTO.Paciente;
import Forms.Cadastro.PacienteCadastro;
import Forms.FuncionarioMenuForm;
import Forms.FuncoesFuncionario.GerenciarAgendamentos;
import Forms.utils.RoundedBorder;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class AgendamentoCadastro extends JFrame {
    public Container c;
    public JLabel title;
    public JTextField pacienteField;
    public JTextField funcionarioField;
    public JFormattedTextField dataField;
    public JFormattedTextField horaField;
    public JTextField exameField;
    public JButton cadastrarButton;
    public JLabel senhaLabel;
    public JLabel confirmarSenhaLabel;
    public JPanel p2;

    public AgendamentoCadastro(Funcionario logado) {
        setTitle("Cadastro de Agendamentos");
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
        title = new JLabel("Cadastro de Agendamentos");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        JLabel pacienteLabel = new JLabel("Paciente (id):");
        pacienteLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        pacienteLabel.setBounds(244, 0, 100, 30);
        p2.add(pacienteLabel);

        pacienteField = new JTextField();
        pacienteField.setFont(new Font("Inter", Font.PLAIN, 16));
        pacienteField.setBounds(500, 0, 50, 30);
        p2.add(pacienteField);

        JLabel funcionarioLabel = new JLabel("Funcionário responsável (id):");
        funcionarioLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        funcionarioLabel.setBounds(244, 50, 210, 30);
        p2.add(funcionarioLabel);

        funcionarioField = new JTextField();
        funcionarioField.setFont(new Font("Inter", Font.PLAIN, 16));
        funcionarioField.setBounds(500, 50, 50, 30);
        p2.add(funcionarioField);

        JLabel exameLabel = new JLabel("Exame (id):");
        exameLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        exameLabel.setBounds(244, 100, 100, 30);
        p2.add(exameLabel);

        exameField = new JTextField();
        exameField.setFont(new Font("Inter", Font.PLAIN, 16));
        exameField.setBounds(500, 100, 50, 30);
        p2.add(exameField);

        JLabel dataLabel = new JLabel("Data:");
        dataLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        dataLabel.setBounds(244, 150, 50, 30);
        p2.add(dataLabel);

        dataField = new JFormattedTextField(createFormatter("##/##/####"));
        dataField.setFont(new Font("Inter", Font.PLAIN, 16));
        dataField.setBounds(294, 150, 100, 30);
        p2.add(dataField);

        JLabel horaLabel = new JLabel("Hora:");
        horaLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        horaLabel.setBounds(450, 150, 50, 30);
        p2.add(horaLabel);

        horaField = new JFormattedTextField(createFormatter("##:##"));
        horaField.setFont(new Font("Inter", Font.PLAIN, 16));
        horaField.setBounds(500, 150, 100, 30);
        p2.add(horaField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(new Font("Inter", Font.BOLD, 16));
        cadastrarButton.setBounds(385, 220, 130, 40);
        cadastrarButton.setContentAreaFilled(false);
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setFont(new Font("Inter", Font.BOLD, 16));
        cadastrarButton.setForeground(new Color(255, 255, 232));
        cadastrarButton.setBorder(new RoundedBorder(10, 3));
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
                    Agendamento novoAgendamento = new Agendamento();
                    // Novela de colocar data no sql:
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        String dataString = dataField.getText();
                        String horaString = horaField.getText();

                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                        LocalDate dataConvertida = LocalDate.parse(dataString, dateFormatter);
                        LocalTime horaConvertida = LocalTime.parse(horaString, timeFormatter);

                        LocalDateTime dataHora = LocalDateTime.of(dataConvertida, horaConvertida);
                        novoAgendamento.setDataHora(dataHora);

                        novoAgendamento.setPaciente(new PacienteDAO().pesquisar(Integer.parseInt(pacienteField.getText())));
                        novoAgendamento.setSupervisor(new FuncionarioDAO().pesquisar(Integer.parseInt(funcionarioField.getText())));
                        novoAgendamento.setExame(new ExameDAO().pesquisar(Integer.parseInt(exameField.getText())));
                        novoAgendamento.setCancelado(false);
                        novoAgendamento.UseService();
                        novoAgendamento.dao.inserir(novoAgendamento);
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                        c.setVisible(false);
                        dispose();
                        GerenciarAgendamentos telaGerenciarAgendamentos = new GerenciarAgendamentos(logado);
                    } catch (DateTimeParseException ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Data e hora inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception err) {
                        System.out.println("stack trace: ");
                        System.out.println(err.getStackTrace());

                        System.out.println("mensagem: ");
                        System.out.println(err.getMessage());
                    }

                }
            }
        });

        c.add(p2);

        setVisible(true);
    }
public AgendamentoCadastro(Paciente logado) {
        setTitle("Cadastro de Agendamentos");
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
        title = new JLabel("Cadastro de Agendamentos");
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        p1.add(title);
        c.add(p1);

        p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 450);
        p2.setLocation(0, 125);

        JLabel pacienteLabel = new JLabel("Paciente (id):");
        pacienteLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        pacienteLabel.setBounds(244, 0, 100, 30);
        p2.add(pacienteLabel);

        pacienteField = new JTextField();
        pacienteField.setFont(new Font("Inter", Font.PLAIN, 16));
        pacienteField.setBounds(500, 0, 50, 30);
        pacienteField.setText(String.valueOf(logado.getId()));
        pacienteField.setEnabled(false);
        p2.add(pacienteField);

        JLabel funcionarioLabel = new JLabel("Funcionário responsável (id):");
        funcionarioLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        funcionarioLabel.setBounds(244, 50, 210, 30);
        p2.add(funcionarioLabel);

        funcionarioField = new JTextField();
        funcionarioField.setFont(new Font("Inter", Font.PLAIN, 16));
        funcionarioField.setBounds(500, 50, 50, 30);
        p2.add(funcionarioField);

        JLabel exameLabel = new JLabel("Exame (id):");
        exameLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        exameLabel.setBounds(244, 100, 100, 30);
        p2.add(exameLabel);

        exameField = new JTextField();
        exameField.setFont(new Font("Inter", Font.PLAIN, 16));
        exameField.setBounds(500, 100, 50, 30);
        p2.add(exameField);

        JLabel dataLabel = new JLabel("Data:");
        dataLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        dataLabel.setBounds(244, 150, 50, 30);
        p2.add(dataLabel);

        dataField = new JFormattedTextField(createFormatter("##/##/####"));
        dataField.setFont(new Font("Inter", Font.PLAIN, 16));
        dataField.setBounds(294, 150, 100, 30);
        p2.add(dataField);

        JLabel horaLabel = new JLabel("Hora:");
        horaLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        horaLabel.setBounds(450, 150, 50, 30);
        p2.add(horaLabel);

        horaField = new JFormattedTextField(createFormatter("##:##"));
        horaField.setFont(new Font("Inter", Font.PLAIN, 16));
        horaField.setBounds(500, 150, 100, 30);
        p2.add(horaField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(new Font("Inter", Font.BOLD, 16));
        cadastrarButton.setBounds(385, 220, 130, 40);
        cadastrarButton.setContentAreaFilled(false);
        cadastrarButton.setFocusPainted(false);
        cadastrarButton.setFont(new Font("Inter", Font.BOLD, 16));
        cadastrarButton.setForeground(new Color(255, 255, 232));
        cadastrarButton.setBorder(new RoundedBorder(10, 3));
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
                    Agendamento novoAgendamento = new Agendamento();
                    // Novela de colocar data no sql:
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        String dataString = dataField.getText();
                        String horaString = horaField.getText();

                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                        LocalDate dataConvertida = LocalDate.parse(dataString, dateFormatter);
                        LocalTime horaConvertida = LocalTime.parse(horaString, timeFormatter);

                        LocalDateTime dataHora = LocalDateTime.of(dataConvertida, horaConvertida);
                        novoAgendamento.setDataHora(dataHora);
                    } catch (DateTimeParseException ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Data e hora inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception err) {
                        System.out.println("stack trace: ");
                        System.out.println(err.getStackTrace());

                        System.out.println("mensagem: ");
                        System.out.println(err.getMessage());
                    }
                    novoAgendamento.setPaciente(new PacienteDAO().pesquisar(Integer.parseInt(pacienteField.getText())));
                    novoAgendamento.setSupervisor(new FuncionarioDAO().pesquisar(Integer.parseInt(funcionarioField.getText())));
                    novoAgendamento.setExame(new ExameDAO().pesquisar(Integer.parseInt(exameField.getText())));
                    novoAgendamento.setCancelado(false);
                    novoAgendamento.UseService();
                    novoAgendamento.dao.inserir(novoAgendamento);
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    c.setVisible(false);
                    dispose();
                    //abrir menu do paciente
                }
            }
        });

        c.add(p2);

        setVisible(true);
    }

    private boolean validarCampos() {
        PacienteDAO validadorPaciente = new PacienteDAO();
        FuncionarioDAO validadorFuncionario = new FuncionarioDAO();
        ExameDAO validadorExame = new ExameDAO();
        String pacienteString = pacienteField.getText();
        String funcionarioString = funcionarioField.getText();
        String exameString = exameField.getText();

        if (pacienteString.isEmpty() || funcionarioString.isEmpty() || exameString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int paciente = Integer.parseInt(pacienteString);
            int funcionario = Integer.parseInt(funcionarioString);
            int exame = Integer.parseInt(exameString);

            if (validadorPaciente.pesquisar(paciente).getId() == 0) {
                JOptionPane.showMessageDialog(this, String.format("O paciente de id %d não existe", paciente), "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (validadorFuncionario.pesquisar(funcionario).getId() == 0) {
                JOptionPane.showMessageDialog(this, String.format("O funcionário de id %d não existe", funcionario), "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (validadorExame.pesquisar(exame).getId() == 0) {
                JOptionPane.showMessageDialog(this, String.format("O exame de id %d não existe", exame), "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception err) {
            JOptionPane.showMessageDialog(this, "Os campos de paciente, funcionário e exame devem ser NÚMEROS INTEIROS", "Erro", JOptionPane.ERROR_MESSAGE);

        }
        return true;
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