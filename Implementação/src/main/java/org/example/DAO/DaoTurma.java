package org.example.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.Turma;

public class DaoTurma {
    private static final String FILE_PATH="Implementação//src//main//java//org//example//Data//Turmas.txt";

    public void adcionarTurma(Turma objTurma) throws IOException{
        BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));

        String line= objTurma.getProfessor()+", "+objTurma.getDisciplina().getNome()+" ,"+objTurma.getAlunos().toString()+".";
        bw.write(line);
        bw.newLine();
        bw.close();    
    }

    public List<Turma> getAllTurmas() throws IOException{
        List<Turma> turmas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(",");
            if (dados.length == 4) {
                Turma t = new Turma(null ,dados[0], dados[3]);
                turmas.add(t);
            }
        }
        br.close();
        return turmas;
    }
    public Turma getTurmaByProfessor(String professor) throws IOException{
       BufferedReader br= new BufferedReader(new FileReader(FILE_PATH));
       String line;
       while((line=br.readLine())!=null){
        String[] dados= line.split(", ");
        if(dados.length==4&&dados[0].equals(professor)){
            br.close();
            return new Turma(null,dados[0],dados[2]);
        }
       }
       br.close();
       return null;
    }
    public void atualizarTurma(Turma turmaUpdated)throws IOException{
        List<Turma> turmas=getAllTurmas();
        BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
        for(Turma turma:turmas){
            if(turma.getProfessor().equals(turmaUpdated.getProfessor())){
                String alunos=String.join(", ",turmaUpdated.getAlunos().toString());
                bw.write(turmaUpdated.getProfessor()+", "+turmaUpdated.getDisciplina().getNome());
            }else{
                String alunos=String.join(", ",turma.getAlunos().toString());
                bw.write(turma.getProfessor()+", "+turma.getDisciplina().getNome());
            }
            bw.newLine();
        }
        bw.close();
    }
    public void excluirTurma(String professor)throws IOException{
        List<Turma> turmas=getAllTurmas();
        BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
        for(Turma turma:turmas){
          if(!turma.getProfessor().equals(professor)){
            String alunos=String.join(", ", turma.getAlunos().toString());
            bw.write(turma.getProfessor()+", "+turma.getDisciplina().getNome()+", "+alunos);
            bw.newLine();
          }
        }
        bw.close();
     
    }
        
}
    
    
