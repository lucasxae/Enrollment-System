package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.example.DAO.DaoAluno;
import org.example.DAO.DaoCurso;
import org.example.DAO.DaoDisciplinas;
import org.example.DAO.DaoProfessor;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        DaoAluno daoAluno=new DaoAluno();
        DaoCurso daoCurso= new DaoCurso();
        DaoDisciplinas daoDisciplinas=new DaoDisciplinas();
        DaoProfessor daoProfessor=new DaoProfessor();

        Curso cursoPrincipal= new Curso("Engenharia de Software", 180.00);
        Aluno alunoLogado=null;
        Professor profLogado=null;

        while (true) { 
            System.out.println("\nSistema de Mtrículas - Menu principal\n");
            System.out.println("1- Acessar funcionalidades do Curso\n");
            System.out.println("2- Acessar funcionalidades do Aluno\n");
            System.out.println("3- Acessar funcionalidades do professor\n");
            System.out.println("4- Sair\n");

            System.err.println("Selecione uma opção\n");
            int opcao=scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    menuCurso(cursoPrincipal);
                    break;
                case 2:
                if(alunoLogado==null){
                  System.out.println("Digite seu nome");
                  String nomeAluno=scanner.nextLine();
                  alunoLogado=daoAluno.getByName(nomeAluno);
                  menuAluno(alunoLogado);
                }
                break;
                case 3:
                if(profLogado==null){
                    System.out.println("Digite seu nome");
                    String nomeProf=scanner.nextLine();
                    profLogado=daoProfessor.getByNome(nomeProf);
                    menuProfessor(profLogado);
                }
                break;
                case 4:
                System.out.println("Saindo.....");
                scanner.close();
                System.exit(0);
                break;
                default:
                    System.out.println("Opção inválida tente novamente.");
            }
        }

    
    }
    public static void menuCurso(Curso cursoPrincipal) throws IOException{
       Scanner s = new Scanner(System.in);
       DaoDisciplinas daoDisciplinas=new DaoDisciplinas();
       DaoAluno daoAluno=new DaoAluno();
       
       
       System.out.println("\nO que deseja fazer?\n");
       System.out.println("1- Adicionar disciplina\n");
       System.out.println("2- Retirar disciplina");
       System.err.println("3- Gerar curriculo\n");
       System.out.println("4- Sair\n");

       System.out.println("Selecione uma opção\n");
       int opcao=s.nextInt();
       s.nextLine();

       switch (opcao) {
           case 1:
               try{
               System.out.println("Digite o nome da disciplina");
               String Dnome=s.nextLine();
               System.out.println("Digite a carga horaria do curso");
               Float ch=s.nextFloat();
               Disciplina nova= new Disciplina(true,cursoPrincipal,null,null, Dnome,ch);
               cursoPrincipal.adicionarDisciplina(nova);
               daoDisciplinas.adicionarDisciplinas(nova);
               } catch (Exception e) {
                System.out.println("Não foi possivel adicionar Disciplina");
               }
               break;
               case 2:
               try{
               System.out.println("Digite o nome da disciplina que deseja excluir");
               Disciplina procurada=daoDisciplinas.getDisciplinaBynome(s.nextLine());
               daoDisciplinas.removerDisciplina(procurada.getNome());
               cursoPrincipal.removerDisciplina(procurada);
            }catch(Exception e){
                System.out.println("Não foi possivel excluir esta disciplina");
            }
               case 3:
               try {
                   cursoPrincipal.gerarCurriculoSemestral();
               } catch (Exception e) {
                   System.out.println("Não foi possivel gerar curriculo");
               }
               break;
               case 4:
               System.out.println("Voltando para o menu principal");
               return;
               default:
               System.out.println("Opção invalida tente novamente");
           
       }
    }
    public static void menuAluno(Aluno aluno){
        
        Scanner b = new Scanner(System.in);
        System.out.println("\nAluno: "+aluno.getNome()+" o que deseja fazer?\n");
        System.out.println("1- Matricular em uma disciplina\n");
        System.out.println("2- Cacelar matricula\n");
        System.out.println("3- Sair\n");

        System.out.println("Selecione uma opção");
        
        int opcao=b.nextInt();

        switch (opcao) {
            case 1:
                List<Disciplina> disciplinasDisponiveis=aluno.getCurso().getDisciplinas();
                for(Disciplina disciplina: disciplinasDisponiveis){
                    System.out.println("Disciplina: "+disciplina.getNome());
                    String nomeDisciP=b.nextLine();
                    for(Disciplina d: disciplinasDisponiveis){
                        if(d.getNome().equals(nomeDisciP)){
                            aluno.matricularDisciplina(d);
                        }
                    }
                try{
                   
            }catch(Exception e){
                System.out.println("Não foi possivel cadastrar na disciplina");
            }
        }
        break;
            case 2:
            try {
                  List<Disciplina> disciplinasDocurso=aluno.getCurso().getDisciplinas();
                  for(Disciplina disciplinaP:disciplinasDocurso){
                    List<Turma> listaDeTurmas=disciplinaP.getTurmas();
                    for (Turma turma:listaDeTurmas) {
                        List<Aluno>alunos=turma.getAlunos();
                        for(Aluno procurado:alunos){
                            if(procurado.getNome().equals(aluno.getNome())){
                                procurado=aluno;
                                procurado.matricularDisciplina(disciplinaP);
                        }
                    }
                }
            }
                
            } catch (Exception e) {
                System.out.println("Não foi possivel desmatricular desta disciplina");
            }
            break;
            case 3:
            System.out.println("Voltando para o menu principal");
            return;
            default: 
            System.out.println("Opção invalida tente novamente");
        }
    }
    public static void menuProfessor(Professor prof){
        Scanner d= new Scanner(System.in);
        System.out.println("\nProfessor: \n"+prof.getNome());
        System.out.println("1- Ver alunos Matriculados\n");
        System.out.println("2- sair");
        
        System.out.println("Escolha uma opção");
        int opcao=d.nextInt();
        switch (opcao) {
            case 1:
            Disciplina DisciplinaProc=prof.getTurma().getDisciplina();
            prof.checarAlunos(DisciplinaProc);
                break;
                case 2:
                System.out.println("Voltando para o menu principal");
                default:
                System.out.println("Opção invalida tente novamente");
            
        }

    }
    }
