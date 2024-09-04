package org.example.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.Professor;

public class DaoProfessor {
    public static final String FILE_PATH="Implementação//src//main//java//org//example//Data//Disciplinas.txt";

    public void adicionarProfessor(Professor prof)throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        String linha=prof.getNome()+", ";
        bw.write(linha);
        bw.newLine();
        bw.close();
    }
    public List<Professor> getAllProfessores() throws IOException{
        List<Professor> professores= new ArrayList<>();
        BufferedReader br= new BufferedReader(new FileReader(FILE_PATH));
        String linha;
        while((linha=br.readLine())!=null){
              String[] dados=linha.split(",");
        if(dados.length==4){
            Professor objProf = new Professor(null,dados[0]);
            professores.add(objProf);
        }
    }
    br.close();
    return professores;
        }
    public Professor getByNome(String nome)throws IOException{
        BufferedReader br= new BufferedReader(new FileReader(FILE_PATH));
        String linha;
        while((linha=br.readLine())!=null){
            String[] dados=linha.split(", ");
            if(dados.length==4 && dados[0].equals(nome)){
                br.close();
                return new Professor(null,dados[0]);
            }
        }
        br.close();
        return null;
        
    }
    public void atualizarProfessor(Professor profUpdated) throws  IOException{
       List<Professor> professores =getAllProfessores();
       BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
       for(Professor professor:professores){
        if(!professor.getNome().equals(profUpdated.getNome())){
            bw.write(profUpdated.getNome()+", "+profUpdated.getTurma());
        }else{
            bw.write(professor.getNome()+", "+professor.getTurma());
        }
        bw.newLine();
       }
       bw.close();
    }
    public void excluirProfessor(String nome) throws IOException{
        List<Professor> professores= getAllProfessores();
        BufferedWriter bw= new BufferedWriter(new FileWriter(FILE_PATH));
        for(Professor professor: professores){
            if(!professor.getNome().equals(nome)){
                bw.write(professor.getNome()+", "+professor.getTurma());
                bw.newLine();
            }
        }
        bw.close();
    }
    }

