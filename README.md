# Project Reviewer

Projeto desenvolvido em Java com Spring Boot para a missão **Project Reviewer**.

## Sobre o projeto

O **Project Reviewer** é uma API que simula um agente de IA para correção de trabalhos de alunos.

A aplicação recebe os dados do aluno e a descrição do trabalho entregue, gera uma nota automática, cria um feedback de avaliação e salva essas informações em um banco de dados H2.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Spring AI
- Maven

## Como rodar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/GabrielNakamura123456/project-reviewer.git
```

### 2. Entrar na pasta do projeto

```bash
cd project-reviewer/project-reviewer
```

### 3. Rodar a aplicação

No terminal, execute:

```bash
mvn spring-boot:run
```

Ou abra o projeto no IntelliJ e rode a classe:

```text
ProjectReviewerApplication
```

### 4. Verificar se a API está funcionando

Abra no navegador:

```text
http://localhost:8080/correcoes
```

Se aparecer:

```json
[]
```

significa que a API está funcionando e o banco ainda está vazio.

## Como testar o cadastro de correção

Com a aplicação rodando, execute no PowerShell:

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/correcoes" -Method POST -ContentType "application/json" -Body '{"nomeAluno":"Gabriel Nakamura","rm":"560671","descricaoTrabalho":"O aluno criou uma API Java com Spring Boot usando controller, service, repository, banco H2 e documentacao no README."}'
```

A API deve retornar uma correção com `id`, `nota`, `feedback` e `dataCorrecao`.

Depois acesse novamente:

```text
http://localhost:8080/correcoes
```

Agora a correção cadastrada deverá aparecer salva.

## Endpoints

### Criar correção

```http
POST /correcoes
```

Exemplo de JSON:

```json
{
  "nomeAluno": "Gabriel Nakamura",
  "rm": "560671",
  "descricaoTrabalho": "O aluno criou uma API Java com Spring Boot usando controller, service, repository, banco H2 e documentacao no README."
}
```

### Listar correções

```http
GET /correcoes
```

Exemplo:

```text
http://localhost:8080/correcoes
```

### Buscar correção por ID

```http
GET /correcoes/{id}
```

Exemplo:

```text
http://localhost:8080/correcoes/1
```

## Banco de dados H2

O projeto utiliza banco H2 em memória.

Console do H2:

```text
http://localhost:8080/h2-console
```

Configurações para acessar o H2:

```text
JDBC URL: jdbc:h2:mem:projectreviewer
User: sa
Password: deixar vazio
```

## Estrutura do projeto

```text
src/main/java/br/com/fiap/projectreviewer
├── controller
│   └── CorrecaoController.java
├── dto
│   └── CorrecaoRequest.java
├── model
│   └── Correcao.java
├── repository
│   └── CorrecaoRepository.java
├── service
│   └── CorrecaoService.java
└── ProjectReviewerApplication.java
```

## Observação sobre Spring AI

O projeto possui a dependência do **Spring AI** configurada no `pom.xml`.

Nesta versão, a correção roda em modo demo para evitar custos com API externa. O sistema simula o comportamento de um agente avaliador usando critérios automáticos.

## Autor

Gabriel Nakamura Ogata  
RM: 560671
