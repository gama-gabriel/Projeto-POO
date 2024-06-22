package Forms.Alteracao;

import DAO.ExameDAO;
import DAO.FuncionarioDAO;
import DAO.PacienteDAO;
import DAO.ResultadoDAO;
import DTO.Agendamento;
import DTO.Funcionario;
import DTO.Paciente;
import DTO.Resultado;
import Forms.Cadastro.AgendamentoCadastro;
import Forms.FuncoesFuncionario.GerenciarAgendamentos;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AgendamentoAlterar extends AgendamentoCadastro {
    private JTextField resultadoField;
    private JButton alterarButton;
    private JCheckBox canceladoCheck;

    public AgendamentoAlterar(Funcionario logado, Agendamento alterado) {
        super(logado);
        setTitle("Alteração de agendamentos");
        title.setText("Alteração de agendamentos");
        cadastrarButton.setVisible(false);

        JLabel canceladoLabel = new JLabel("cancelado:");
        canceladoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        canceladoLabel.setBounds(244, 200, 80, 30);
        p2.add(canceladoLabel);

        canceladoCheck = new JCheckBox();
        canceladoCheck.setBounds(330, 200, 30, 30);
        canceladoCheck.setBackground(new Color(0xffffe8));
        p2.add(canceladoCheck);

        JLabel resultadoLabel = new JLabel("resultado (id):");
        resultadoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        resultadoLabel.setBounds(244, 250, 150, 30);
        p2.add(resultadoLabel);

        resultadoField = new JTextField();
        resultadoField.setFont(new Font("Inter", Font.PLAIN, 16));
        resultadoField.setBounds(500, 250, 50, 30);
        p2.add(resultadoField);

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            dataField.setText(alterado.getDataHora().format(dateFormatter));
            horaField.setText(alterado.getDataHora().format(timeFormatter));
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        pacienteField.setText(String.valueOf(alterado.getPaciente().getId()));
        funcionarioField.setText(String.valueOf(alterado.getSupervisor().getId()));
        exameField.setText(String.valueOf(alterado.getExame().getId()));
        canceladoCheck.setSelected(alterado.getCancelado());
        if (alterado.getResultado() != null) {
            resultadoField.setText(String.valueOf(alterado.getResultado().getId()));
        }

        alterarButton = new JButton("Alterar");
        alterarButton.setFont(new Font("Inter", Font.BOLD, 16));
        alterarButton.setBounds(385, 320, 130, 40);
        alterarButton.setContentAreaFilled(false);
        alterarButton.setFocusPainted(false);
        alterarButton.setFont(new Font("Inter", Font.BOLD, 16));
        alterarButton.setForeground(new Color(255, 255, 232));
        alterarButton.setBorder(new RoundedBorder(10, 3));
        alterarButton.setForeground(new Color(43, 37, 93, 191));
        alterarButton.setOpaque(false);
        alterarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                alterarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                alterarButton.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                alterarButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                alterarButton.setForeground(new Color(43, 37, 93, 191));
            }
        });
        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    // Novela de colocar data no sql:
                    try {
                        Agendamento novoAgendamento = new Agendamento();
                        novoAgendamento.setId(alterado.getId());
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
                        novoAgendamento.setCancelado(canceladoCheck.isSelected());
                        if (!resultadoField.getText().isEmpty()) {
                            novoAgendamento.setResultado(new ResultadoDAO().pesquisar(Integer.parseInt(resultadoField.getText())));
                        }
                        novoAgendamento.UseService();
                        novoAgendamento.dao.alterar(novoAgendamento);
                        JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                        c.setVisible(false);
                        dispose();
                        GerenciarAgendamentos telaGerenciarAgendamentos = new GerenciarAgendamentos(logado);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Data e hora inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        p2.add(alterarButton);
        setVisible(true);
    }

    private boolean validarCampos() {
        PacienteDAO validadorPaciente = new PacienteDAO();
        FuncionarioDAO validadorFuncionario = new FuncionarioDAO();
        ExameDAO validadorExame = new ExameDAO();
        ResultadoDAO validadorResultado = new ResultadoDAO();
        String pacienteString = pacienteField.getText();
        String funcionarioString = funcionarioField.getText();
        String exameString = exameField.getText();
        String resultadoString = resultadoField.getText();

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

            if (!resultadoString.isEmpty()) {
                int resultado = Integer.parseInt(resultadoString);
                if (validadorResultado.pesquisar(resultado).getId() == 0) {
                    JOptionPane.showMessageDialog(this, String.format("O resultado de id %d não existe", resultado), "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(this, "Os campos de paciente, funcionário, exame e resultado devem ser NÚMEROS INTEIROS", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println(err.getMessage());
            return false;

        }
        return true;
    }

    public AgendamentoAlterar(Paciente logado) {
        super(logado);
            setTitle("Alteração de agendamentos");
        title.setText("Alteração de agendamentos");
        cadastrarButton.setVisible(false);

        JLabel canceladoLabel = new JLabel("cancelado:");
        canceladoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        canceladoLabel.setBounds(244, 200, 150, 30);
        p2.add(canceladoLabel);

        canceladoCheck = new JCheckBox();
        canceladoCheck.setBounds(500, 200, 30, 30);
        canceladoCheck.setBackground(new Color(0xffffe8));
        p2.add(canceladoCheck);

        JLabel resultadoLabel = new JLabel("resultado (id):");
        resultadoLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        resultadoLabel.setBounds(244, 250, 150, 30);
        p2.add(resultadoLabel);

        resultadoField = new JTextField();
        resultadoField.setFont(new Font("Inter", Font.PLAIN, 16));
        resultadoField.setBounds(500, 250, 50, 30);
        p2.add(resultadoField);

        alterarButton = new JButton("Alterar");
        alterarButton.setFont(new Font("Inter", Font.BOLD, 16));
        alterarButton.setBounds(385, 320, 130, 40);
        alterarButton.setContentAreaFilled(false);
        alterarButton.setFocusPainted(false);
        alterarButton.setFont(new Font("Inter", Font.BOLD, 16));
        alterarButton.setForeground(new Color(255, 255, 232));
        alterarButton.setBorder(new RoundedBorder(10, 3));
        alterarButton.setForeground(new Color(43, 37, 93, 191));
        alterarButton.setOpaque(false);
        alterarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                alterarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                alterarButton.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                alterarButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                alterarButton.setForeground(new Color(43, 37, 93, 191));
            }
        });
        alterarButton.addActionListener(new ActionListener() {
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
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Data e hora inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    novoAgendamento.setPaciente(new PacienteDAO().pesquisar(Integer.parseInt(pacienteField.getText())));
                    novoAgendamento.setSupervisor(new FuncionarioDAO().pesquisar(Integer.parseInt(funcionarioField.getText())));
                    novoAgendamento.setExame(new ExameDAO().pesquisar(Integer.parseInt(exameField.getText())));
                    novoAgendamento.setCancelado(canceladoCheck.isSelected());
                    novoAgendamento.UseService();
                    novoAgendamento.dao.inserir(novoAgendamento);
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    c.setVisible(false);
                    dispose();
                    //abrir menu do paciente
                }
            }
        });
        p2.add(alterarButton);
        setVisible(true);
    }
}
