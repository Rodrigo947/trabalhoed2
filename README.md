# Trabalho de Estrutura de Dados 2

## Execução
**1.** Baixe e instale o Java JDK 8 ou acima: 

**Windows:** https://adoptopenjdk.net/

**Linux:** `$ sudo apt install default-jre`

**2.** Clone o repositório do projeto do github:

```bash
$ git clone https://github.com/Rodrigo947/trabalhoed2
```
**3.** Faça o download do dataset no link https://drive.google.com/file/d/1akPqLZg-_Ug2SUyPWJ4bwjcWFP9tsKgL/view?usp=sharing e extraia os arquivos na pasta `data` do projeto.

**4.** Abra um terminal na pasta e execute o comando:

```bash
$ java -cp trabalhoed2.jar Main
```

## Instruções de uso
Ao executar programa o seguinte menu será mostrado:
```
TRABALHO DE ESTRUTURA DE DADOS 2
1-Cenario 1
2-Cenario 2
3-Cenario 3
4-Parte 2
5-Parte 3
6-Gerar Arquivo de Objetos
7-Gerar Arquivos de Arrays
0-Sair
Opcao:
```
Antes de processar quaisquer opções de 1 à 5 é necessário rodar as opções 6 e 7 emsequência.

### Opção 6

Responsável por fazer o pré processamento do dataset criando um arquivo de objetos onde cada objeto guarda um registro do dataset.

O arquivo gerado está em `data/datasetOBJ.txt`.

### Opção 7

A partir do arquivo `data/entrada.txt`, essa opção gera os arquivos de arrays aleatórios que serão usados por cada cenário e os guarda na pasta `data/arrays/`. Uma pasta é criada para cada tamanho de vetor e o nome de dos arquivos seguem o seguinte padrão: **tipoDeArray_tamanhoDoArray_seed.txt**

**OBS.: A geração desses arquivos podem demorar muito tempo, portanto caso queira pular a execução das opções 6 e 7, faça o download de todos os arquivos tratados e extraia na pasta data.** https://drive.google.com/file/d/1aktD1ncPi1pcvm-8jznUlVvDC4oOfZT_/view?usp=sharing

### Cenários

Os resultados de cada cenário são armazenados em `resultados`. A pasta está divida pelo nome das ordenações, então dependendo do cenário executado, as determinadas pastas serão preenchidas.

```
---Cenário 1---
Executa apenas o QuickSort Recursivo.
Pasta a ser preenchida: 
resultados/QuickSort/cenario1/Recursivo

---Cenário 2---
Executa os seguintes tipos de QuickSort: Recursivo, Mediana, Inserção.
Pastas a serem preenchidas: 
resultados/QuickSort/cenario2/Recursivo
resultados/QuickSort/cenario2/Mediana
resultados/QuickSort/cenario2/Insercao

---Cenário 3---
Executa as ordenações Heap Sort ,Insertion Sort, Merge Sort e Tree Sort
Pastas a serem preenchidas: 
resultados/HeapSort
resultados/InsertionSort
resultados/MergeSort
resultados/TreeSort
```
## Descrição das Classes 

Com o objetivo de organizar o código do trabalho, os arquivos foram dividos da seguinte forma:

| Nome da Classe | Descrição   |
| -------------- | ----------- |
| Livro          | Definição do objeto Livro com todos os seus atributos                                                                      |
| GerarArquivos  | Classe responável por gerar o arquivo ``datasetOBJ.txt`` e os arquivos de arrays                                           |
| GerarArrays    | A partir do ``datasetOBJ.txt`` gera arrays aleatórios além de ler os arquivos de arrays                                    |
| Leitura        | Responsável pelo pré processamento do dataset                                                                              |
| HeapSort       | Implementação de todas as funções necessárias para executar a ordenação do tipo Heap Sort                                  |
| InsertionSort  | Implementação de todas as funções necessárias para executar a ordenação do tipo Insertion Sort                             |
| MergeSort      | Implementação de todas as funções necessárias para executar a ordenação do tipo Merge Sort                                 |
| TreeSort       | Implementação de todas as funções necessárias para executar a ordenação do tipo Tree Sort                                  |
| QuickSort      | Implementação de todas as funções necessárias para executar os sequintes tipos de Quick Sort: Recursivo, Mediana, Inserção |

## Parte 2 e 3

Para mais informações sobre esse parte, consulte o arquivo  Relatório.pdf. 
