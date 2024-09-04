package org.example.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.Curso;
import org.example.Disciplina;
import org.example.Enum.TipoDisciplina;
import org.example.Professor;
import org.example.Turma;

public class DaoDisciplinas {
    private static final String FILE_PATH = "Enrollment-System\\Implementacao\\src\\main\\java\\org\\example\\Data\\Disciplinas.txt";

    public void adicionarDisciplinas(Disciplina objDisciplina) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        String line = objDisciplina.getNome() + ", " + objDisciplina.getTipo() + ", " + ", "
                + objDisciplina.getCargaHoraria() + ", " + objDisciplina.getCurso().getNome() + ", "
                + objDisciplina.getStringDeTurmas();
        bw.write(line);
        bw.newLine();
        bw.close();
    }

    public List<Disciplina> getAllDisciplinas() throws IOException {
        List<Disciplina> disciplinas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] dados = line.split(", ");
            if (dados.length >= 5) {
                String nome = dados[0].trim();
                TipoDisciplina tipo = TipoDisciplina.valueOf(dados[1].trim());
                float cargaHoraria = Float.parseFloat(dados[3].trim());
                Curso curso = new Curso(dados[4].trim(), 2.0);

                List<Turma> turmas = new ArrayList<>();
                for (int i = 5; i < dados.length; i += 2) {
                    String codigoTurma = dados[i].trim();
                    String nomeProfessor = dados[i + 1].trim();
                    Professor professor = new Professor(null, nomeProfessor);
                    Turma turma = new Turma(codigoTurma, professor, null, null);
                    turmas.add(turma);
                }

                Disciplina objDisciplina = new Disciplina(true, curso, tipo, turmas, nome, cargaHoraria);
                disciplinas.add(objDisciplina);
            }
        }
        br.close();
        return disciplinas;
    }

    public Disciplina getDisciplinaBynome(String nome) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] dados = line.split(",");
            if (dados.length == 6 && dados[0].equals(nome)) {
                br.close();
                return new Disciplina(dados[0], TipoDisciplina.valueOf(dados[1].trim()), true);
            }
        }
        br.close();
        return null;
    }

    public void atualizarDisciplina(Disciplina disciplinaUpdated) throws IOException {
        List<Disciplina> disciplinas = getAllDisciplinas();

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().equals(disciplinaUpdated.getNome())) {
                bw.write(disciplinaUpdated.getNome() + ", " + disciplinaUpdated.getTipo() + ", "
                        + disciplinaUpdated.getCargaHoraria() + ", "
                        + disciplinaUpdated.getCurso().getNome());
            } else {
                bw.write(disciplina.getNome() + ", " + disciplina.getTipo() + ", " + disciplina.getCargaHoraria() + ", "
                        + disciplina.getCurso().getNome());
            }
        }
        bw.close();
    }

    public void removerDisciplina(String nome) throws IOException {
        List<Disciplina> disciplinas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;

        while ((line = br.readLine()) != null) {
            String[] dados = line.split(",");

            if (dados.length >= 5) {
                String nomeDisciplina = dados[0].trim();
                TipoDisciplina tipo = TipoDisciplina.valueOf(dados[1].trim());
                float cargaHoraria = Float.parseFloat(dados[3].trim());
                Curso curso = new Curso(dados[4].trim(), 2.0);

                List<Turma> turmas = new ArrayList<>();

                if (!nomeDisciplina.equals(nome)) {
                    disciplinas.add(new Disciplina(true, curso, tipo, turmas, nomeDisciplina, cargaHoraria));
                }
            }
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Disciplina disciplina : disciplinas) {
            bw.write(disciplina.getNome() + ", " + disciplina.getTipo() + ", , " + disciplina.getCargaHoraria() + ", "
                    + disciplina.getCurso().getNome() + ", " + disciplina.getStringDeTurmas());
            bw.newLine();
        }
        bw.close();
    }
}