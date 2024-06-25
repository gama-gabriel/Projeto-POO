package Forms.Cadastro;

import DAO.ExameDAO;
import DAO.FuncionarioDAO;
import DAO.PacienteDAO;
import DTO.Agendamento;
import DTO.Exame;
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
import java.util.List;

public class AgendamentoCadastro extends JFrame {
    public Container c;
    public JLabel title;
    public JTextField pacienteField;
    public JTextField funcionarioField;
    public JComboBox<String> nomeMedico;
    public List<Funcionario> medicosDisponiveis;
    public FuncionarioDAO finderMedico = new FuncionarioDAO();
    public PacienteDAO finderPaciente = new PacienteDAO();
    public JComboBox<String> nomePaciente;
    public List<Paciente> pacientesDisponiveis;
    public JFormattedTextField dataField;
    public JFormattedTextField horaField;
    public JComboBox<String> tipoExame;
    public JTextField exameField;
    public List<Exame> examesDiponiveis;
    public ExameDAO finderExame = new ExameDAO();
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

       // pacienteField = new JTextField();
       // pacienteField.setFont(new Font("Inter", Font.PLAIN, 16));
       // pacienteField.setBounds(500, 0, 50, 30);
       // p2.add(pacienteField);

        nomePaciente = new JComboBox<>();
        nomePaciente.removeAllItems();
        pacientesDisponiveis = finderPaciente.retornaLista("");
        for (Paciente paciente: pacientesDisponiveis) {
            String nome_paciente = paciente.getNome();
            int id_paciente = paciente.getId();
            nomePaciente.addItem(String.format("%d: %s", id_paciente, nome_paciente));
        }
        nomePaciente.setFont(new Font("Inter", Font.PLAIN, 16));
        nomePaciente.setBounds(500, 0, 150, 30);
        p2.add(nomePaciente);


        JLabel funcionarioLabel = new JLabel("Funcionário responsável (id):");
        funcionarioLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        funcionarioLabel.setBounds(244, 50, 210, 30);
        p2.add(funcionarioLabel);

//        funcionarioField = new JTextField();
//        funcionarioField.setFont(new Font("Inter", Font.PLAIN, 16));
//        funcionarioField.setBounds(500, 50, 50, 30);
//        p2.add(funcionarioField);

        nomeMedico = new JComboBox<>();
        nomeMedico.removeAllItems();
        medicosDisponiveis = finderMedico.retornaLista("");
        for (Funcionario medico: medicosDisponiveis) {
            String nome_medico = medico.getNome();
            String cargo = medico.getCargo();
            int id_medico = medico.getId();
            nomeMedico.addItem(String.format("%d: %s - %s", id_medico, nome_medico, cargo));
        }
        nomeMedico.setFont(new Font("Inter", Font.PLAIN, 16));
        nomeMedico.setBounds(500, 50, 150, 30);
        p2.add(nomeMedico);

        JLabel exameLabel = new JLabel("Exame (id):");
        exameLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        exameLabel.setBounds(244, 100, 100, 30);
        p2.add(exameLabel);

//        exameField = new JTextField();
//        exameField.setFont(new Font("Inter", Font.PLAIN, 16));
//        exameField.setBounds(500, 100, 50, 30);
//        p2.add(exameField);

        tipoExame = new JComboBox<>();
        tipoExame.removeAllItems();
        examesDiponiveis = finderExame.retornaLista("");
        for (Exame exame : examesDiponiveis) {
            String nome_exame = exame.getNome();
            int id_exame = exame.getId();
            tipoExame.addItem(String.format("%d: %s", id_exame, nome_exame));
        }
        tipoExame.setFont(new Font("Inter", Font.PLAIN, 16));
        tipoExame.setBounds(500, 100, 150, 30);
        p2.add(tipoExame);

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

                    String pacienteSelecionado = (String) nomePaciente.getSelectedItem();
                    int id_paciente = Integer.parseInt(pacienteSelecionado.substring(0, pacienteSelecionado.indexOf(':')));

                    String medSelecionado = (String) nomeMedico.getSelectedItem();
                    int id_medico = Integer.parseInt(medSelecionado.substring(0, medSelecionado.indexOf(':')));

                    String exameSelecionado = (String) tipoExame.getSelectedItem();
                    int id_exame = Integer.parseInt(exameSelecionado.substring(0, exameSelecionado.indexOf(':')));

                    Exame exame = finderExame.pesquisar(id_exame);
                    Funcionario funcionario = finderMedico.pesquisar(id_medico);
                    Paciente paciente = finderPaciente.pesquisar(id_paciente);

                    novoAgendamento.setPaciente(paciente);
                    novoAgendamento.setSupervisor(funcionario);
                    novoAgendamento.setExame(exame);
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
        });

        c.add(p2);

        setVisible(true);
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