package Forms;

import DAO.*;
import DTO.Exame;
import DTO.Funcionario;

import java.util.Date;

import DTO.Paciente;
import DTO.Resultado;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
//        Paciente p1 = new Paciente();
//        p1.setId(1);
//        p1.setCpf("12345678911");
//        p1.setEmail("luis@gmail.com");
//        p1.setNome("Luis");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        p1.setDataNascimento(dateFormat.parse("13/07/2004"));
//        p1.setAtivo(true);
//        p1.setSenha("senha123");
//        PacienteDAO p1DAO = new PacienteDAO();
//        p1DAO.inserir(p1);
        MenuForm f = new MenuForm();
        //ResultadoDAO rdao = new ResultadoDAO();
        //Resultado r = new Resultado();
        //r.setDescricao("negativo");
        //if (rdao.inserir(r) > 0) {
        //    System.out.println("sucesso");
        //} else {
        //    System.out.println("falha");
        //}
//        ExameDAO edao = new ExameDAO();
//        Exame e = new Exame();
//        e.setNome("Raio-x");
//        e.setDescricao("Exame de raio-x");
//        e.setDisponivel(true);
//        e.setPreparo("Não entrar com brincos");
//        e.setInstrucoesPos("Sem instruções");
//        if (edao.inserir(e) > 0) {
//            System.out.println("exame inserido com sucesso");
//        } else {
//            System.out.println("erro");
//        }
     //   FuncionarioDAO dao = new FuncionarioDAO();
     //   Funcionario x = new Funcionario();

     //   x.setNome("Gabriel");
     //   x.setCpf("00000010111");
     //   x.setDataNascimento(new Date(105, 10, 24));
     //   x.setEmail("gabriel@gmail.com");
     //   x.setSenha("123456");
     //   x.setAtivo(true);
     //   x.setCargo("Médico");

     //   if(dao.inserir(x)>0){
     //       System.out.println("registro inserido com sucesso!");
     //   }
     //   else {
     //       System.out.println("erro ao inserir (dentro do main)");
     //   }

     //   FuncionarioTableModel modelo = new FuncionarioTableModel();
     //   modelo.setDados(dao.retornaLista(""));
     //   int i;
     //   for (i=0;i < 2; i++) {
     //       System.out.println(modelo.getDados().get(i).getId());
     //       System.out.println(modelo.getDados().get(i).getNome());
     //       System.out.println(modelo.getDados().get(i).getEmail());
     //       System.out.println(modelo.getDados().get(i).getDataNascimento());
     //       System.out.println(modelo.getDados().get(i).getSenha());
     //       System.out.println(modelo.getDados().get(i).getCargo());
     //       System.out.println();
     //   }

    }
}
