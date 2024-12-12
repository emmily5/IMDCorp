package model;

import enums.Nivel;
import enums.Formacao;
import enums.Genero;
import java.time.LocalDate;
import java.util.List;

public class Professor extends Pessoa implements Funcionario {
    private Nivel nivelProfessor;
    private Formacao formacaoProfessor;
    private List<String> disciplinas;

    // Construtor parametrizado
    public Professor(String nome, String cpf, LocalDate dataNascimento, Genero genero, 
                     Endereco endereco, Long matricula, Double salario, 
                     String departamento, Integer cargaHoraria, LocalDate dataIngresso,
                     Nivel nivelProfessor, Formacao formacaoProfessor, List<String> disciplinas) {
        super(nome, cpf, dataNascimento, genero, endereco, matricula, salario, 
              departamento, cargaHoraria, dataIngresso);
        this.nivelProfessor = nivelProfessor;
        this.formacaoProfessor = formacaoProfessor;
        this.disciplinas = disciplinas;
    }

    // Getters e Setters
    public Nivel getNivelProfessor() {
        return nivelProfessor;
    }

    public void setNivelProfessor(Nivel nivelProfessor) {
        this.nivelProfessor = nivelProfessor;
    }

    public Formacao getFormacaoProfessor() {
        return formacaoProfessor;
    }

    public void setFormacaoProfessor(Formacao formacaoProfessor) {
        this.formacaoProfessor = formacaoProfessor;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }

    // Implementação do método calculaSalario conforme especificado
    @Override
    public Double calculaSalario() {
        double salarioBase = 4000.0;
        double salarioNivel = salarioBase;

        // Cálculo do adicional por nível
        for (int i = 1; i < nivelProfessor.getValor(); i++) {
            salarioNivel *= 1.05; // 5% de aumento por nível
        }

        // Adicional por formação
        double adicionalFormacao = salarioBase * formacaoProfessor.getAdicional();

        return salarioNivel + adicionalFormacao;
    }

    @Override
    public String toString() {
        return super.toString() + ", Professor{" +
                "nivelProfessor=" + nivelProfessor +
                ", formacaoProfessor=" + formacaoProfessor +
                ", disciplinas=" + disciplinas +
                '}';
    }
}