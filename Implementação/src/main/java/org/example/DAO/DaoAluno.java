package org.example.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.Aluno;

public class DaoAluno {
    private static final String FILE_PATH = "Enrollment-System\\Implementação\\src\\main\\java\\org\\example\\Data\\Alunos.txt";

    public void adicionarAluno(Aluno objAluno) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        String line = objAluno.getNome() + ", " + objAluno.getCurso() + ", "
                + objAluno.getCurso().getDisciplinas().get(0).getTurmas() + " .";
        bw.write(line);
        bw.newLine();
        bw.close();
    }

    public List<Aluno> getAlunos() throws IOException {
        List<Aluno> alunos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(", ");
            if (dados.length == 3) {
                Aluno alunoObj = new Aluno(dados[0]);
                alunos.add(alunoObj);
            }
        }
        br.close();

        return alunos;
    }

    public Aluno getByName(String nome) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(", ");
            if (dados.length == 3 && dados[0].equals(nome)) {
                br.close();
                return new Aluno(nome);
            }
        }
        br.close();
        return null;
    }

    public void updateAluno(Aluno alunoUpdated) throws IOException {
        List<Aluno> alunos = getAlunos();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Aluno obj : alunos) {
            if (obj.getNome().equals(alunoUpdated.getNome())) {
                bw.write(alunoUpdated.getNome() + ", " + alunoUpdated.getCurso());
            } else {
                bw.write(obj.getNome() + ", " + obj.getCurso().getNome());
                bw.newLine();
            }
        }
        bw.close();
    }

    public void removerAluno(String nome) throws IOException {
        List<Aluno> alunos = getAlunos();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Aluno aluno : alunos) {
            if (!aluno.getNome().equals(nome)) {
                bw.write(aluno.getNome() + ", " + aluno.getCurso().getNome());
                bw.newLine();
            }
            bw.close();
        }
    }
}