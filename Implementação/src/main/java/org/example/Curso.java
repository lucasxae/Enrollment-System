package org.example;

import java.util.List;

public class Curso {
    private String nome;
    private double creditos;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getCreditos() {
        return creditos;
    }
    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    private List<Disciplina> disciplinas;
}
