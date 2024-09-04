package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.example.DAO.DaoAluno;
import org.example.DAO.DaoCurso;
import org.example.DAO.DaoDisciplinas;
import org.example.DAO.DaoProfessor;
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
                        menuAluno(alunoLogado);
                    }
                    break;
                case 3:
                    if (profLogado == null) {
                        System.out.print("Digite seu nome: ");
                        String nomeProf = scanner.nextLine();
                        profLogado = daoProfessor.getByNome(nomeProf);
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

    private static List<Turma> adicionarTurmas(Scanner s, int qtdTurmas, List<Turma> turmas) {
        for (int i = 1; i <= qtdTurmas; i++) {
            System.out.print("Digite o código da turma " + i + ": ");
            String codigo = s.next();

            System.out.print("Insira o nome do professor que irá ministrar a turma " + i + ": ");
            String nomeProf = s.next();

            Professor professor = new Professor(null, nomeProf);
            Turma turma = new Turma(codigo, professor, null, null);

            turmas.add(turma);
            professor.adicionarTurma(turma);
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

                    cursoPrincipal.adicionarDisciplina(nova);
                    // daoDisciplinas.adicionarDisciplinas(nova);
                } catch (Exception e) {
                    System.out.println("ERRO: Não foi possivel adicionar uma nova disciplina.");
                }
                break;
            case 2:
                try {
                    System.out.print("Digite o nome da disciplina que deseja excluir: ");
                    String nomeDisciplina = s.nextLine();

                    List<Disciplina> disciplinasDoCurso = cursoPrincipal.getDisciplinas();

                    Iterator<Disciplina> iterator = disciplinasDoCurso.iterator();
                    while (iterator.hasNext()) {
                        Disciplina disciplina = iterator.next();
                        if (disciplina.getNome().equals(nomeDisciplina)) {
                            iterator.remove();
                            // daoDisciplinas.removerDisciplina(disciplina.getNome());
                        }
                    }
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
                    List<Disciplina> disciplinas = cursoPrincipal.getDisciplinas();
                    if (disciplinas.isEmpty()) {
                        System.out.println("ERRO: Não existem disciplinas cadastradas.");
                    }
                    for (Disciplina disciplina : disciplinas) {
                        System.out.println("Disciplinas: ");
                        System.out.println(disciplina.getNome());
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

    public static void menuAluno(Aluno aluno) {
        Scanner b = new Scanner(System.in);
        System.out.println("\n[Aluno: " + aluno.getNome() + " - O que deseja fazer?]\n");
        System.out.println("1- Matricular em uma disciplina\n");
        System.out.println("2- Cancelar matricula\n");
        System.out.println("3- Sair\n");

        System.out.println("Selecione uma opção");

        int opcao = b.nextInt();

        switch (opcao) {
            case 1:
                List<Disciplina> disciplinasDisponiveis = aluno.getCurso().getDisciplinas();
                for (Disciplina disciplina : disciplinasDisponiveis) {
                    System.out.println("Disciplina: " + disciplina.getNome());
                    String nomeDisciP = b.nextLine();
                    for (Disciplina d : disciplinasDisponiveis) {
                        if (d.getNome().equals(nomeDisciP)) {
                            aluno.matricularDisciplina(d);
                        }
                    }
                    try {

                    } catch (Exception e) {
                        System.out.println("ERRO: Não foi possivel cadastrar na disciplina.");
                    }
                }
                break;
            case 2:
                try {
                    List<Disciplina> disciplinasDocurso = aluno.getCurso().getDisciplinas();
                    for (Disciplina disciplinaP : disciplinasDocurso) {
                        List<Turma> listaDeTurmas = disciplinaP.getTurmas();
                        for (Turma turma : listaDeTurmas) {
                            List<Aluno> alunos = turma.getAlunos();
                            for (Aluno procurado : alunos) {
                                if (procurado.getNome().equals(aluno.getNome())) {
                                    procurado = aluno;
                                    procurado.matricularDisciplina(disciplinaP);
                                }
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println("ERRO: Não foi possivel cancelar a matrícula desta disciplina.");
                }
                break;
            case 3:
                System.out.println("Voltando para o menu principal. . .");
                return;
            default:
                System.out.println("ERRO: Opção inválida! Tente novamente.");
        }
    }

    public static void menuProfessor(Professor prof) {
        Scanner d = new Scanner(System.in);
        System.out.println("\n[Professor: \n" + prof.getNome() + " - O que deseja fazer?]\n");
        System.out.println("1 - Ver alunos Matriculados\n");
        System.out.println("2 - Sair");

        System.out.println("Escolha uma opção");
        int opcao = d.nextInt();
        switch (opcao) {
            case 1:
                Disciplina DisciplinaProc = prof.getTurma().getDisciplina();
                prof.checarAlunos(DisciplinaProc);
                break;
            case 2:
                System.out.println("Voltando para o menu principal. . .");
            default:
                System.out.println("ERRO: Opção inválida! Tente novamente.");

        }
    }
}
