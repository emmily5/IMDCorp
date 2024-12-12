package service;

import dao.BancoDAO;
import model.Pessoa;
import model.Professor;
import model.TecnicoADM;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.stream.Collectors; OLHAR O PORQUÊ DO ERRO 

public class Operacoes {
    private BancoDAO bancoDAO;
    private Scanner scanner;

    public Operacoes() {
        this.bancoDAO = BancoDAO.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarProfessor() {
        // Implementação do método de cadastro de professor
        // (Será necessário coletar todos os dados via console)
        System.out.println("Cadastro de Professor");
    }

    public void cadastrarTecnicoADM() {
        // Implementação do método de cadastro de técnico administrativo
        System.out.println("Cadastro de Técnico Administrativo");
    }

    public void listarProfessores() {
        ArrayList<Pessoa> funcionarios = bancoDAO.getArrayPessoa();
        
        System.out.println("Lista de Professores:");
        funcionarios.stream()
            .filter(f -> f instanceof Professor)
            .map(f -> (Professor) f)
            .forEach(professor -> {
                System.out.println("Nome: " + professor.getNome());
                System.out.println("Disciplinas: " + professor.getDisciplinas());
                System.out.println("---");
            });
    }

    public void listarTecnicosADM() {
        ArrayList<Pessoa> funcionarios = bancoDAO.getArrayPessoa();
        
        System.out.println("Lista de Técnicos Administrativos:");
        funcionarios.stream()
            .filter(f -> f instanceof TecnicoADM)
            .map(f -> (TecnicoADM) f)
            .forEach(tecnico -> {
                System.out.println("Nome: " + tecnico.getNome());
                System.out.println("Função: " + 
                    (tecnico.getFuncaoGratificada() ? "Função Gratificada" : "Sem Função Gratificada"));
                System.out.println("---");
            });
    }

    public void deletarProfessor(Long matricula) {
        ArrayList<Pessoa> funcionarios = bancoDAO.getArrayPessoa();
        
        boolean removido = funcionarios.removeIf(
            f -> f instanceof Professor && f.getMatricula().equals(matricula)
        );
        
        if (removido) {
            System.out.println("Professor removido com sucesso!");
            bancoDAO.salvarDados();
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    public void deletarTecnicoADM(Long matricula) {
        ArrayList<Pessoa> funcionarios = bancoDAO.getArrayPessoa();
        
        boolean removido = funcionarios.removeIf(
            f -> f instanceof TecnicoADM && f.getMatricula().equals(matricula)
        );
        
        if (removido) {
            System.out.println("Técnico Administrativo removido com sucesso!");
            bancoDAO.salvarDados();
        } else {
            System.out.println("Técnico Administrativo não encontrado.");
        }
    }

    public Professor buscarProfessor(Long matricula) {
        ArrayList<Pessoa> funcionarios = bancoDAO.getArrayPessoa();
        
        return funcionarios.stream()
            .filter(f -> f instanceof Professor && f.getMatricula().equals(matricula))
            .map(f -> (Professor) f)
            .findFirst()
            .orElse(null);
    }

    public TecnicoADM buscarTecnicoADM(Long matricula) {
        ArrayList<Pessoa> funcionarios = bancoDAO.getArrayPessoa();
        
        return funcionarios.stream()
            .filter(f -> f instanceof TecnicoADM && f.getMatricula().equals(matricula))
            .map(f -> (TecnicoADM) f)
            .findFirst()
            .orElse(null);
    }
}