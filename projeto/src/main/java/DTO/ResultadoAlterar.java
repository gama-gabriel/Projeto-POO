package DTO;

import Forms.FuncoesFuncionario.GerenciarExames;
import Forms.FuncoesFuncionario.GerenciarResultados;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultadoAlterar extends ResultadoCadastro{
    private Resultado alterado;
    private JButton alterarButton;
    public Resultado getAlterado() {
        return alterado;
    }

    public void setAlterado(Resultado alterado) {
        this.alterado = alterado;
    }
    public ResultadoAlterar(Funcionario logado, Resultado alterado) {
        super(logado);
        setAlterado(alterado);
        setTitle("Alteração de cadastro de resultados");
        cadastrarButton.setVisible(false);

        descricaoField.setText(alterado.getDescricao());


        alterarButton = new JButton("Alterar");
        alterarButton.setBounds(375, 120, 150, 40);
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
                    Resultado novoExame = new Resultado();
                    novoExame.setId(alterado.getId());
                    novoExame.setDescricao(descricaoField.getText());
                    novoExame.UseService();
                    novoExame.dao.alterar(novoExame);
                    JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                    c.setVisible(false);
                    dispose();
                    GerenciarResultados telaGenrenciarResultados = new GerenciarResultados(logado);
                }
            }
        });

        c.add(p2);

        setVisible(true);
    }
}
