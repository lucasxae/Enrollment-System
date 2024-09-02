package org.example;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;

import org.example.Enum.DisciplinaType;

public class Disciplina {
    private boolean status;
    private static int vagas = 60;
    private DisciplinaType tipo;
    private List<Turma> turmas;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static int getVagas() {
        return vagas;
    }

    public static void setVagas(int vagas) {
        Disciplina.vagas = vagas;
    }

    public DisciplinaType getTipo() {
        return tipo;
    }

    public void setTipo(DisciplinaType tipo) {
        this.tipo = tipo;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<Aluno> checarAlunosMatriculados() {
        List<Aluno> alunos = new ArrayList<>();
        for (Turma turma : turmas) {
            int tam = turma.getAlunos().size();

            for (int i = 0; i < tam; i++) {
                alunos.add(turma.getAlunos().get(i));
            }
        }
        return alunos;
    }

    public void fecharDisciplina() {

    }

}
