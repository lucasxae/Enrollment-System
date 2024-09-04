package org.example;

import java.util.List;

public class Professor {

    Turma turma;
    String nome;

    public Professor(Turma turma, String nome) {
        this.turma = turma;
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> checarAlunos(Disciplina disciplina){
        return disciplina.checarAlunosMatriculados(); 
    };

    public void adicionarTurma(Turma turma){
        this.turma = turma;
    }

}
