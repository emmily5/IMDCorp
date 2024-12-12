package model;

import enums.Nivel;
import enums.Formacao;
import enums.Genero;
import java.time.LocalDate;

public class TecnicoADM extends Pessoa implements Funcionario {
    private Nivel nivelTecnico;
    private Formacao formacaoTecnico;
    private Boolean insalubridade;
    private Boolean funcaoGratificada;

    // Construtor parametrizado
    public TecnicoADM(String nome, String cpf, LocalDate dataNascimento, Genero genero, 
                      Endereco endereco, Long matricula, Double salario, 
                      String departamento, Integer cargaHoraria, LocalDate dataIngresso,
                      Nivel nivelTecnico, Formacao formacaoTecnico, 
                      Boolean insalubridade, Boolean funcaoGratificada) {
        super(nome, cpf, dataNascimento, genero, endereco, matricula, salario, 
              departamento, cargaHoraria, dataIngresso);
        this.nivelTecnico = nivelTecnico;
        this.formacaoTecnico = formacaoTecnico;
        this.insalubridade = insalubridade;
        this.funcaoGratificada = funcaoGratificada;
    }

    // Getters e Setters
    public Nivel getNivelTecnico() {
        return nivelTecnico;
    }

    public void setNivelTecnico(Nivel nivelTecnico) {
        this.nivelTecnico = nivelTecnico;
    }

    public Formacao getFormacaoTecnico() {
        return formacaoTecnico;
    }

    public void setFormacaoTecnico(Formacao formacaoTecnico) {
        this.formacaoTecnico = formacaoTecnico;
    }

    public Boolean getInsalubridade() {
        return insalubridade;
    }

    public void setInsalubridade(Boolean insalubridade) {
        this.insalubridade = insalubridade;
    }

    public Boolean getFuncaoGratificada() {
        return funcaoGratificada;
    }

    public void setFuncaoGratificada(Boolean funcaoGratificada) {
        this.funcaoGratificada = funcaoGratificada;
    }

    // Implementação do método calculaSalario conforme especificado
    @Override
    public Double calculaSalario() {
        double salarioBase = 2500.0;
        double salarioNivel = salarioBase;

        // Cálculo do adicional por nível
        for (int i = 1; i < nivelTecnico.getValor(); i++) {
            salarioNivel *= 1.03; // 3% de aumento por nível
        }

        // Adicional por formação
        double adicionalFormacao = salarioBase * formacaoTecnico.getAdicional();

        // Adicional de insalubridade
        double adicionalInsalubridade = insalubridade ? salarioBase * 0.5 : 0;

        // Adicional de função gratificada
        double adicionalFuncaoGratificada = funcaoGratificada ? salarioBase * 0.5 : 0;

        return salarioNivel + adicionalFormacao + adicionalInsalubridade + adicionalFuncaoGratificada;
    }

    @Override
    public String toString() {
        return super.toString() + ", TecnicoADM{" +
                "nivelTecnico=" + nivelTecnico +
                ", formacaoTecnico=" + formacaoTecnico +
                ", insalubridade=" + insalubridade +
                ", funcaoGratificada=" + funcaoGratificada +
                '}';
    }
}