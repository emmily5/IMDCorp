package model;

import enums.Nivel;
import enums.Formacao;
import enums.Genero;
import java.time.LocalDate;
import java.util.List;

public class Professor extends Pessoa {
    private Nivel nivelProfessor;
    private Formacao formacaoProfessor;
    private List<String> disciplinas;

    // Construtor parametrizado
    public Professor
    (String nome, String cpf, Long matricula, LocalDate dataNascimento, Genero genero, Endereco endereco, Double salario, String departamento, Integer cargaHoraria, LocalDate dataIngresso,Nivel nivelProfessor, Formacao formacaoProfessor, List<String> disciplinas) {
        super(nome, cpf, matricula, dataNascimento, genero, endereco, salario, departamento, cargaHoraria, dataIngresso);
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

    // Método para calcular salário
    @Override
    public Double calculaSalario() {
        double salarioBase = 4000.0;
        double salarioNivel = salarioBase;

        // Cálculo do adicional por nível
        for (int i = 1; i < nivelProfessor.getValor(); i++) {
            salarioNivel *= 1.05; // 5% de aumento por nível
        }

        // Adicional por formação
        double adicionalFormacao = 0.0;
    switch (formacaoProfessor) {
        case ESPECIALIZACAO:
            adicionalFormacao = salarioBase * 0.25;
            break;
        case MESTRADO:
            adicionalFormacao = salarioBase * 0.50;
            break;
        case DOUTORADO:
            adicionalFormacao = salarioBase * 0.75;
            break;
        default:
            adicionalFormacao = 0.0; // Caso padrão: sem adicional
            break;
    }
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