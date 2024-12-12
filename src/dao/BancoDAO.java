package dao;

import model.Pessoa;
import java.util.ArrayList;
import java.io.*;

public class BancoDAO implements Serializable {
    // Atributo estático para manter a única instância
    private static BancoDAO instance;
    
    // Lista de funcionários
    private ArrayList<Pessoa> funcionarios;

    // Caminho do arquivo de persistência
    private static final String ARQUIVO_DADOS = "funcionarios.dat";

    // Construtor privado para impedir instanciação direta
    private BancoDAO() {
        funcionarios = new ArrayList<>();
        carregarDados();
    }

    // Método para obter a instância única
    public static BancoDAO getInstance() {
        if (instance == null) {
            instance = new BancoDAO();
        }
        return instance;
    }

    // Retorna a lista de pessoas
    public ArrayList<Pessoa> getArrayPessoa() {
        return funcionarios;
    }

    // Adiciona um funcionário à lista
    public void adicionarFuncionario(Pessoa pessoa) {
        funcionarios.add(pessoa);
        salvarDados();
    }

    // Remove um funcionário da lista
    public void removerFuncionario(Pessoa pessoa) {
        funcionarios.remove(pessoa);
        salvarDados();
    }

    // Salva os dados em arquivo
    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            out.writeObject(funcionarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    // Carrega os dados do arquivo
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
            funcionarios = (ArrayList<Pessoa>) in.readObject();
        } catch (FileNotFoundException e) {
            // Primeira execução ou arquivo não existe
            funcionarios = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            funcionarios = new ArrayList<>();
        }
    }
}