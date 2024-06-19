package Forms;

import DAO.FuncionarioDAO;
import DTO.Funcionario;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception
    {
        MenuForm f = new MenuForm();
        //FuncionarioDAO dao = new FuncionarioDAO();
        //Funcionario x = new Funcionario();

        //x.setNome("abc def");
        //x.setCpf("00000010111");
        //x.setDataNascimento(new Date(2000, 10, 24));
        //x.setEmail("abc@email.com.br");
        //x.setSenha("123456");
        //x.setAtivo(true);
        //x.setCargo("diferente");

        //if(dao.inserir(x)>0){
        //    System.out.println("registro inserido com sucesso!");
        //}
        //else {
        //    System.out.println("erro ao inserir (dentro do main)");
        //}

        //FuncionarioTableModel modelo = new FuncionarioTableModel();
        //modelo.setDados(dao.retornaLista(""));
        //int i;
        //for (i=0;i < 2; i++) {
        //    System.out.println(modelo.getDados().get(i).getId());
        //    System.out.println(modelo.getDados().get(i).getNome());
        //    System.out.println(modelo.getDados().get(i).getEmail());
        //    System.out.println(modelo.getDados().get(i).getDataNascimento());
        //    System.out.println(modelo.getDados().get(i).getSenha());
        //    System.out.println(modelo.getDados().get(i).getCargo());
        //}

    }
}
