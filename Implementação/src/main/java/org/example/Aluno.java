package org.example;

import java.util.List;

public class Aluno implements ICobranca, ICurriculo{

    private Curso curso;
    private List<Turma> turmas;
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public void matricularDisciplina(Disciplina disciplina){
        
    }

    public boolean checarOptativas(){
        return true;
    }
    
    public boolean cancelarMatricula(Disciplina disciplina){
        return true; 
    }

    @Override
    public void notificarCobranca() {
        
    }

    @Override 
    public String gerarCurriculoSemestral(){
        return "lab";
    }

}
