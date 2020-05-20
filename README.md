# Garbage Eater
Jogo no estilo "snake game" desenvolvido por [@LucasGabrielB](https://github.com/LucasGabrielB/) e [@JMTresso](https://github.com/JMTresso), 
para incentivar a coleta seletiva de resíduos e a reciclagem.<br />
Jogo de código aberto, sobre a licença [MIT](https://github.com/LucasGabrielB/GarbageEater/blob/master/LICENSE).

![tela do menu](https://user-images.githubusercontent.com/48874910/82405159-72ea6a00-9a39-11ea-95cd-173e58f00ba6.png)

## Sumário
- [Regras e funcionamento do jogo](#Regras-e-funcionamento-do-jogo) 
    - [Formas de perder no jogo](#Formas-de-perder-no-jogo)
-  [Estrutura do jogo](#Estrutura-do-jogo)
    - [Pacote view](#Pacote-view)
    - [Pacote soundEffects](#Pacote-soundEffects)
    - [Pacote entities](#Pacote-entities)
    - [Pacote database](#Pacote-database)
        - [Banco de Dados e Classificação dos Jogadores](#Banco-de-Dados-e-Classificação-dos-Jogadores)

<div id='Regras-e-funcionamento-do-jogo'/>

## Regras e funcionamento do jogo
O jogo utiliza do sistema de cores da coleta seletiva e reciclagem para desafiar a coordenação e o raciocínio rápido do jogador.
Foram escolhidos 4 tipos de descarte para compor o jogo, plástico (vermelho), papel (azul), vidro (verde) e metal (amarelo),
onde ao serem coletados de forma correta geram pontos ao jogador, assim como penalidades ao não coletar da devida forma.<br />
A mecânica do jogo foi baseada no funcionamento do conhecido “Snake Game” também conhecido como “Jogo da Cobrinha” onde um 
personagem se movimenta em uma área quadriculada coletando pontos e aumentando seu tamanho gradativamente.<br />
O nome do jogador é inserido na caixa de entrada localizada na parte inferior esquerda da tela inicial, o jogo 
não pode ser iniciado até o jogador não preencher essa caixa com algum nome.

<div id='Formas-de-perder-no-jogo'/>

### Formas de perder no jogo

Existem 3 formas de perder no jogo, a primeira é atingir a borda do cenário, a segunda forma está na colisão do personagem 
com o seu próprio corpo, por último ao iniciar o jogo é perceptível a existência de 3 corações na parte
superior direita, cada coração representa uma chance que o personagem
tem para coletar o descarte corretamente, um coração é retirado quando se erra a cor
requisitada para recolher o descarte, quando não se tem mais corações e um descarte
é coletado com a cor incorreta, é fim de jogo.<br/>
Esse detalhe em especifico serve para abstrair a importância da coleta
seletiva feita de forma correta, pois, a fazer de forma incorreta apenas prejudica o
desempenho da reciclagem na redução dos impactos ambientais causados pelo descarte incorreto desse material.
Sempre que o jogador perde no jogo, sua pontuação é salva no banco de
dados, caso o jogador já estiver cadastrado no banco de dados sua nova pontuação
só será salva se for maior que a pontuação anterior, que está salva no banco de dados.

<div id='Estrutura-do-jogo'/>

## Estrutura do jogo

A estrutura do programa foi dividida em alguns pacotes para melhor
organização sendo eles, view, soundEffects, entities e database, aonde cada um deles
cuida respectivamente, das interfaces gráficas e a interação com o usuário, os efeitos
sonoros, as entidades desenvolvidas para a criação do jogo, e a conexão com o banco
de dados.<br/>
Mais detalhes sobre o conteúdo detalhado de cada pacote abaixo.

<div id='Pacote-view'/>

### Pacote view

O pacote view, contém as classes referentes as interfaces gráficas
do programa, sendo elas, MenuScreen, HelpScreen, WarningScreen e GameScreen. 

![pacote view](https://user-images.githubusercontent.com/48874910/82405911-8eef0b00-9a3b-11ea-8b30-ae6f47be6cdc.png)

A classe MenuScreen é a primeira classe a ser executada quando o programa
é iniciado, nela contém todas as interações iniciais com o usuário, como
cadastramento de um apelido, pelo qual o jogador será referenciado, visualização do
sistema de classificação dos jogadores (rank), botões de navegação que levam o
jogador para a tela de ajuda (HelpScreen) aonde contém as informações necessárias
para entender o funcionamento do jogo, e também que levam o jogador para a tela do
jogo, aonde ele acontece de fato (GameScreen). 

<div id='Pacote-soundEffects'/>

### Pacote soundEffects

O pacote soundEffects contém a classe que cuida dos efeitos sonoros do programa, SoundEffect.
Essa classe manipula os arquivos de áudio existentes dentro de uma pasta
chamada sounds existente dentro do pacote.

![pacote soundEffects](https://user-images.githubusercontent.com/48874910/82406051-09b82600-9a3c-11ea-9ba8-bcd22dda7d4a.png)

<div id='Pacote-entities'/>

### Pacote entities

![pacote entities](https://user-images.githubusercontent.com/48874910/82406277-9662e400-9a3c-11ea-8b15-a5a89abb98e2.png)

Ilustrado na imagem acima, é possível analisar algumas relações como heranças
e dependências. A classe SquareInTheScreen representa um objeto (comumente um
quadrado) na tela do jogo com uma coordenada e o tamanho desse objeto. Essa
classe representa a forma mais genérica de classes do sistema.<br/>
A classe SnakeBodyPart, que representam as partes do corpo do personagem e a classe
Garbage que representa o lixo reciclável, que será coletado por ele. Ambas essas
classes são subclasses da classe SquareInTheScreen. Essas subclasses também
tem o atributo color do tipo RecycleBinColor, que é um enumerador que contém os
tipos possíveis de corres das lixeiras, RED (vermelho), GREEN (verde), YELLOW
(amarelo) e BLUE (azul).<br/>
A classe Snake representa o personagem que irá se movimentar na tela de
jogo, tendo como atributo uma lista de SnakeBodyPart, que representaram seu corpo
e um atributo color do tipo RecycleBinColor que representara a cor atual de todas 
essas partes do seu corpo (RecycleBinColor). Ela também contém os métodos
addBodyPart para adicionar uma nova SnakeBodyPart ao corpo do personagem e
também o método getLength que retorna um número inteiro, que representa o número
de partes do corpo do personagem.<br/>
A classe Player representa um jogador, tendo como atributos nickname que
representa o nome do personagem e o atributo score que representa a pontuação
dele. <br/>
A classe HealthBar representa a barra de vida do personagem, por conta
disso, está diretamente ligada a classe Snake, ela tem o atributo healthBar que é uma
lista de SquareInTheScreen, que representam os corações do personagem, e o
atributo haveLife do tipo booleano, que serve para verificar se o player ainda tem
corações de vida. Além disso ela também cotem o método takeDamage, que remove
um coração da barra de vida.

<div id='Pacote-database'/>

### Pacote database

O pacote database, contém a classe que faz a conexão com o
banco de dados DatabaseConnection, essa classe contém os métodos estáticos
responsáveis pela conexão e comunicação com o banco de dados, localizado dentro
de uma pasta chamada db, nesse mesmo pacote. <br/>
O método postPlayer serve para cadastrar um jogador no banco de dados, ele
primeiro verifica se o jogador está cadastrado no banco de dados, se não estiver ele
o adiciona juntamente com sua pontuação, se o jogador já estiver cadastrado no
banco de dados ele verifica se a pontuação guardada no banco de dados e menor 
que a pontuação atual, se for, ele atualiza a pontuação que está guardada no banco
de dados para a nova pontuação.<br/>
O método getTop10 serve para retornar uma lista dos dez melhores jogadores
cadastrados no banco de dados, ordenada pela pontuação dos jogadores em ordem
decrescente.<br/>
E os métodos privados, playerExistInDatbase, getPlayerScore e
getConnection, que servem como métodos auxiliares para os métodos públicos
postPlayer e getTop10.

![pacote database](https://user-images.githubusercontent.com/48874910/82406207-66b3dc00-9a3c-11ea-8c8f-2d9d5222f5e0.png)

<div id='Banco-de-Dados-e-Classificação-dos-Jogadores'/>

#### Banco de Dados e Classificação dos Jogadores

Foi desenvolvido um sistema de ranqueamento, que classifica os jogadores a partir de sua pontuação.<br/>
Para o desenvolvimento do sistema de classificação são necessárias duas
informações principais, sendo elas o nome e a pontuação do jogador durante a
rodada. Tais dados são salvos sempre que o jogador perde e enviados para o banco
de dados que os armazena em uma tabela, criada com o código apresentado abaixo.<br/>


    CREATE TABLE player(
       nickname VARCHAR(15) PRIMARY KEY,
       score INT DEFAULT 0
      );


Esses dados são exibidos na tela inicial do jogo em formato de lista, classificando os jogadores de 
forma decrescente com base na pontuação.
