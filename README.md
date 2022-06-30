# Executando o sistema:
- Compile o arquivo "Main.java"
- Execute o arquivo "Main" gerado

# Funcionalidades:

1) Criação de Conta (Implementado)
2) Criação/Edição de Pefil (Implementado)
3) Adição de Amigos (Implementado)
4) Envio de Mensagens (Implementado)
5) Criação de Comunidades (Implementado)
6) Adição de Membros (Implementado)
7) Recuperar informações sobre um determinado usuário (Implementado)
8) Remoção da Conta (Implementado)
9) Envio de Mensagens no feed de noticias (Implementado)
10) Controle de visualização do filtro de notícias (Implementado)

# Excessões tratadas:
- Dois usuários não podem ter o mesmo apelido
- Não é possível achar/entrar em comunidades que não existem
- Não é possível entrar em contas que não existem
- Não é possível acessar informações do perfil que não existem
- Não é possível adicionar uma data de nascimento com formato inválido
- Não é possível acessar uma opção do menu principal que não existe

# Code Smells:
- Código duplicado (Duplicated Code) (Arquivos Main.java (197-210) e User.java (51-62))
- Construtores com código semelhante (Duplicated Code) (Arquivo User.java, linhas 22-39)
  - O que foi feito:
  Aplicar o pattern "Chain Constructors" a partir de um construtor que abrange as variáveis dos outros menores (linhas 32-38), sendo estes usando a  palavra reservada "this" para se referir ao construtor maior, porém usando "null" nas variáveis não utilizadas (linhas 22-30).
- Vários if/else alinhados (Long Method) (Arquivo Main.java, linhas 13-166)
- Uma classe que tenta fazer muitas coisas (Large Class) (classe User)
- Comentários (Arquivo Main.java)
  - O que foi feito: Vários comentários foram removidos, variáveis e funções com abreviações e nomes confusos foram ajustados.
