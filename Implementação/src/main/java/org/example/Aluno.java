package org.example;

import java.util.List;

import org.example.Enum.DisciplinaType;

/**
 * The Aluno class represents a student enrolled in a course and various
 * classes.
 * It implements the ICobranca and ICurriculo interfaces.
 */
public class Aluno implements ICobranca, ICurriculo {

    private Curso curso;
    private List<Turma> turmas;

    /**
     * Gets the course the student is enrolled in.
     *
     * @return the course the student is enrolled in
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Sets the course the student is enrolled in.
     *
     * @param curso the course to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Gets the list of classes the student is enrolled in.
     *
     * @return the list of classes the student is enrolled in
     */
    public List<Turma> getTurmas() {
        return turmas;
    }

    /**
     * Sets the list of classes the student is enrolled in.
     *
     * @param turmas the list of classes to set
     */
    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    /**
     * Enrolls the student in a given discipline.
     *
     * @param disciplina the discipline to enroll in
     */
    public void matricularDisciplina(Disciplina disciplina) {
        var canDoOptives = checarOptativas();
        var canDoLimits = checarLimites();
        if(disciplina.isStatus()){
            throw new IllegalArgumentException("A disciplina já está matriculada");
        }
        if (!canDoOptives) {
         throw new IllegalArgumentException("O aluno já está matriculado em duas disciplinas optativas");
        }

        if(!canDoLimits){
            throw new IllegalArgumentException("O aluno já está matriculado em quatro disciplinas obrigatórias");
        }

    }

    /**
     * Checks if the student has enrolled in optional subjects.
     *
     * @return true if the student has enrolled in optional subjects, false
     *         otherwise
     */
    public boolean checarOptativas() {
        var disciplinas = curso.getDisciplinas();
        var count = 0;
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getTipo() == DisciplinaType.OPTATIVA) {
                count++;
            }
        }
        if (count >= 2)
            return false;

        return true;
    }

    private boolean checarLimites() {
        var disciplinas = curso.getDisciplinas();
        var count = 0;
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getTipo() == DisciplinaType.OBRIGATORIA) {
                count++;
            }
        }
        if (count >= 4)
            return false;

        return true;
    }
    /**
     * Cancels the student's enrollment in a given discipline.
     *
     * @param disciplina the discipline to cancel enrollment in
     * @return true if the enrollment was successfully canceled, false otherwise
     */
    public boolean cancelarMatricula(Disciplina disciplina) {
        return true;
    }

    /**
     * Notifies the student of a charge.
     */
    @Override
    public void notificarCobranca() {
        // Implementation here
    }

    /**
     * Generates the student's semester curriculum.
     *
     * @return a string representing the student's semester curriculum
     */
    @Override
    public String gerarCurriculoSemestral() {
        return "lab";
    }
}