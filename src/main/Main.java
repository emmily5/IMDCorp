package main;

import dao.BancoDAO;
import enums.*;
import model.*;
//import service.Operacoes; OLHAR O PORQUÊ DO ERRO

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.ArrayList; OLHAR O PORQUÊ DO ERRO
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BancoDAO banco = BancoDAO.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n --- Sistema de Gerenciamento de Funcionários ---");
            System.out.println("1. Adicionar Professor");
            System.out.println("2. Adicionar Técnico Administrativo");
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Remover Funcionário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> {
                    Professor professor = criarProfessor(scanner);
                    banco.adicionarFuncionario(professor);
                    System.out.println("Professor adicionado com sucesso!");
                }
                case 2 -> {
                    TecnicoADM tecnico = criarTecnico(scanner);
                    banco.adicionarFuncionario(tecnico);
                    System.out.println("Técnico Administrativo adicionado com sucesso!");
                }
                case 3 -> {
                    System.out.println("\n=== Lista de Funcionários ===");
                    banco.getArrayPessoa().forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Informe a matrícula do funcionário a ser removido: ");
                    long matricula = scanner.nextLong(); // Lê a matrícula como long
                    scanner.nextLine(); // Limpa o buffer
                
                    Pessoa funcionario = banco.getArrayPessoa().stream()
                            .filter(p -> p.getMatricula() == matricula) // Verifica pelo número de matrícula
                            .findFirst()
                            .orElse(null);
                
                    if (funcionario != null) {
                        banco.removerFuncionario(funcionario);
                        System.out.println("Funcionário removido com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                }                
                case 5 -> {
                    System.out.println("Encerrando o programa. Até mais!");
                    running = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static Professor criarProfessor(Scanner scanner) {
        System.out.println("\n=== Cadastro de Professor ===");
        Pessoa pessoaBase = criarPessoaBase(scanner);

        System.out.print("Informe o nível do professor (1 a 8): ");
        Nivel nivel = Nivel.values()[scanner.nextInt() - 1];

        System.out.print("Informe a formação do professor (1: Especialização, 2: Mestrado, 3: Doutorado): ");
        Formacao formacao = Formacao.values()[scanner.nextInt() - 1];

        scanner.nextLine(); // Limpa o buffer
        System.out.print("Informe as disciplinas separadas por vírgula: ");
        List<String> disciplinas = List.of(scanner.nextLine().split(",\\s*"));

        return new Professor(
                pessoaBase.getNome(), pessoaBase.getCpf(), pessoaBase.getMatricula(), pessoaBase.getDataNascimento(),
                pessoaBase.getGenero(), pessoaBase.getEndereco(), pessoaBase.getSalario(), pessoaBase.getDepartamento(), pessoaBase.getCargaHoraria(),
                pessoaBase.getDataIngresso(), nivel, formacao, disciplinas
        );
    }

    private static TecnicoADM criarTecnico(Scanner scanner) {
        System.out.println("\n=== Cadastro de Técnico Administrativo ===");
        Pessoa pessoaBase = criarPessoaBase(scanner);

        System.out.print("Informe o nível do técnico (1 a 8): ");
        Nivel nivel = Nivel.values()[scanner.nextInt() - 1];

        System.out.print("Informe a formação do técnico (1: Especialização, 2: Mestrado, 3: Doutorado): ");
        Formacao formacao = Formacao.values()[scanner.nextInt() - 1];

        System.out.print("Possui insalubridade? (true/false): ");
        boolean insalubridade = scanner.nextBoolean();

        System.out.print("Possui função gratificada? (true/false): ");
        boolean funcaoGratificada = scanner.nextBoolean();

        return new TecnicoADM(
                pessoaBase.getNome(), pessoaBase.getCpf(),pessoaBase.getMatricula(), pessoaBase.getDataNascimento(),
                pessoaBase.getGenero(), pessoaBase.getEndereco(),
                pessoaBase.getSalario(), pessoaBase.getDepartamento(), pessoaBase.getCargaHoraria(),
                pessoaBase.getDataIngresso(), nivel, formacao, insalubridade, funcaoGratificada
        );
    }

    private static Pessoa criarPessoaBase(Scanner scanner) {
        System.out.print("Informe o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o cpf: ");
        String cpf = scanner.nextLine();

        System.out.print("Informe a data de nascimento (DD-MM-AAAA): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.print("Informe o gênero (1: Masculino, 2: Feminino, 3: Outro): ");
        Genero genero = Genero.values()[scanner.nextInt() - 1];

        scanner.nextLine(); // Limpa o buffer
        System.out.println("=== Endereço ===");
        System.out.print("Informe a rua: ");
        String rua = scanner.nextLine();

        System.out.print("Informe o número: ");
        int numero = scanner.nextInt();

        scanner.nextLine(); // Limpa o buffer
        System.out.print("Informe o bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Informe a cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Informe o CEP: ");
        String cep = scanner.nextLine();

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, cep);

        System.out.print("Informe a matrícula: ");
        long matricula = scanner.nextLong();

        System.out.print("Informe o salário: ");
        double salario = scanner.nextDouble();

        scanner.nextLine(); // Limpa o buffer
        System.out.print("Informe o departamento: ");
        String departamento = scanner.nextLine();

        System.out.print("Informe a carga horária: ");
        int cargaHoraria = scanner.nextInt();

        scanner.nextLine(); // Limpa o buffer
        System.out.print("Informe a data de ingresso (AAAA-MM-DD): ");
        LocalDate dataIngresso = LocalDate.parse(scanner.nextLine());

        return new Pessoa(nome, cpf, matricula, dataNascimento, genero, endereco, salario, departamento, cargaHoraria, dataIngresso) {};
    }
}