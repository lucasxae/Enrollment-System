package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.Enum.TipoDisciplina;

public class Disciplina {
    private boolean aberta;
    private Curso curso;
    private TipoDisciplina tipo;
    private List<Turma> turmas;
    
    
    public Disciplina(boolean aberta, Curso curso, TipoDisciplina tipo, List<Turma> turmas) {
        this.aberta = aberta;
        this.curso = curso;
        this.tipo = tipo;
        this.turmas = turmas;
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

    public boolean alocarAluno(Aluno aluno){
            for (int i = 0; i < turmas.size(); i++) {
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

        public boolean removerAluno(Aluno aluno){
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
        
}
