package org.example;

import org.example.Enum.DisciplinaType;

public class Aluno {

    private Curso curso;
    private Turma turma;
    private int qntMatericasOptativas = 0;
    private  int qntMatericasObrigatorias = 0;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aluno(Curso curso, Turma turma) {
        this.curso = curso;
        this.turma = turma;
    }

    public String matricularDisciplina(Disciplina disciplina) {
        if (disciplina.isStatus()) {
            disciplina.getTurmas().get(0).getAlunos().add(this);
            disciplina.setVagas(disciplina.getVagas() - 1);
            if(disciplina.getTipo().equals(DisciplinaType.OPTATIVA)){
                qntMatericasOptativas++;
            }
            else{
                qntMatericasObrigatorias++;
            }
            return "Matrícula realizada com sucesso!";
        }
        return "Matrícula não realizada!";
    }

    public String cancelarMatricula(Disciplina disciplina) {
        if (disciplina.isStatus()) {
            disciplina.getTurmas().get(0).getAlunos().remove(this);
            disciplina.setVagas(disciplina.getVagas() + 1);
            if(disciplina.getTipo().equals(DisciplinaType.OPTATIVA)){
                qntMatericasOptativas--;
            }
            else{
                qntMatericasObrigatorias--;
            }
            return "Matrícula cancelada com sucesso!";
        }
        return "Matrícula não cancelada!";
    }

    public boolean checarDisponibilidade(Disciplina disciplina) {
        if (disciplina.getTipo().equals(DisciplinaType.OBRIGATORIA)) {
            if (qntMatericasObrigatorias < 4 && disciplina.getVagas() > 0) {
                return true;
            }
        } else {
            if (qntMatericasOptativas < 2 && disciplina.getVagas() > 0) {
                return true;
            }
        }
        throw new IllegalArgumentException("Não é possível matricular-se em mais disciplinas!");
    }

}