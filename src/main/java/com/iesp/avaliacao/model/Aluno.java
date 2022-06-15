package com.iesp.avaliacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Aluno {

    @GeneratedValue
    @Id
    private int id;

    @Column(name = "nome")
    private String nome;

    @CPF
    @Column(nullable = true)
    private String cpf;

    @Column(name = "nota")
    private double nota;

    public Aluno(){

    }

    public Aluno(String nome, String cpf, double nota){
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome está inválido");
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf != null && cpf.length() == 0) {
            this.cpf = null;
        }
        else {
            this.cpf = cpf;
        }
    }
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nota=" + nota +
                '}';
    }
}
