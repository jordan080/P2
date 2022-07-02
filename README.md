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
- Código duplicado (Duplicated Code) (classes Main e User, a partir do método getCommunity())
  - O que foi feito: Aplicando o "Extract Class", foi criado uma classe separada que contém os métodos usados por mais de uma classe.
- Construtores com código semelhante (Duplicated Code) (classe User)
  - O que foi feito:
  Aplicar o "Chain Constructors" a partir de um construtor que abrange as variáveis dos outros menores (linhas 32-38), sendo estes usando a  palavra reservada "this" para se referir ao construtor maior, porém usando "null" nas variáveis não utilizadas (linhas 22-30).
- Vários if/else alinhados (Long Method, "menu" da classe Main)
- Feature Envy (classe Main, métodos sendMessage() e addRemoveFriend())
  - O que foi feito: addRemoveFriend() foi movido para a classe User (Move Method), enquanto que sendMessage() foi dividido para as classes Utils e Feed.
- Método muito grande (Long Method, método sendMessage())
  - O que foi feito: Foi dividido para as classes Utils e Feed, a partir de uma interface chamada SendMessage. 
- Comentários (Arquivo Main.java)
  - O que foi feito: Vários comentários foram removidos, variáveis e métodos com abreviações e nomes confusos foram ajustados.
