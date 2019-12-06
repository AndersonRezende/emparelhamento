# Emparelhamento
Algoritmo para resolver o problema do emparelhamento - GRAFOS

### Explicação
O software realiza a leitura de um arquivo contendo uma matriz de dimensões [L,C] e converte em listas que armazenam os vértices de acordo com o seu grau (quantidade de conexões com vértices adjacentes). Ao final, é realizado o emparelhamento dos vértices selecionando preferêncialmente os vértices de menor grau. A cada emparelhamento, a lista de vértices é atualizada e o processo é feito até limpar a lista.

### Demonstração
Alguns arquivos (.txt) de exemplos podem ser encontrados na pasta `Exemplos`. 
Sinta-se a vontade para criar/modificar os arquivos desde que: 
1. Siga o padrão contendo [L,C] de dimensão.
2. As colunas sejam separadas por espaço e as linhas por paragráfos/quebra de linhas.

### Instalação

1. Faça o clone do projeto utilizando: `$ git clone https://github.com/AndersonRezende/emparelhamento.git`

### Testando
1. Abra o terminal na pasta do projeto.
2. Compile o arquivo Programa.java usando o seguinte comando: `$ javac Programa.java`
3. Rode a aplicação usando o seguinte comando: `$ java Programa`

### Notas
1. O algoritmo é um pouco antigo, foi feito na época da faculdade, então está confuso/mal otimizado. Novas versões com correções/melhorias serão lançadas.
2. Sinta-se a vontade para entrar em contato ou sugerir correções (MR's).
