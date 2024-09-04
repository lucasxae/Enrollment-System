package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.DAO.*;
import org.example.Enum.TipoDisciplina;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DaoAluno daoAluno = new DaoAluno();
        DaoCurso daoCurso = new DaoCurso();
        DaoDisciplinas daoDisciplinas = new DaoDisciplinas();
        DaoProfessor daoProfessor = new DaoProfessor();

        Curso cursoPrincipal = new Curso("Engenharia de Software", 180.00);

        Aluno alunoLogado = null;
        Professor profLogado = null;
        daoCurso.adcionarCurso(cursoPrincipal);

        while (true) {
            System.out.println("\n[Sistema de Matrículas - Menu principal]\n");
            System.out.println("1 - Acessar funcionalidades do Curso");
            System.out.println("2 - Acessar funcionalidades do Aluno");
            System.out.println("3 - Acessar funcionalidades do professor");
            System.out.println("4 - Sair\n");

            System.err.print("Selecione uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    menuCurso(cursoPrincipal);
                    break;
                case 2:
                if (alunoLogado == null) {
                    System.out.print("Digite seu nome: ");
                    String nomeAluno = scanner.nextLine();
                    alunoLogado = daoAluno.getByName(nomeAluno);
                    if(alunoLogado==null){
                        System.out.println("Aluno não encontrado no sistema (1- Para efetuar cadastro|2- Para sair)");
                        int op=scanner.nextInt();
                        scanner.nextLine();
                        switch(op){
                            case 1:
                            System.out.println("Digite seu nome");
                            String nomeAl=scanner.nextLine();

                            Aluno novo= new Aluno(cursoPrincipal, nomeAl);
                            daoAluno.adicionarAluno(novo);
                            novo.setCurso(cursoPrincipal);
                            System.out.println("Cadastro efetuado");
                            return;

                            case 2:
                            return ;
                            default:
                            System.out.println("Opção invalida");
                        }
                    }else{
                    }
                    alunoLogado.setCurso(cursoPrincipal);
                    menuAluno(alunoLogado);
                }
                    break;
                case 3:
                    if (profLogado == null) {
                        System.out.print("Digite seu nome: ");
                        String nomeProf = scanner.nextLine();
                        profLogado = new Professor(nomeProf);
                        daoProfessor.adicionarProfessor(profLogado); 
                        menuProfessor(profLogado);
                    }
                    break;
                case 4:
                    System.out.println("Saindo. . .");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERRO: Opção inválida! Tente novamente.");
            }
        }
    }

    private static List<Turma> lerTurmasDoArquivo(String filePath) {
        List<Turma> turmas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(", ");
                if (dados.length == 2) {
                    String codigo = dados[0];
                    String nomeProfessor = dados[1];
                    Professor professor = new Professor(null, nomeProfessor);
                    Turma turma = new Turma(codigo, professor, null, null);
                    turmas.add(turma);
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível ler o arquivo de turmas.");
        }
        return turmas;
    }

    private static List<Turma> adicionarTurmas(Scanner s, int qtdTurmas, List<Turma> turmas) throws IOException {
        DaoTurma daoTurma = new DaoTurma();
        for (int i = 1; i <= qtdTurmas; i++) {
            System.out.print("Digite o código da turma " + i + ": ");
            String codigo = s.next();

            System.out.print("Insira o nome do professor que irá ministrar a turma " + i + ": ");
            String nomeProf = s.next();

            Professor professor = new Professor(null, nomeProf);
            Turma turma = new Turma(codigo, professor, null, "2024.2");

            turmas.add(turma);


            professor.adicionarTurma(turma);
          //  daoTurma.adcionarTurma(turma);

        }

        return turmas;
    }

    private static TipoDisciplina obterTipoDisciplina(Scanner s) {
        System.out.print("Qual o tipo da disciplina? (1 - OBRIGATÓRIA ou 2 - OPTATIVA): ");
        if (s.nextInt() == 1) {
            return TipoDisciplina.OBRIGATORIA;
        } else {
            return TipoDisciplina.OPTATIVA;
        }
    }

    public static void menuCurso(Curso cursoPrincipal) throws IOException {
        Scanner s = new Scanner(System.in);
        DaoDisciplinas daoDisciplinas = new DaoDisciplinas();
        DaoAluno daoAluno = new DaoAluno();

        System.out.println("\n[Curso - O que deseja fazer?]\n");
        System.out.println("1 - Adicionar disciplina");
        System.out.println("2 - Remover disciplina");
        System.out.println("3 - Gerar currículo");
        System.out.println("4 - Listar disciplinas cadastradas");
        System.out.println("5 - Sair\n");

        System.out.print("Selecione uma opção: ");
        int opcao = s.nextInt();
        s.nextLine();

        switch (opcao) {
            case 1:
                try {
                    System.out.print("Digite o nome da disciplina: ");
                    String Dnome = s.nextLine();

                    System.out.print("Digite a carga horária da disciplina: ");
                    Float ch = s.nextFloat();

                    System.out.print("Quantas turmas deseja adicionar? ");
                    int qtdTurmas = s.nextInt();

                    List<Turma> turmas = new ArrayList<>();
                    turmas = adicionarTurmas(s, qtdTurmas, turmas);

                    TipoDisciplina tipo = obterTipoDisciplina(s);

                    Disciplina nova = new Disciplina(true, cursoPrincipal, tipo, turmas, Dnome, ch);
                    nova.setTurmas(turmas);
                    cursoPrincipal.adicionarDisciplina(nova);
                    daoDisciplinas.adicionarDisciplinas(nova);

                } catch (Exception e) {
                    System.out.println("ERRO: Não foi possivel adicionar uma nova disciplina.");
                }
                break;
            case 2:
                try {
                    System.out.print("Digite o nome da disciplina que deseja excluir: ");
                    String nomeDisciplina = s.nextLine();
                    daoDisciplinas.removerDisciplina(nomeDisciplina);
                } catch (Exception e) {
                    System.out.println("ERRO: Não foi possivel excluir esta disciplina.");
                }
            case 3:
                try {
                    String curriculo = cursoPrincipal.gerarCurriculoSemestral();
                    System.out.println(curriculo);
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("ERRO: Não foi possivel gerar o currículo.");
                }
                break;
            case 4:
                try {
                    List<Disciplina> disc = daoDisciplinas.getAllDisciplinas();
                    System.out.println("Disciplinas:");
                    int i = 0;
                    for (Disciplina disciplina : disc) {
                        i++;
                        System.out.println(i + " - " + disciplina.getNome());
                    }
                } catch (Exception e) {
                    System.out.println("ERRO: Não foi possivel listar as disciplinas.");
                }
                break;
            case 5:
                System.out.println("Voltando para o menu principal. . .");
                return;
            default:
                System.out.println("ERRO: Opção inválida! Tente novamente.");
        }
    }

    public static void menuAluno(Aluno aluno) throws IOException {
        Scanner b = new Scanner(System.in);
        DaoAluno daoAluno = new DaoAluno();
        DaoDisciplinas daoDisciplinas = new DaoDisciplinas();
        System.out.println("\n[Aluno: " + aluno.getNome() + " - O que deseja fazer?]\n");
        System.out.println("1- Matricular em uma disciplina\n");
        System.out.println("2- Cancelar matricula\n");
        System.out.println("3- Sair\n");

        System.out.println("Selecione uma opção");

        int opcao = b.nextInt();

        switch (opcao) {
            case 1:

                List<Disciplina> disc = daoDisciplinas.getAllDisciplinas();
                System.out.println("Disciplinas:");
                for (Disciplina disciplina : disc) {
                    System.out.println(disciplina.getNome());
                }

                System.out.println("Digite o nome da disciplina: ");
                String Dnome = b.next();

                Disciplina novaMatricula = daoDisciplinas.getDisciplinaBynome(Dnome);
                if (novaMatricula == null) {
                    System.out.println("ERRO: Disciplina não encontrada.");
                }

                // aluno.matricularDisciplina(novaMatricula);
                daoAluno.adicionarAluno(aluno);

                break;
            case 2:
                try {
                    System.out.print("Digite o nome da disciplina que deseja cancelar a matrícula: ");
                    String nomeDisciplina = b.next();
                    System.out.print("Digite seu nome de matricula:  ");
                    String nomeLogin = b.next();
                    Disciplina disciplina = new Disciplina(nomeDisciplina);
                    aluno.cancelarMatricula(disciplina);
                    daoAluno.removerAluno(nomeLogin);
                } catch (Exception e) {
                    System.out.println("ERRO: Não foi possível cancelar a matrícula.");
                }
                break;

            case 3:
                System.out.println("Voltando para o menu principal. . .");
                return;
            default:
                System.out.println("ERRO: Opção inválida! Tente novamente.");
        }
    }

    public static void menuProfessor(Professor prof) throws IOException {
        Scanner d = new Scanner(System.in);
        DaoTurma daoTurma = new DaoTurma();
        System.out.println("\n[Professor: \n" + prof.getNome() + " - O que deseja fazer?]\n");
        System.out.println("1 - Ver alunos Matriculados\n");
        System.out.println("2 - Sair");

        System.out.println("Escolha uma opção");
        int opcao = d.nextInt();
        switch (opcao) {
            case 1:
                Disciplina DisciplinaProc = prof.getTurma().getDisciplina();
                prof.checarAlunos(DisciplinaProc);
                daoTurma.getTurmaByProfessor(prof.getNome());
                break;
            case 2:
                System.out.println("Voltando para o menu principal. . .");
            default:
                System.out.println("ERRO: Opção inválida! Tente novamente.");

        }
    }
}
