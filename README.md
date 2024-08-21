## Sistema de Matrículas
### Descrição do Problema

Uma universidade pretende informatizar seu sistema de matrículas. A secretaria da universidade gera o currículo para cada semestre e mantém as informações sobre as disciplinas, professores e alunos.

<details>
<summary><b>Mais informações</b></summary>

Cada curso tem um nome, um determinado número de créditos e é constituído por diversas disciplinas.

Os alunos podem se matricular em 4 disciplinas como 1ª opção (obrigatórias) e em mais 2 outras alternativas (optativas).

Há períodos para efetuar matrículas, durante os quais um aluno pode acessar o sistema para se matricular em disciplinas e/ou para cancelar matrículas feitas anteriormente.

Uma disciplina só fica ativa, isto é, só vai ocorrer no semestre seguinte se, no final do período de matrículas tiver, pelo menos, 3 alunos inscritos (matriculados). Caso contrário, a disciplina será cancelada. O número máximo de alunos inscritos a uma disciplina é de 60 e quando este número é atingido, as inscrições (matrículas) para essa disciplina são encerradas.

Após um aluno se inscrever para um semestre, o sistema de cobranças é notificado pelo sistema de matrículas, de modo que o aluno possa ser cobrado pelas disciplinas daquele semestre.

Os professores podem acessar o sistema para saber quais são os alunos que estão matriculados em cada disciplina.

Todos os usuários do sistema têm senhas que são utilizadas para validação do respectivo login.

</details>

#### Laboratório 01 - Sprint 01

Neste laboratório, vamos projetar um Sistema de Matrículas para uma Universidade.

- [x] Diagrama de Caso de Uso em PlantUML
- [x] Descrição das histórias de usuário em Markdown

---
#### Histórias de Usuário

##### US01 - Gerar currículo semestral

**Como** Secretaria da Universidade, **quero** gerar um currículo semestral **para** informar os alunos sobre as ofertas de disciplinas e o corpo docente que irá ministrar as matérias.

---
##### US02 - Cadastrar cursos e disciplinas

**Como** Secretaria da Universidade, **quero** cadastrar cursos com suas respectivas disciplinas **para** que os alunos possam se matricular e escolher suas matérias de acordo com a necessidade e carga horária.

---
##### US03 - Matricular em disciplina obrigatória

**Como** aluno, **quero** me matricular em uma disciplina obrigatória **para**  compor a minha grade curricular do semestre e cumprir os requisitos do meu curso.

---
##### US04 - Matricular em disciplina optativa
**Como** aluno, **quero** me matricular em uma disciplina optativa, **para** compor a minha grade curricular do semestre e expandir meus conhecimentos para outras áreas.

---
##### US05 - Cancelar matrícula
**Como** aluno, **quero** poder cancelar a matrícula de uma disciplina durante o período de efetuação permitido, **para** que eu possa ajustar a minha grade e carga horária de acordo com as minhas necessidades.

---
##### US06 - Verificar informações sobre disciplinas
**Como** aluno, **quero** saber informações básicas sobre uma disciplina, como status (ativada ou desativada), número de vagas e entre outros, **para** que eu possa planejar meu semestre adequadamente e decidir em quais disciplinas me matricular.

---
##### US07 - Notificação do Sistema de Cobranças
**Como** um sistema de matrículas, **quero** notificar o sistema de cobranças após o aluno se inscrever no semestre, **para** que o aluno seja cobrado corretamente de acordo com as matérias escolhidas. 

---
##### US08 - Acessar alunos matriculados
**Como** um professor, **quero** acessar o sistema e visualizar a lista de alunos matriculados em cada disciplina que estou lecionando, **para** que eu possa acompanhar e gerenciar os alunos inscritos em minhas turmas.

---
##### US09 - Autenticação por senha 
**Como** um usuário do sistema, **quero** utilizar uma senha segura para fazer login, **para** que eu possa acessar minha conta de forma segura e acessar as funcionalidades que são específicas ao meu perfil.

---
##### US10 - Cancelar disciplina
**Como** Secretaria da Universidade, **quero** cancelar a oferta de uma disciplina, **para** caso a mesma não tenha atingido o número mínimo de inscrições

---
##### US11 - Cancelar matrícula para disciplina
**Como** Secretaria da Universidade, **quero** cancelar matrículas feitas para uma disciplina, **para** caso a disciplina tenha sido cancelada ou já tenha atingido o número máximo de alunos.