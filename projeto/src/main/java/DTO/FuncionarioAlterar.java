package DTO;

import DAO.FuncionarioDAO;
import DAO.PacienteDAO;
import Forms.FuncionarioLoginForm;
import Forms.FuncionarioMenuForm;
import Forms.FuncoesFuncionario.GerenciarFuncionarios;
import Forms.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FuncionarioAlterar extends FuncionarioCadastro{
    public Funcionario getAlterado() {
        return alterado;
    }

    public void setAlterado(Funcionario alterado) {
        this.alterado = alterado;
    }

    private Funcionario alterado;
    private JButton alterarButton;
   public FuncionarioAlterar(Funcionario logado, Funcionario alterado) {
       setAlterado(alterado);
       setTitle("Alteração de cadastro de funcionários");
       title.setText("Alteração de cadastro");
       cadastrarButton.setVisible(false);

       cpfField.setText(alterado.getCpf());
       nomeField.setText(alterado.getNome());
       emailField.setText(alterado.getEmail());
       DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       dataNascimentoField.setText(sdf.format(alterado.getDataNascimento()));
       senhaField.setText(alterado.getSenha());
       confirmarSenhaField.setText(alterado.getSenha());
       if (logado.getId() != alterado.getId()) {
           senhaField.setVisible(false);
           confirmarSenhaField.setVisible(false);
       }
       cargoField.setText(alterado.getCargo());
       cpfField.setText(alterado.getCpf());

       alterarButton = new JButton("Alterar");
       alterarButton.setFont(new Font("Inter", Font.BOLD, 16));
       alterarButton.setBounds(385, 350, 130, 40);
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
       p2.add(alterarButton);

       alterarButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (validarCampos()) {
                   Funcionario novoUsuario = new Funcionario();
                   novoUsuario.setId(alterado.getId());
                   novoUsuario.setCpf(cpfField.getText());
                   novoUsuario.setAtivo(true);
                   novoUsuario.setNome(nomeField.getText());
                   novoUsuario.setEmail(emailField.getText());
                   novoUsuario.setCargo(cargoField.getText());
                   novoUsuario.setSenha(senhaField.getText());
                   // Novela de colocar data no sql:
                   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                   try {
                       novoUsuario.setDataNascimento(sdf.parse(dataNascimentoField.getText()));
                   } catch (ParseException ex) {
                       JOptionPane.showMessageDialog(null, "Data de nascimento inválida, informação não inserida!", "Erro", JOptionPane.ERROR_MESSAGE);
                   }
                   novoUsuario.UseService();
                   novoUsuario.dao.alterar(novoUsuario);
                   JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                   c.setVisible(false);
                   dispose();
                   GerenciarFuncionarios telaGenrenciarFunc = new GerenciarFuncionarios(logado);
               }
           }
       });


   }

    private boolean validarCampos() {
        FuncionarioDAO validador = new FuncionarioDAO();
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String nome = nomeField.getText();
        String dataNascimento = dataNascimentoField.getText();
        String cargo = cargoField.getText();
        String senha = new String(senhaField.getPassword());
        String confirmarSenha = new String(confirmarSenhaField.getPassword());
        System.out.println(alterado.getEmail());
        System.out.println(email);
        System.out.println(alterado.getCpf());
        System.out.println(cpf);
        if (cpf.isEmpty() || email.isEmpty() || nome.isEmpty() || dataNascimento.isEmpty() || cargo.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
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

        if (!alterado.getCpf().equals(cpf)) {
            if (validador.pesquisaCpf(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if (!(alterado.getEmail().equals(email))) {
            if (validador.pesquisaEmail(email)) {
                JOptionPane.showMessageDialog(this, "Email já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        System.out.println(!alterado.getEmail().equals(email));
        System.out.println(!(alterado.getEmail().equals(email)));
        System.out.println(alterado.getCpf().equals(cpf));
        return true;
    }
}
