package service;

import dao.BancoDAO;
//import model.Pessoa;
import model.Professor;
import model.TecnicoADM;
//import model.Formacao;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
//import java.util.stream.Collectors; OLHAR O PORQUÊ DO ERRO 

public class Operacoes {
    private BancoDAO bancoDAO;
    private Scanner scanner;

    public Operacoes() {
        this.bancoDAO = BancoDAO.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarProfessor(Professor professor) {
        bancoDAO.adicionarFuncionario(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    public void cadastrarTecnicoADM(TecnicoADM tecnico) {
        bancoDAO.adicionarFuncionario(tecnico);
        System.out.println("Técnico Administrativo cadastrado com sucesso!");
    }

    public void listarProfessores() {
        System.out.println("\n=== Lista de Professores ===");
        List<Professor> professores = bancoDAO.getArrayPessoa().stream()
                .filter(p -> p instanceof Professor)  // Filtra apenas os professores
                .map(p -> (Professor) p)
                .toList();

        for (Professor professor : professores) {
            System.out.println("Nome: " + professor.getNome());
            System.out.println("Disciplinas: " + String.join(", ", professor.getDisciplinas()));
        }
    }

    public void listarTecnicosADM() {
        System.out.println("\n=== Lista de Técnicos Administrativos ===");
        List<TecnicoADM> tecnicos = bancoDAO.getArrayPessoa().stream()
                .filter(p -> p instanceof TecnicoADM)  // Filtra apenas os técnicos administrativos
                .map(p -> (TecnicoADM) p)
                .toList();

        for (TecnicoADM tecnico : tecnicos) {
            System.out.println("Nome: " + tecnico.getNome());
            System.out.println("Função: " + tecnico.getFuncaoGratificada());
        }
    }


   public void deletarProfessor(long matricula) {
        Optional<Professor> professor = bancoDAO.getArrayPessoa().stream()
                .filter(p -> p instanceof Professor && p.getMatricula() == matricula)
                .map(p -> (Professor) p)
                .findFirst();

        if (professor.isPresent()) {
            bancoDAO.removerFuncionario(professor.get());
            System.out.println("Professor removido com sucesso!");
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public void deletarTecnicoADM(long matricula) {
        Optional<TecnicoADM> tecnico = bancoDAO.getArrayPessoa().stream()
                .filter(p -> p instanceof TecnicoADM && p.getMatricula() == matricula)
                .map(p -> (TecnicoADM) p)
                .findFirst();

        if (tecnico.isPresent()) {
            bancoDAO.removerFuncionario(tecnico.get());
            System.out.println("Técnico Administrativo removido com sucesso!");
        } else {
            System.out.println("Técnico Administrativo não encontrado!");
        }
    }


    public void buscarProfessor(long matricula) {
        Optional<Professor> professor = bancoDAO.getArrayPessoa().stream()
                .filter(p -> p instanceof Professor && p.getMatricula() == matricula)
                .map(p -> (Professor) p)
                .findFirst();

        if (professor.isPresent()) {
            System.out.println("Professor encontrado: " + professor.get());
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public void buscarTecnicoADM(long matricula) {
        Optional<TecnicoADM> tecnico = bancoDAO.getArrayPessoa().stream()
                .filter(p -> p instanceof TecnicoADM && p.getMatricula() == matricula)
                .map(p -> (TecnicoADM) p)
                .findFirst();

        if (tecnico.isPresent()) {
            System.out.println("Técnico Administrativo encontrado: " + tecnico.get());
        } else {
            System.out.println("Técnico Administrativo não encontrado!");
        }
    }

    // Método para calcular o salário de um professor
    public void calcularSalarioProfessor(Professor professor) {
        double salarioBase = 4000.0;
        double salario = salarioBase;

        // Cálculo do nível
        for (int i = 1; i < professor.getNivelProfessor().ordinal(); i++) {
            salario += salario * 0.05;  // A cada nível, 5% de aumento sobre o salário
        }

        // Cálculo da formação professor 
        switch (professor.getFormacaoProfessor()) {
            case ESPECIALIZACAO -> salario += salarioBase * 0.25;
            case MESTRADO -> salario += salarioBase * 0.50;
            case DOUTORADO -> salario += salarioBase * 0.75;
            default -> {}
        }

        System.out.println("Salário do Professor: R$ " + salario);
    }

    // Método para calcular o salário de um técnico administrativo
    public void calcularSalarioTecnicoADM(TecnicoADM tecnico) {
        double salarioBase = 2500.0;
        double salario = salarioBase;

        // Cálculo do nível técnicoADM
        for (int i = 1; i < tecnico.getNivelTecnico().ordinal(); i++) {
            salario += salario * 0.03;  // A cada nível, 3% de aumento sobre o salário
        }

        // Cálculo da formação
        switch (tecnico.getFormacaoTecnico()) {
            case ESPECIALIZACAO -> salario += salarioBase * 0.25;
            case MESTRADO -> salario += salarioBase * 0.50;
            case DOUTORADO -> salario += salarioBase * 0.75;
            default -> {} //nao sei se realmente precisa
        }

        // Adicionais
        if (tecnico.getInsalubridade()) {
            salario += salarioBase * 0.50;  // Insalubridade
        }
        if (tecnico.getFuncaoGratificada()) {
            salario += salarioBase * 0.50;  // Função gratificada
        }

        System.out.println("Salário do Técnico Administrativo: R$ " + salario);
    }
}