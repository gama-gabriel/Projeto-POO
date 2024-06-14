package DTO;

import java.time.LocalDateTime;

public class Agendamento {
    private int id;
    private LocalDateTime dataHora;
    private boolean cancelado;
    private Paciente paciente;
    private Funcionario supervisor;
    private Exame exame;
    private Resultado resultado;
}
