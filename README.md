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
- Código duplicado (Duplicated Code)
  - Como era anteriomente: As classes Main e User possuiam o mesmo método chamado getCommunity(), isso causava que o código do mesmo método estava duplicado em duas classes. 
  
  - O que foi feito: Aplicando o "Extract Class", foi criado uma classe separada (Utils) que contém os métodos usados por mais de uma classe.
  
- Construtores com código semelhante (Duplicated Code)
  - Como era anteriormente: Haviam vários construtores na classe User com as mesmas variáveis.
  
  - O que foi feito: Aplicar o "Chain Constructors" a partir de um construtor que abrange as variáveis dos outros menores (linhas 32-38), sendo estes usando a  palavra reservada "this" para se referir a própria classe, porém usando "null" nas variáveis não utilizadas (linhas 22-30).
  
- Vários if/else alinhados (Long Method)
  - Como era anteriormente: Na classe Main haviam vários if/else que serviam como checks para a opção do menu que o usuário desejava, principalmente quando ele entrava no sistema.
  
  - O que foi feito: Foi implementada a técnica "Command", a partir do seguinte esquema: Uma interface é implementada por classes que terminam em "__Command", na qual possui um método "executeCommand()". Este irá executar um método que vai de acordo com o nome da classe (ex.: A classe SeeFeedCommand possui em seu método "executeCommand()" uma chamada outro método para executar a função de mostrar o feed). Além disso, há uma classe de controle chamada "Controller" na qual integra todas as classes que implementam a interface e a opção do menu que o usuário deseja. Todas as classes estão dentro de um vetor e o número da opção do usuário será a posição vetor que contém a classe que será executada.
  
- Feature Envy (classe Main, métodos sendMessage() e addRemoveFriend())
  - Como era anteriormente: Na classe Main, existiam os métodos sendMessage() e addRemoveFriend() que não faziam muito sentido de estarem lá pois "encaixariam" melhor em outras classes.
  
  - O que foi feito: addRemoveFriend() foi movido para a classe User (técnica "Move Method"), enquanto que sendMessage() foi dividido e enviado para as classes Utils e Feed.
  
- Método muito grande (Long Method, método sendMessage())
  - Como era anteriormente: sendMessage() era um método grande demais para a sua função.
  - O que foi feito: Aplicando a técnica "Extract Method", foi dividido em três métodos chamados sendMessagetoUser(), sendMessagetoCommunity() e sendMessagetoFeed().
  
- Comentários (Arquivo Main.java)
  - Como era anteriormente: Existiam vários comentários que serviam para orientar quem estava lendo o código, sendo que o próprio código deveria se explicar por si só.
  
  - O que foi feito: Vários comentários foram removidos, inclusive, aplicando a técnica "Rename Method", variáveis e métodos com abreviações e nomes confusos foram ajustados.
