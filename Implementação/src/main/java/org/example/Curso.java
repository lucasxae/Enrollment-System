package org.example;

import java.util.List;

public class Curso implements ICurriculo {
    private String nome;
    private double creditos;
    
    public Curso(String nome, Double creditos){
        this.creditos=creditos;
        this.nome=nome;
    }
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

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }

    @Override
    public String gerarCurriculoSemestral() {
        StringBuilder curriculo = new StringBuilder();
        curriculo.append("Curso: ").append(this.nome).append("\n");
        curriculo.append("Créditos: ").append(this.creditos).append("\n");
        curriculo.append("Disciplinas:\n");
    
        for (Disciplina disciplina : disciplinas) {
            curriculo.append("\n  Nome: ").append(disciplina.getNome()).append("\n");
            curriculo.append("  Professores:\n");
            for (Turma turma : disciplina.getTurmas()) {
                curriculo.append("    - ").append(turma.getProfessor()).append("\n");
            }
            curriculo.append("  Alunos:\n");
            for (Turma turma : disciplina.getTurmas()) {
                curriculo.append("    Turma ").append(turma.getCodigo()).append(":\n");
                for (Aluno aluno : turma.getAlunos()) {
                    curriculo.append("      - ").append(aluno.getNome()).append("\n");
                }
            }
        }
        return curriculo.toString();
    }
    
}
