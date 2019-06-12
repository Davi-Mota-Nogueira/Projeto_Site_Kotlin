# Projeto Site Kotlin

O projeto consiste na uma utilização conjunta da linguagem Kotlin, sendo utilizada no compilador de JavaScript. Utilizando HTML, será desenvolvida uma página web que contém um jogo. O jogo escolhido para esse projeto foi o Resta 1.

O projeto representa o projeto da disciplina `Linguagem de programação funcional` do curso de `Engenharia de Computação` ministrado na [Universidade de Pernambuco](http://www.ecomp.poli.br/).



## O jogo

### Breve história

​	Gottfried von Leibniz, o filósofo e matemático alemão, numa carta de 1716, exaltava as virtudes de um passatempo que tem atravessado os séculos e, ainda hoje, continua despertando o interesse, tanto do 
jogador ocasional, disposto a matar meia-hora agradavelmente, quanto do estudioso, mais interessado em descobrir as leis que governam suas soluções. 

​	Seu nome é Solitaire (solitário), mas é muito mais conhecido no Brasil como Resta Um. Sua origem é um quebra-cabeça à parte, repleto de peças que não se encaixam bem. Uma das histórias não confirmadas, 
porém das mais difundidas, diz que ele foi inventado por um prisioneiro encarcerado numa solitária da Bastilha, como lenitivo para seu tédio.De uma coisa temos certeza: o tabuleiro utilizado no  Solitaire é muito semelhante a outro usado em jogos mais antigos, como  por exemplo o medieval Raposa e gansos e, com toda a probabilidade,  evoluiu a partir dele.
​	Seja como for, o século XVIII já assistia à  sua extraordinária difusão pela Europa. Modernamente, distinguimos dois  tabuleiros de Solitaire: o inglês e o francês. Este último apresenta, em relação ao primeiro, quatro casas a mais. O inglês, tradicionalmente, é  trabalhado numa única peça circular de madeira, na qual se fazem 33  concavidades e um sulco ao longo da borda.

​	Neste modelo, utilizam-se bolas de gude como peças e o sulco se  destina à colocação das bolas capturadas. O modelo francês é usualmente  composto de um quadrado perfurado, onde se introduzem pinos de madeira.  Embora seja muito fácil encontrar no comércio jogos de Solitaire de variados tamanhos, aspectos e preços, bastará ao leitor o diagrama da mais 32 grãos de feijão para se iniciar nos mistérios desse passatempo,  que Leibniz recomendava como boa forma de exercitar o raciocínio. 



### Como jogar

​	O [Resta um](http://pt.wikipedia.org/wiki/Resta_um), também conhecido como solitário inglês ou Senku tem uma origem incerta. É um jogo de tabuleiro que se joga com fichas: na modalidade mais clássica, o tabuleiro tem 33 posições e 32 fichas devido a que a posição central está sem ficha. O jogo consiste em eliminar as fichas ao estilo das damas mas só com movimentos horizontais e verticais. Consegue-se resolver o jogo quando só fica uma ficha no tabuleiro na posição central (que inicialmente estava vazia ). 

​	Um movimento consiste em pegar uma peça e fazê-la "saltar" sobre outra peça, sempre na horizontal ou na vertical,  terminando em um espaço vazio. A peça que foi "saltada" é retirada do  tabuleiro. O jogo termina quando não é mais possível fazer nenhum outro  movimento. Nesta ocasião, o jogador ganha se restar apenas uma peça no  tabuleiro.

​	Nas **modalidades inversas**, inicialmente só há uma ficha no tabuleiro: com os movimentos (saltando
de uma posição vazia para chegar a outra posição vazia ) acrescentam-se  fichas até conseguir que só fique uma posição vazia  no tabuleiro.



### Regras

​	`O jogador deve colocar um feijão (uma peça) em cima de cada casa (pontos pretos), exceto a central `. As peças se movem pulando as outras na horizontal e na vertical, nunca na diagonal. Para mover-se, uma peça deve pular outra que lhe seja adjacente, caindo numa casa vazia imediatamente seguinte. **A peça que foi pulada é retirada do jogo**. Se aquela que acabou de se mover puder fazer um novo pulo, o leitor pode prosseguir movendo-a numa sucessão de pulos (isso é considerado uma única jogada). Entretanto, não é obrigatório pular todas as peças disponíveis, podendo-se interromper a
jogada após qualquer um dos pulos da sequencia. O objetivo do passatempo é deixar uma única peça sobre o tabuleiro.



### Algoritmo

​	Pseudocódigo representando a solução através do algoritmo *backtracking* mas, pode ser utilizado para uma generalizar a solução do problema.

`Such a puzzle is amenable to backtracking to find a solution. The algorithm is quite straight-forward:`

With *n* pegs,

- if there is only *n* = 1 peg left,
  - if it is in the center, return indicating we found a solution, 		
  - otherwise, we did not;
- otherwise, for each possible jump, 	
  - modify the board to account for the jump and call the backtracking algorithm recursively, where
    - if the algorithm indicates success, record the jump and return indicating we found a solution,
    - otherwise, reset the board to the state it was in prior to making the jump; and
- the loop will only finish if none of the possible jumps led to a solution, so return indicating we did not find one.

This algorithm is implemented in the source directory for both the English and European boards. The solution that is found is:

1. Jump from (3,5) to (3,3),
2. from (3,2) to (3,4),
3. from (3,0) to (3,2),
4. from (5,3) to (3,3),
5. from (3,3) to (3,1),
6. from (5,2) to (3,2),
7. from (4,0) to (4,2),
8. from (2,1) to (4,1),
9. from (2,3) to (2,1),
10. from (2,0) to (2,2),
11. from (2,5) to (2,3),
12. from (4,4) to (2,4),
13. from (2,3) to (2,5),
14. from (0,4) to (2,4),
15. from (0,2) to (0,4),
16. from (4,6) to (4,4),
17. from (2,6) to (4,6),
18. from (3,2) to (5,2),
19. from (1,2) to (3,2),
20. from (6,2) to (4,2),
21. from (3,2) to (5,2),
22. from (6,4) to (6,2),
23. from (6,2) to (4,2),
24. from (4,1) to (4,3),
25. from (4,3) to (4,5),
26. from (4,6) to (4,4),
27. from (5,4) to (3,4),
28. from (3,4) to (1,4),
29. from (0,4) to (2,4),
30. from (2,5) to (2,3), and
31. finally from (1,3) to (3,3). 



### Programação

Utilizando conjuntamente HTML, CSS e Kotlin será necessário desenvolver uma aplicação que rodando no servidor local (*localhost*). Não será utilizado o conceito de `servers`, por não ser conteúdo programático da disciplina.



#### Roadmap

* Projeto Kotlin.js com HTML no localhost, sem aplicação de server;
* Desenvolvimento do escopo do jogo Resta 1;
* Funções no `Kotlin File`, enquanto design e esqueleto do site no HTML/CSS;



## Referências

* [Solitari](http://peg-solitaire.sourceforge.net/)
* [Super Interessante](https://super.abril.com.br/comportamento/jogo-resta-um/)
* [Solution by backtracking](https://ece.uwaterloo.ca/~dwharder/aads/Algorithms/Backtracking/Peg_solitaire/)
* [Trying to solve Peg Solitaire by a simple, depth-first search](http://www.dcc.fc.up.pt/~acm/tpeg.pdf)

