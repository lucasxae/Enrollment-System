package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.Enum.TipoDisciplina;

public class Disciplina {
    private String nome;
    private float cargaHoraria;
    private boolean aberta;
    private Curso curso;
    private TipoDisciplina tipo;
    private List<Turma> turmas = new ArrayList<>();

    public Disciplina(boolean aberta, Curso curso, TipoDisciplina tipo, List<Turma> turmas, String nome,
            Float cargahoraria) {
        this.aberta = aberta;
        this.curso = curso;
        this.tipo = tipo;
        this.turmas = turmas;
        this.cargaHoraria = cargahoraria;
        this.nome = nome;
    }

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public Disciplina(String nome, TipoDisciplina tipo, boolean aberta) {
        this.nome = nome;
        this.tipo = tipo;
        this.aberta = aberta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public boolean getAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoDisciplina getTipo() {
        return tipo;
    }

    public void setTipo(TipoDisciplina tipo) {
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
        aberta = false;
    }

    public boolean alocarAluno(Aluno aluno) {

        for (int i = 0; i < turmas.size() + 1; i++) {
            List<Aluno> alunos = turmas.get(i).getAlunos();
            for (int x = 0; x < alunos.size(); x++) {
                if (alunos.get(x).getNome().equals(aluno.getNome())) {
                    return false;
                }
            }
        }

        for (int i = 0; i < turmas.size(); i++) {
            if (turmas.get(i).adicionarAluno(aluno)) {
                return true;
            }
        }
        return false;
    }

    public boolean removerAluno(Aluno aluno) {
        for (int i = 0; i < turmas.size(); i++) {
            List<Aluno> alunos = turmas.get(i).getAlunos();
            for (int x = 0; x < alunos.size(); x++) {
                if (alunos.get(x).getNome().equals(aluno.getNome())) {
                    turmas.get(i).removerAluno(alunos.get(x));
                    return true;
                }
            }
        }
        return false;
    }
    public String getStringDeTurmas(){
        String turmasS="";
        for(Turma t:this.turmas){
            turmasS+=" "+t.getProfessor().getNome()+", "+t.getCodigo();
        }

        return turmasS;
    }
}
