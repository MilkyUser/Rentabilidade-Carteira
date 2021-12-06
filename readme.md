# Rentabilidade da Carteira

Este programa calcula a rentabilidade de uma carteira de ativos para qualquer intervalo de tempo.
Você pode fazer download dos arquivos compilados [aqui](https://drive.google.com/drive/folders/10uB7rOi-QxJoiXpUD4fenr5pzXnyqUY0?usp=sharing)
e executa-los digitando o seguinte comando no terminal:

`java ./<diretório do download>/build/Main <caminho até carteira.csv> <caminho até preços.csv>`. 

É preciso, pelo menos, a versão 8 do JRE (Java Runtime Enviroment) instalada para executar este programa.

## Especificação dos arquivos de entrada

Os arquivos de entrada (que o programa usa para calcular a rentabilidade) 
não devem possuir cabeçalho e devem estar no formato csv
(o caracter separador é `;`).
###carteira.csv

Este arquivo deve seguir o seguinte formato:

`TIPO_DE_ATIVO;NOME;QUANTIDADE`

Onde:

* `TIPO_DE_ATIVO` representa o tipo de ativo (tais como Ações, DAP's e DDI's)
* `NOME` representa o nome do ativo
* `QUANTIDADE` representa quantidade do ativo na carteira (número real onde `.` é o separador decimal)

### preços.csv

Este arquivo deve seguir o seguinte formato:

`NOME;PREÇO_DE_ARBETURA;PREÇO_DE_FECHAMENTO`

Onde:

* `NOME` representa o nome do ativo
* `PREÇO_DE_ABERTURA` representa o preço de abertura do ativo (número real onde `.` é o separador decimal)
* `PREÇO_DE_FECHAMENTO` representa o preço de fechamento do ativo (número real onde `.` é o separador decimal)

## Saída

A saída do programa é impressa na saída padrão e está no mesmo formato do arquivo de entrada `carteira.csv` com a
adição da rentabilidade total na última linha.

## Compilação manual

A compilação manual pode ser realizada a partir da versão 14 do JDK (Java Development Kit).

Para compilar o programa e executar utilize os seguintes comandos:

```
mkdir build
javac <./caminho_até_os_arquivos_fonte/*.java> -d ./build
java ./build/Main```
