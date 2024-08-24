package org.example;

import java.util.List;

public class Professor {

    Turma turma;

    public List<Aluno> checarAlunos(Disciplina disciplina){
        return disciplina.checarAlunosMatriculados(); 
    };

}
