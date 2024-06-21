package Forms.Alteracao;

import DTO.Exame;
import DTO.Funcionario;
import Forms.Cadastro.ExameCadastro;
import Forms.FuncoesFuncionario.GerenciarExames;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExameAlterar extends ExameCadastro {
    private Exame alterado;
    private JButton alterarButton;

    public Exame getAlterado() {
        return alterado;
    }

    public void setAlterado(Exame alterado) {
        this.alterado = alterado;
    }

    public ExameAlterar(Funcionario logado, Exame alterado) {
        super(logado);
        setAlterado(alterado);
        setTitle("Alteração de cadastro de exames");
        title.setText("Alteração de cadastro");
        cadastrarButton.setVisible(false);

        nomeField.setText(alterado.getNome());
        descricaoField.setText(alterado.getDescricao());
        preparoField.setText(alterado.getPreparo());
        instrucoesField.setText(alterado.getInstrucoesPos());

        alterarButton = new JButton("Alterar");
        alterarButton.setBounds(375, 310, 150, 40);
        alterarButton.setContentAreaFilled(false);
        alterarButton.setFocusPainted(false);
        alterarButton.setFont(new Font("Inter", Font.BOLD, 16));
        alterarButton.setForeground(new Color(255, 255, 232));
        alterarButton.setBorder(new RoundedBorder(10, 2));
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
        p2.add(alterarButton);

        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    Exame novoExame = new Exame();
                    novoExame.setId(alterado.getId());
                    novoExame.setNome(nomeField.getText());
                    novoExame.setDisponivel(true);
                    novoExame.setDescricao(descricaoField.getText());
                    novoExame.setPreparo(preparoField.getText());
                    novoExame.setInstrucoesPos(instrucoesField.getText());
                    novoExame.UseService();
                    novoExame.dao.alterar(novoExame);
                    JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                    c.setVisible(false);
                    dispose();
                    GerenciarExames telaGenrenciarExames = new GerenciarExames(logado);
                }
            }
        });

        c.add(p2);

        setVisible(true);
    }
}

