package org.example;

import org.example.Enum.TipoDisciplina;

public class Aluno {

    public String nome;
    private Curso curso;
    private static int discipinaObrigatoria = 0;
    private static int disciplinaOptativa = 0;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aluno(Curso curso, String nome) {
        this.curso = curso;
        this.nome = nome;
    }

    public String matricularDisciplina(Disciplina disciplina) {
        boolean disponibilidade = checarDisponibilidade(disciplina);
        if (disciplina.getAberta() && disponibilidade) {
           boolean isAlocado =  disciplina.alocarAluno(this);
            if(disciplina.getTipo().equals(TipoDisciplina.OPTATIVA)){
                disciplinaOptativa++;
            }
            else{
                discipinaObrigatoria++;
            }
            if(isAlocado)
                return "Matrícula realizada com sucesso!";
        }
        return "Matrícula não realizada!";
    }

    public String cancelarMatricula(Disciplina disciplina) {
        if (disciplina.getAberta()) {
            boolean isAlocado =  disciplina.removerAluno(this);
            if(disciplina.getTipo().equals(TipoDisciplina.OPTATIVA)){
                disciplinaOptativa--;
            }
            else{
                discipinaObrigatoria--;
            }
            if(isAlocado)
                return "Matrícula cancelada com sucesso!";
        }
        return "Matrícula não cancelada!";
    }

    public boolean checarDisponibilidade(Disciplina disciplina) {
      
        if (disciplina.getTipo().equals(DisciplinaType.OBRIGATORIA)) {
            if (discipinaObrigatoria < 4) {
                return true;
            }
        } else {
            if (disciplinaOptativa < 2 ) {
                return true;
            }
        }
        throw new IllegalArgumentException("Não é possível matricular-se em mais disciplinas!");
    }

}