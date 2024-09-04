package org.example.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.Disciplina;

public class DaoDisciplinas {
    private static final String FILE_PATH="Implementação//src//main//java//org//example//Disciplina.java";

    public void adicionarDisciplinas(Disciplina objDisciplina) throws IOException{
        BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH,true));
        String line= objDisciplina.getNome()+", "+objDisciplina.getTipo()+", "+", "+objDisciplina.getCargaHoraria()+", "+objDisciplina.getCurso().getNome()+", "+objDisciplina.getTurmas().toString();
        bw.write(line);
        bw.newLine();
        bw.close();
}

public List<Disciplina> getAllDisciplinas() throws IOException{
    List<Disciplina> disciplinas= new ArrayList<>();
    BufferedReader br= new BufferedReader(new FileReader(FILE_PATH));
    String line;
    while((line=br.readLine())!=null){
        String[] dados= line.split(",");
        if(dados.length==4){
            Disciplina objDisciplina = new Disciplina(dados[0]);
            disciplinas.add(objDisciplina);
        }
    }
    br.close();
    return disciplinas;
}
public Disciplina getDisciplinaBynome(String nome) throws IOException{
    BufferedReader br= new BufferedReader(new FileReader(FILE_PATH));
    String line;
    while((line=br.readLine())!=null){
       String[] dados=line.split(",");
       if(dados.length==4&&dados[0].equals(nome)){
        br.close();
        return new Disciplina(dados[0]);
       }
    }
    br.close();
    return null;
    
}
public void atualizarDisciplina(Disciplina disciplinaUpdated) throws IOException{
    List<Disciplina> disciplinas= getAllDisciplinas();

    BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
    for(Disciplina disciplina:disciplinas){
      if(disciplina.getNome().equals(disciplinaUpdated.getNome())){
        bw.write(disciplinaUpdated.getNome()+", "+disciplinaUpdated.getTipo()+", "+disciplinaUpdated.getCargaHoraria()+", "
        +disciplinaUpdated.getCurso().getNome());
      }else{
        bw.write(disciplina.getNome()+", "+disciplina.getTipo()+", "+disciplina.getCargaHoraria()+", "+disciplina.getCurso().getNome());
      }
    }
    bw.close();
}
public void removerDisciplina(String nome)throws IOException {
    List<Disciplina> disciplinas= getAllDisciplinas();
    BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
    for(Disciplina disciplina: disciplinas){
        if(!disciplina.getNome().equals(nome)){
            bw.write(disciplina.getNome()+", "+disciplina.getTipo()+", "+disciplina.getCargaHoraria()+", "+disciplina.getCurso().getNome());
            bw.newLine();
        }
    }
    bw.newLine();
}
}