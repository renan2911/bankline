# BankLine

### Equipe SULTANS OF SPRING 🍀:
- [Fernanda](https://github.com/fernandavianadss)
- [Luiz](https://github.com/LgMotta)
- [André](https://github.com/andre-prado)
- [Renan](https://github.com/renan2911)
  

### Elaboração do projeto :monocle_face:
- **Planning**: entendimento e discussão para elaboração do projeto. Desmembramento e priorização de tasks.
- Durante os dias de desenvolvimento utilizamos ferramentas como github, github projects e google meet para nos auxiliar.
- **Dailys** eram feitas todos os dias durante a noite, onde discutíamos dificuldades e estratégias para as próximas tasks.
- Após a Daily, retornávamos para nossas tasks e quando necessário, fazíamos pair programming.

### GitHub Projects :scroll:
- Escolhemos utilizar o github projects como ferramenta para separação e acompanhamento das tasks. 
- Listamos todas as tasks na coluna **to Do**, e utilizávamos as outras para o desenvolvimento em si: **in progress** e **done**.
- A escolha da próxima task era por ordem de prioridade e assim que escolhida, marcávamos com o nome do membro.

### Github :desktop_computer:
- Mantivemos a `main` como branch principal. Cada task foi feita na branch development, e após a conclusão, era aberta a `pull Request` e pelo menos um membro da equipe precisava fazer a review e aprovar o desenvolvimento, assim, podendo mergear para a branch principal.

### API :gear:
- Utilizamos o spring boot com as seguintes depêndencias: Spring Data JPA, Spring Security, JJWT, Spring Web e swagger.
- Utilizamos o banco de dados **MySQL**, que foi hospedado no herokuapp.

### Swagger
[Swagger doc](https://bankline-api-sultans-of-spring.herokuapp.com/swagger-ui.html)

### Base API URL - Heroku
[URL](bankline-api-sultans-of-spring.herokuapp.com/)

### Team :handshake:
- A escolha do time foi feita por sorteio.
- Nossos pontos como equipe foram: `determinação para encontrar boas soluções para vários desafios que aconteceram;`
                                   `Organização de tarefas;`
                                   `Foco e comprometimento para conseguir entregar o projeto final da melhor maneira.`
                                   
            

`-----------------------------------------------------------------`

## Visão Geral
API	                                      | Descrição                        |	Corpo da solicitação     |	Corpo da resposta                                       |
:-------:                                 |:-------:                         |:-------:                  | :-------:                                                |
POST /usuarios                            |	Salvar usuário                   |                           | Id do usuário cadastrado                                 |
POST /login             	                | Autenticação no sistema          |      	                   | Informações da conta e usuário, com token de autenticaçao|
POST /login/alterarSenha                  |	Alteração de senha	             |                           | Senha temporária                                         |
POST /lancamentos                         |	Lançamento                       |                           | Informações referente ao lançamento                      |
GET /planos-conta/{login}                 | Buscar todos os planos de contas |                           | Informações de todos os planos de conta                  |
POST /planos-conta                        | Criar plano de conta             |                           | Nenhum                                                   |
GET /dashboard/login/dataInicio/dataFim   | Extrato de todos os lançamentos  |                           | Extrato com todos os lançamentos de acordo com a data    |
