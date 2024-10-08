package org.example;


import java.util.Date;
import java.util.List;

import org.example.Enum.StatusTurma;

public class Turma {
    private String codigo;
    private Professor professor;
    private List<Aluno> alunos;
    private Disciplina disciplina;
    private String semestre;
    private Date ano;
    private StatusTurma status;
    private static int minAlunos = 3;
    private static int maxAlunos = 60;
    

    public Turma(String codigo, Professor prof,Disciplina disciplina, String semestre){
        this.alunos=null;
        this.disciplina=disciplina;
        this.professor=prof;
        this.codigo=codigo;
        this.semestre=semestre;
    }
    
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Date getAno() {
        return ano;
    }
    public String getCodigo(){
        return this.codigo;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public StatusTurma getStatus() {
        return status;
    }

    public void setStatus(StatusTurma status) {
        this.status = status;
    }

    public void cancelarTurma() {
        if(this.alunos.size() < minAlunos) {
            setStatus(StatusTurma.CANCELADA);
        }
    }

    public void concluirTurma() {
        setStatus(StatusTurma.CONCLUIDA);
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (this.alunos.size() < maxAlunos) {
            this.alunos.add(aluno);
            return true;
        } else{
            return false;
        }
    }

    public boolean removerAluno(Aluno aluno) {
        if(aluno != null){
        this.alunos.remove(aluno);
        return true;
        } else{
            return false;
        }
    }
    
}
