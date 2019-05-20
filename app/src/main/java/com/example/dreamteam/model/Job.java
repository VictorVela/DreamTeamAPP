package com.example.dreamteam.model;

public class Job {

    private String nome;
    private String valor;

    public Job() {
    }

    public Job(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Job{" +
                "nome='" + nome + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
