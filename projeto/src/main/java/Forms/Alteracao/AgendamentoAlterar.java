package Forms.Alteracao;

import DAO.ExameDAO;
import DAO.FuncionarioDAO;
import DAO.PacienteDAO;
import DAO.ResultadoDAO;
import DTO.*;
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
import java.util.List;

public class AgendamentoAlterar extends AgendamentoCadastro {
    private JTextField resultadoField;
    private ResultadoDAO finderResultado = new ResultadoDAO();
    private JComboBox<String> idResultado;
    private List<Resultado> resultadosDisponiveis;
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

       // resultadoField = new JTextField();
       // resultadoField.setFont(new Font("Inter", Font.PLAIN, 16));
       // resultadoField.setBounds(500, 250, 50, 30);
       // p2.add(resultadoField);

        idResultado = new JComboBox<>();
        idResultado.removeAllItems();
        idResultado.addItem("Nenhum");
        resultadosDisponiveis = finderResultado.retornaLista("");
        for (Resultado resultado: resultadosDisponiveis) {
            int id_resutlado = resultado.getId();
            idResultado.addItem(String.format("%d", id_resutlado));
        }
        idResultado.setFont(new Font("Inter", Font.PLAIN, 16));
        idResultado.setBounds(500, 250, 100, 30);
        p2.add(idResultado);

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            dataField.setText(alterado.getDataHora().format(dateFormatter));
            horaField.setText(alterado.getDataHora().format(timeFormatter));
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        canceladoCheck.setSelected(alterado.getCancelado());

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
                // Novela de colocar data no sql:
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

                    String pacienteSelecionado = (String) nomePaciente.getSelectedItem();
                    int id_paciente = Integer.parseInt(pacienteSelecionado.substring(0, pacienteSelecionado.indexOf(':')));

                    String medSelecionado = (String) nomeMedico.getSelectedItem();
                    int id_medico = Integer.parseInt(medSelecionado.substring(0, medSelecionado.indexOf(':')));

                    String exameSelecionado = (String) tipoExame.getSelectedItem();
                    int id_exame = Integer.parseInt(exameSelecionado.substring(0, exameSelecionado.indexOf(':')));

                    Resultado resultado = new Resultado();
                    String resultadoSelecionado = (String) idResultado.getSelectedItem();
                    if (!resultadoSelecionado.equals("Nenhum")) {
                        int id_resultado = Integer.parseInt(resultadoSelecionado);
                        resultado = finderResultado.pesquisar(id_resultado);
                    }

                    Exame exame = finderExame.pesquisar(id_exame);
                    Funcionario funcionario = finderMedico.pesquisar(id_medico);
                    Paciente paciente = finderPaciente.pesquisar(id_paciente);
                    novoAgendamento.setId(alterado.getId());
                    novoAgendamento.setDataHora(dataHora);
                    novoAgendamento.setPaciente(paciente);
                    novoAgendamento.setSupervisor(funcionario);
                    novoAgendamento.setExame(exame);
                    novoAgendamento.setCancelado(canceladoCheck.isSelected());
                    novoAgendamento.setResultado(resultado);
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
        });
        p2.add(alterarButton);
        setVisible(true);
    }

}
