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

public class DaoCurso {
    private static final String FILE_PATH="Enrollment-System\\Implementação\\src\\main\\java\\org\\example\\Data\\Cursos.txt";

    public void adcionarCurso(Curso cursoObj)throws IOException{
        BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH,true));
        String disciplinas="";
        List<Disciplina> listDeDisciplinas=cursoObj.getDisciplinas();
        for(Disciplina d:listDeDisciplinas){
            disciplinas+= d.getNome()+", ";
            
        }
        
        String line= cursoObj.getNome()+", "+cursoObj.getCreditos()+", "+disciplinas;
        bw.write(line);
        bw.newLine();
        bw.close();
    }

    public List<Curso> getAllCursos() throws IOException{
        List<Curso> cursos= new ArrayList<>();
        BufferedReader br= new BufferedReader(new FileReader(FILE_PATH));
        String linha;
        while((linha=br.readLine()) !=null){
            String[] dados=linha.split(", ",4);
            if(dados.length==4){
                Curso ObjCurso= new Curso(dados[0],null);
                cursos.add(ObjCurso);
                
            }
        }
        br.close();
        return cursos;
        
    }

    public Curso getCursoByNome(String nome) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while((line=br.readLine())!=null){
           String[] dados= line.split(", ");
           if(dados[0].equals(nome)){
             br.close();
             return new Curso(nome,null);
           }
        }
        return null;
    }

    public void atualizarCurso(Curso cursoAtualizado) throws IOException{
        List<Curso> cursos=getAllCursos();
        BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
        for (Curso curso:cursos) {
            String disciplinas="";
            List<Disciplina> listDeDisciplinas=cursoAtualizado.getDisciplinas();
            for(Disciplina d:listDeDisciplinas){
                disciplinas+= d.getNome()+", ";
                
            }
            bw.write(cursoAtualizado.getNome()+", "+cursoAtualizado.getNome()+", "+curso.getCreditos()+", "+disciplinas);
            bw.newLine(); 
        }
        bw.close();
    }
    
    public void removerCurso(String nome)throws IOException{
       List<Curso> cursos= getAllCursos();
       BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
       for(Curso curso: cursos){
        if(!curso.getNome().equals(nome)){
          String disciplinas= String.join(", ", curso.getDisciplinas().toString());
          bw.write(curso.getNome()+", "+curso.getCreditos()+", "+disciplinas);
          bw.newLine();
        }
       }
       bw.close();
    }
}
