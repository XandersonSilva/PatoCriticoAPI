

# Pato Crítico 🦆

API de registro e consulta de avaliações de jogos, permitindo que usuários compartilhem suas opiniões e organizem sua biblioteca pessoal de jogos.
A API Patocrítico foi desenvolvida como solução para o desafio técnico do Processo Seletivo 2025.2 para a área de Back-End da IncludeJr. 
O projeto consiste na criação de uma API REST robusta para um site de avaliação de jogos, com o objetivo de centralizar e organizar dados de games, usuários e suas respectivas avaliações.

## 📜 Índice

- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Diagrama do Banco de Dados](#-diagrama-do-banco-de-dados)
- [Endpoints da API](#-endpoints-da-api)
- [Variáveis de Ambiente](#-variáveis-de-ambiente)
- [Como Executar o Projeto](#-como-executar-o-projeto)
  - [Pré-requisitos](#pré-requisitos)
  - [Rodando com Docker (Recomendado)](#-rodando-com-docker-recomendado)
  - [Rodando Localmente](#-rodando-localmente)
- [Documentação Interativa (Swagger)](#-documentação-interativa-swagger)
- [Autor](#-autor)

## ✨ Funcionalidades

- **Gerenciamento de Usuários:** Cadastro, login, validação de e-mail, recuperação de senha e edição de perfil.
- **Catálogo de Jogos:** Administração (CRUD) de jogos no sistema.
- **Avaliações:** Usuários podem criar, editar e excluir suas próprias avaliações para os jogos.
- **Sistema de Votos:** Usuários podem votar em avaliações como "úteis" ou "inúteis".
- **Denúncias:** Sistema para denunciar avaliações que violem as regras, com moderação por administradores.
- **Biblioteca Pessoal:** Usuários podem organizar seus jogos em listas ("Jogando", "Finalizado", "Lista de Desejos").
- **Segurança:** Autenticação baseada em JWT (JSON Web Token) e controle de acesso por papéis (Usuário e Administrador).
- **Notificações:** Envio de e-mails para validação de conta, recuperação de senha e resultados de denúncias.

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído utilizando as seguintes tecnologias:

- **Backend:**
  - **Java 21:** Versão da linguagem de programação.
  - **Spring Boot 3.5.6:** Framework principal para construção da API.
  - **Spring Data JPA:** Para persistência de dados.
  - **Spring Security:** Para autenticação e autorização.
  - **Spring Mail:** Para envio de e-mails.
  - **PostgreSQL:** Banco de dados relacional.
  - **java-jwt:** Biblioteca para criação e validação de tokens JWT.

- **Ferramentas e Outros:**
  - **Maven:** Gerenciador de dependências e build.
  - **Lombok:** Para reduzir código boilerplate (getters, setters, etc.).
  - **MapStruct:** Para mapeamento de DTOs e Entidades.
  - **SpringDoc OpenAPI (Swagger):** Para documentação interativa da API.
  - **Docker:** Para containerização da aplicação.

## 🗃️ Diagrama do Banco de Dados

O diagrama abaixo representa a estrutura física das tabelas e seus relacionamentos no banco de dados PostgreSQL.

![Diagrama de casos de uso](/docs/pato_critico%20-%20ER.png)

## 🌐 Endpoints da API

A seguir estão os endpoints da API, agrupados por funcionalidade/controller.

### Autenticação
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/registrar` | Registra um novo usuário na plataforma. | Público |
| `POST` | `/auth/entrar` | Realiza o login e retorna um token JWT. | Público |
| `GET` | `/auth/validar/{code}` | Valida a conta de um novo usuário via código de e-mail. | Público |

### Usuário
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `PUT` | `/usuario/editar` | Edita as informações do perfil do usuário logado. | Usuário |
| `DELETE` | `/usuario/apagar` | Exclui a conta do usuário logado. | Usuário |
| `GET` | `/usuario/todos` | Busca todos os usuários cadastrados. | Administrador |
| `PATCH` | `/usuario/mudarPapel` | Altera o papel (role) de um usuário específico. | Administrador |

### Recuperação de Senha
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/recuperarSenha/{usuarioEmail}/gerarLink` | Gera e envia um link de recuperação para o e-mail. | Público |
| `PATCH` | `/recuperarSenha/atualizar/{token}` | Atualiza a senha usando um token de recuperação válido. | Público |

### Confirmação e Status do Usuário
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/usuarioConfirmacao/{usuarioId}/inativar` | Bane um usuário, inativando sua conta. | Administrador |
| `POST` | `/usuarioConfirmacao/{usuarioId}/reativar` | Remove o banimento de um usuário. | Administrador |

### Jogos
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/jogo/salvar` | Cadastra um novo jogo no sistema. | Administrador |
| `PUT` | `/jogo/editar` | Edita as informações de um jogo existente. | Administrador |
| `DELETE` | `/jogo/apagar` | Apaga um jogo do sistema. | Administrador |
| `PATCH` | `/jogo/informarGenero` | Associa um gênero a um jogo. | Administrador |
| `PATCH` | `/jogo/adicionarTag` | Adiciona uma tag a um jogo. | Administrador |
| `PATCH` | `/jogo/adicionarPlataforma` | Adiciona uma plataforma a um jogo. | Administrador |
| `GET` | `/jogo/todos` | Busca todos os jogos cadastrados. | Público |
| `GET` | `/jogo/{jogoId}` | Busca um jogo específico pelo ID. | Público |
| `GET` | `/jogo/{jogoId}/avaliacoes` | Busca todas as avaliações de um jogo específico. | Público |

### Biblioteca Pessoal (Relação Usuário-Jogo)
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `GET` | `/usuarioJogo/visualizarBiblioteca` | Visualiza todos os jogos na biblioteca pessoal. | Usuário |
| `PATCH` | `/usuarioJogo/{jogoId}/desejo` | Adiciona um jogo à lista de desejos. | Usuário |
| `PATCH` | `/usuarioJogo/{jogoId}/jogando` | Marca um jogo como "jogando". | Usuário |
| `PATCH` | `/usuarioJogo/{jogoId}/finalizado` | Marca um jogo como "finalizado". | Usuário |
| `DELETE` | `/usuarioJogo/{jogoId}/removerJogoBiblioteca` | Remove um jogo da biblioteca pessoal. | Usuário |

### Avaliações
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/avaliacao/salvar` | Publica uma nova avaliação para um jogo. | Usuário |
| `PUT` | `/avaliacao/editar` | Edita uma avaliação do próprio usuário. | Usuário |
| `DELETE` | `/avaliacao/apagar/{avaliacaoId}` | Exclui uma avaliação do próprio usuário. | Usuário |
| `DELETE` | `/avaliacao/apagarAvaliacaoImpropria/{avaliacaoId}` | Remove uma avaliação imprópria (após denúncia). | Administrador |

### Votos
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/voto/salvar` | Vota em uma avaliação como "útil" ou "inútil". | Usuário |
| `GET` | `/voto/{avaliacaoId}/votos` | Busca todos os votos de uma avaliação. | Usuário |

### Denúncias
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/denuncia/salvar` | Cria uma nova denúncia para uma avaliação. | Usuário |
| `GET` | `/denuncia/todas` | Busca todas as denúncias registradas. | Administrador |
| `DELETE` | `/denuncia/{denunciaId}/aceitarDenuncia` | Aceita a denúncia e remove a avaliação. | Administrador |
| `DELETE` | `/denuncia/{denunciaId}/rejeitarDenuncia` | Rejeita e remove a denúncia. | Administrador |

### Gêneros, Tags e Plataformas (Administração)
| Verbo | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/genero/salvar` | Adiciona um novo gênero ao sistema. | Administrador |
| `GET` | `/genero/todos` | Busca todos os gêneros cadastrados. | Usuário |
| `POST` | `/tag/salvar` | Adiciona uma nova tag ao sistema. | Administrador |
| `GET` | `/tag/todas` | Busca todas as tags registradas. | Usuário |
| `POST` | `/plataforma/salvar` | Adiciona uma nova plataforma ao sistema. | Administrador |
| `GET` | `/plataforma/todas` | Busca todas as plataformas registradas. | Usuário |

## ⚙️ Variáveis de Ambiente

Para executar a aplicação, você precisa configurar as seguintes variáveis de ambiente. Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```env
# Configurações do Banco de Dados PostgreSQL
BANCO_URL=jdbc:postgresql://localhost:5432/patocritico
BANCO_USER=seu_usuario
BANCO_PASSWORD=sua_senha

# Chave secreta para geração do JWT (use uma string longa e segura)
SECRET_KEY=sua_chave_secreta_aqui

# Duração do token JWT em horas
TEMPO_DURACAO_HORAS=48

# Configurações do Servidor de E-mail (Ex: Gmail)
MAILUSERNAME=seu_email@gmail.com
# Para o Gmail, use uma "Senha de App" gerada em sua conta Google
SERVICE_EMAIL_KEY=sua_senha_de_app_do_gmail

# Duração do link de validação de usuário em minutos
TEMPO_VALIDACAO_USUARIO=30
```

## 🚀 Como Executar o Projeto

### Pré-requisitos

- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/) e [Docker Compose](https://docs.docker.com/compose/install/) (para a opção com Docker)
- [PostgreSQL](https://www.postgresql.org/download/) (para a opção local)

### 🐳 Rodando com Docker (Recomendado)

Esta é a forma mais simples de subir a aplicação e o banco de dados.

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/XandersonSilva/PatoCriticoAPI.git
    cd PatoCriticoAPI
    ```

2.  **Crie o arquivo `.env`:**
    Crie um arquivo chamado `.env` na raiz do projeto. Para usar com o Docker Compose abaixo, você pode usar estas configurações (ajuste conforme necessário):
    ```env
    # Configurações do Banco de Dados PostgreSQL (para o Docker Compose)
    BANCO_URL=jdbc:postgresql://db:5432/patocritico
    BANCO_USER=postgres
    BANCO_PASSWORD=admin

    # Chave secreta para geração do JWT
    SECRET_KEY=minhaChaveSuperSecretaParaPatoCritico123

    # Duração do token JWT em horas
    TEMPO_DURACAO_HORAS=48

    # Configurações do Servidor de E-mail
    MAILUSERNAME=seu_email_real@gmail.com
    SERVICE_EMAIL_KEY=sua_senha_de_app_do_gmail

    # Duração do link de validação de usuário em minutos
    TEMPO_VALIDACAO_USUARIO=30
    ```

3.  **Crie um arquivo `docker-compose.yml`:**
    Na raiz do projeto, crie um arquivo `docker-compose.yml` com o seguinte conteúdo:
    ```yaml
    services:
      db:
        image: postgres:13
        container_name: patocritico-db
        environment:
          POSTGRES_USER: ${BANCO_USER}
          POSTGRES_PASSWORD: ${BANCO_PASSWORD}
          POSTGRES_DB: patocritico
        ports:
          - "5432:5432"
        volumes:
          - postgres_data:/var/lib/postgresql/data

      app:
        build: .
        container_name: patocritico-api
        depends_on:
          - db
        ports:
          - "8080:8080"
        env_file:
          - .env

    volumes:
      postgres_data:
    ```

4.  **Suba os containers:**
    Execute o comando na raiz do projeto:
    ```bash
    docker-compose up --build
    ```

A API estará disponível em `http://localhost:8080`.

### 💻 Rodando Localmente

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/XandersonSilva/PatoCriticoAPI.git
    cd PatoCriticoAPI
    ```
2.  **Configure o Banco de Dados:**
    - Certifique-se de que o PostgreSQL está instalado e rodando.
    - Crie um banco de dados chamado `patocritico`.

3.  **Configure as Variáveis de Ambiente:**
    - Crie o arquivo `.env` conforme descrito na seção [Variáveis de Ambiente](#-variáveis-de-ambiente), ajustando `BANCO_URL`, `BANCO_USER` e `BANCO_PASSWORD` para as suas credenciais locais.
    - Alternativamente, exporte as variáveis no seu terminal.

4.  **Compile o projeto com Maven:**
    ```bash
    mvn clean install
    ```
5.  **Execute a aplicação:**
    ```bash
    java -jar target/PatoCritico-0.0.1-SNAPSHOT.jar
    ```

A API estará disponível em `http://localhost:8080`.

## 📖 Documentação Interativa (Swagger)

Com a aplicação em execução, você pode acessar a documentação interativa da API, que permite visualizar e testar todos os endpoints diretamente pelo navegador.

Acesse: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 👤 Autor

**Xanderson Silva Souza**

- **E-mail:** <xandersonsilvasouza@gmail.com>
- **Contato:**
<a href="https://linkedin.com/in/xanderson-silva" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="LinkedIn de Xanderson Silva" height="30" width="40" /></a>
<a href="https://instagram.com/x.s.s____" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/instagram.svg" alt="Instagram de Xanderson Silva" height="30" width="40" /></a>
