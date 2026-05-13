# Project Reviewer

Projeto desenvolvido em Java com Spring Boot para a missão **Project Reviewer**.

## Sobre o projeto

O **Project Reviewer** é uma API que usa **Spring AI com Ollama** para corrigir trabalhos de alunos.

O sistema recebe o nome do aluno, RM e a descrição do trabalho. Depois, a IA gera uma nota e um feedback. Essas informações são salvas no banco de dados H2.

## Tecnologias usadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Spring AI
- Ollama
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

### 3. Instalar o Ollama

Baixe e instale o Ollama:

```text
https://ollama.com/download
```

Depois, no terminal, teste se instalou:

```bash
ollama --version
```

### 4. Baixar o modelo da IA

No terminal, rode:

```bash
ollama pull llama3.2:1b
```

### 5. Rodar o projeto

Com o Ollama instalado, rode o projeto:

```bash
mvn spring-boot:run
```

Ou abra no IntelliJ e execute a classe:

```text
ProjectReviewerApplication
```

### 6. Testar se a API está funcionando

Abra no navegador:

```text
http://localhost:8080/correcoes
```

Se aparecer `[]`, significa que a API está funcionando e ainda não tem nenhuma correção salva.

## Como cadastrar uma correção

Com o projeto rodando, execute este comando no PowerShell:

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/correcoes" -Method POST -ContentType "application/json" -Body '{"nomeAluno":"Gabriel Nakamura","rm":"560671","descricaoTrabalho":"O aluno criou uma API Java com Spring Boot usando controller, service, repository, banco H2, Spring AI com Ollama e documentacao no README."}'
```

A API vai retornar a correção com nota, feedback e data.

Depois acesse novamente:

```text
http://localhost:8080/correcoes
```

A correção cadastrada deverá aparecer salva.

## Endpoints

### Criar correção

```http
POST /correcoes
```

### Listar correções

```http
GET /correcoes
```

### Buscar correção por ID

```http
GET /correcoes/{id}
```

Exemplo:

```text
http://localhost:8080/correcoes/1
```

## Banco H2

Console do H2:

```text
http://localhost:8080/h2-console
```

Configuração:

```text
JDBC URL: jdbc:h2:mem:projectreviewer
User: sa
Password: deixar vazio
```

## Spring AI com Ollama

O projeto usa o `ChatClient` do Spring AI para enviar a descrição do trabalho para o modelo local do Ollama.

Configuração usada no `application.properties`:

```properties
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.options.model=llama3.2:1b
```

## Autor

Gabriel Nakamura Ogata  
RM: 560671
