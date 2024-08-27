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

- [x] Diagrama de Caso de Uso em PlantUML
- [x] Descrição das histórias de usuário em Markdown

#### Laboratório 01 - Sprint 02

- [x] Diagrama de Classes em PlantUML
- [x] Criação do Projeto Java com as classes, assinatura dos métodos e atributos

---
#### Histórias de Usuário

##### US01 - Gerar currículo semestral

**Como** Secretaria da Universidade, **quero** gerar um currículo semestral **para** informar os alunos sobre as ofertas de disciplinas e o corpo docente que irá ministrar as matérias.

**Critérios de Aceite**
- A secretaria gera um novo currículo para o semestre
- O currículo deve incluir informações sobre as disciplinas, professores e alunos
- O currículo pode ser salvo e editado até o início do período de matrículas

---
##### US02 - Cadastrar cursos

**Como** Secretaria da Universidade, **quero** cadastrar cursos com suas respectivas disciplinas **para** que os alunos possam se matricular e escolher suas matérias de acordo com a necessidade e carga horária.

**Critérios de Aceite**
- A secretaria pode cadastrar novos cursos com nomes, números de créditos e respectivas disciplinas que constituirão a grade curricular
- As disciplinas devem ser associadas corretamente aos cursos
- As disciplinas cadastradas devem aparecer no currículo do semestre

---
##### US03 - Matricular em disciplina obrigatória

**Como** aluno, **quero** me matricular em uma disciplina obrigatória **para**  compor a minha grade curricular do semestre e cumprir os requisitos do meu curso.

**Critérios de Aceite**
- O aluno pode visualizar todas as disciplinas obrigatórias disponíveis
- O aluno pode selecionar até 4 disciplinas obrigatórias durante o período de matrículas
- O sistema deve validar e impedir que o aluno se matricule em mais de 4 disciplinas obrigatórias
- As disciplinas selecionadas pelo aluno devem ser salvas e confirmadas após a conclusão do processo de matrícula

---
##### US04 - Matricular em disciplina optativa
**Como** aluno, **quero** me matricular em uma disciplina optativa, **para** compor a minha grade curricular do semestre e expandir meus conhecimentos para outras áreas.

**Critérios de Aceite**
- O aluno pode visualizar todas as disciplinas optativas disponíveis
- O aluno pode selecionar até 2 disciplinas optativas durante o período de matrículas
- O sistema deve validar e impedir que o aluno se matricule em mais de 2 disciplinas optativas
- As disciplinas optativas selecionadas devem ser salvas e confirmadas após a conclusão do processo de matrícula

---
##### US05 - Cancelar matrícula em disciplina
**Como** aluno, **quero** poder cancelar a matrícula de uma disciplina durante o período de efetuação permitido, **para** que eu possa ajustar a minha grade e carga horária de acordo com as minhas necessidades.

**Critérios de Aceite**
- O aluno pode visualizar uma lista de disciplinas nas quais está matriculado
- O aluno pode cancelar a matrícula em qualquer disciplina durante o período permitido
- O cancelamento da matrícula deve ser confirmado antes de ser efetivado.
- O sistema deve atualizar a lista de disciplinas matriculadas após o cancelamento

---
##### US06 - Verificar informações de disciplinas
**Como** aluno, **quero** verificar informações gerais de uma disciplina como turma, professor, número de alunos e status (ativa ou cancelada), **para** que eu possa planejar meu semestre adequadamente.

**Critérios de Aceite**
- O aluno deve poder verificar informações gerais de uma disciplina
- O aluno deve ser notificado se a disciplina está ativa ou foi cancelada

---
##### US07 - Cancelar disciplina
**Como** Secretaria da Universidade, **quero** cancelar a oferta de uma disciplina, **para** caso a mesma não tenha atingido o número mínimo de inscrições.

**Critérios de Aceite**
- O sistema deve verificar o número de alunos matriculados em cada disciplina no final do período de matrículas
- Se uma disciplina tiver menos de 3 alunos matriculados, ela é cancelada
- O aluno deve ser notificado se a disciplina for cancelada
- O sistema deve anular todas as matrículas feitas na disciplina que foi cancelada
- O sistema deve permitir que o aluno escolha outra disciplina em caso da descontinuação da disciplina

---
##### US08 - Limite de vagas em disciplinas
**Como** aluno, **quero** saber o número de vagas disponíveis em cada disciplina durante o processo de matrícula, **para** que eu possa decidir em quais disciplinas me matricular com base na disponibilidade.

**Critérios de Aceite**

- O sistema deve exibir o número de vagas disponíveis em cada disciplina para o aluno
- O sistema deve validar e impedir a matrícula em disciplinas que já atingiram o limite de 60 alunos.
- O aluno é notificado se uma disciplina escolhida atingir o limite de vagas durante o processo de matrícula.
- O sistema deve atualizar o número de vagas em função de novas matrículas/cancelamentos

---
##### US09 - Notificação do Sistema de Cobranças
**Como** um sistema de matrículas, **quero** notificar o sistema de cobranças após o aluno se inscrever no semestre, **para** que o aluno seja cobrado corretamente de acordo com as matérias escolhidas. 

**Critérios de Aceite**
- O sistema de matrículas envia uma notificação ao sistema de cobranças após a matrícula ser confirmada
- A notificação inclui a lista de disciplinas nas quais o aluno está matriculado
- O sistema de cobranças confirma o recebimento da notificação
- O aluno é cobrado corretamente pelas disciplinas selecionadas

---
##### US10 - Acessar alunos matriculados
**Como** um professor, **quero** acessar o sistema e visualizar a lista de alunos matriculados em cada disciplina que estou lecionando, **para** que eu possa acompanhar e gerenciar os alunos inscritos em minhas turmas.

**Critérios de Aceite**
- O professor pode visualizar a lista completa de alunos matriculados em cada uma de suas disciplinas
- A lista de alunos inclui informações relevantes como nome e número de matrícula
- O professor pode acessar e exportar essa lista como um arquivo