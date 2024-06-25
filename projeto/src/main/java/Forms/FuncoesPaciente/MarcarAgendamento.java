package Forms;

import DAO.AgendamentoDAO;
import DAO.ExameDAO;
import DTO.Agendamento;
import DTO.Exame;
import DTO.Paciente;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MarcarAgendamento extends JFrame {
    private Container c;
    private List<Exame> examesDiponiveis;
    private ExameDAO finder = new ExameDAO();
    private JComboBox<String> tipoExame;
    private JFormattedTextField dataAgendamento;
    private JFormattedTextField horaField;

    public MarcarAgendamento(Paciente logado) {
        setTitle("Cadastro de Agendamento");
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
        JLabel title = new JLabel("Cadastro de Agendamento", SwingConstants.CENTER);
        title.setLocation(200, 40); // Ajuste a posição conforme necessário
        title.setSize(500, 40); // Aumente o tamanho para caber o texto completo
        title.setFont(new Font("Inter", Font.BOLD, 30));
        title.setForeground(new Color(43, 37, 93, 255));
        title.setVisible(true);
        p1.add(title);

        c.add(p1);

        JButton botaoVoltar = new JButton("voltar");
        botaoVoltar.setFont(new Font("Inter", Font.PLAIN, 16));
        botaoVoltar.setBounds(50, 30, 200, 65);
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
                PacienteMenuForm telaMenuFunc = new PacienteMenuForm(logado);
            }
        });
        p1.add(botaoVoltar);

        JPanel p2 = new JPanel(null);
        p2.setBackground(new Color(255, 255, 232));
        p2.setSize(900, 375);
        p2.setLocation(0, 125);
        p2.setVisible(true);

        // Adicionar campos de texto e botões
        adicionarCampo(p2, "Data e Hora", 100, 10, 400, 30, "Calendario");
        adicionarCampo(p2, "Exame ID", 450, 10, 400, 30, "Combobox");

        JButton salvarBtn = new JButton("Agendar");
        salvarBtn.setBorder(new RoundedBorder(20, 2));
        salvarBtn.setForeground(new Color(43, 37, 93, 191));
        salvarBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                salvarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                salvarBtn.setForeground(new Color(43, 37, 93, 255));
            }

            public void mouseExited(MouseEvent evt) {
                salvarBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                salvarBtn.setForeground(new Color(43, 37, 93, 191));
            }
        });
        salvarBtn.setContentAreaFilled(false);
        salvarBtn.setFocusPainted(false);
        salvarBtn.setFont(new Font("Inter", Font.BOLD, 16));
        salvarBtn.setSize(250, 45);
        salvarBtn.setLocation((p2.getWidth() - 250) / 2, 260);
        salvarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dataStr = dataAgendamento.getText();
                String horaStr = horaField.getText();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                try {
                    LocalDate data = LocalDate.parse(dataStr, dateFormatter);
                    LocalTime hora = LocalTime.parse(horaStr, timeFormatter);
                    LocalDateTime dataHora = LocalDateTime.of(data, hora);

                    ExameDAO exameDao = new ExameDAO();
                    String nomeExame = (String) tipoExame.getSelectedItem();
                    Exame exame = exameDao.findByName(nomeExame);
                    AgendamentoDAO aux = new AgendamentoDAO();
                    Agendamento novoAgendamento = new Agendamento();
                    novoAgendamento.setPaciente(logado);
                    novoAgendamento.setExame(exame);
                    novoAgendamento.setDataHora(dataHora);
                    novoAgendamento.setCancelado(false);
                    aux.inserirPaciente(novoAgendamento);

                    JOptionPane.showMessageDialog(c, "Agendamento marcado com sucesso!", "Agendado!", JOptionPane.INFORMATION_MESSAGE);
                    c.setVisible(false);
                    dispose();
                    PacienteMenuForm telaPaciente = new PacienteMenuForm(logado);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(c, "Selecione uma data e hora válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        p2.add(salvarBtn);

        c.add(p2);

        setVisible(true);
    }

    private void adicionarCampo(JPanel panel, String labelText, int x, int y, int fieldWidth, int fieldHeight, String tipo) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Inter", Font.BOLD, 15));
        label.setForeground(new Color(43, 37, 93, 255));
        label.setSize(fieldWidth, fieldHeight);
        label.setLocation(x, y);
        panel.add(label);

        if (tipo.equals("Calendario")) {
            dataAgendamento = new JFormattedTextField(createFormatter("##/##/####"));
            dataAgendamento.setFont(new Font("Inter", Font.PLAIN, 16));
            dataAgendamento.setBounds(x + 20, y + 50, 100, 30);
            panel.add(dataAgendamento);

            horaField = new JFormattedTextField(createFormatter("##:##"));
            horaField.setFont(new Font("Inter", Font.PLAIN, 16));
            horaField.setBounds(x + 20, y + 130, 60, 30);
            panel.add(horaField);
        } else if (tipo.equals("Combobox")) {
            tipoExame = new JComboBox<>();
            tipoExame.removeAllItems();
            examesDiponiveis = finder.retornaLista("");
            for (Exame exame : examesDiponiveis) {
                String nome_exame = exame.getNome();
                tipoExame.addItem(nome_exame);
            }
            tipoExame.setFont(new Font("Inter", Font.PLAIN, 16));
            tipoExame.setBounds(x + 20, y + 50, 350, 30);
            panel.add(tipoExame);
        } else {
            System.out.println("E oq meu parceiro?");
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
}