# Desafio JAVA

### Desenvolvedor: Juliano Freitas

## Notas sobre o desafio:

Algumas notas sobre a interpretação do desafio.


- A aplicação desenvolvida monitora o user.home do sistema operacional. Por exemplo, no Windows o diretório pode ser **c:\Usuarios\<nome do usuario**

```
Diretório padrão de entrada de dados: 
 %user.home%/data/in
```

- Faz análise dos dados somente dos arquivos com **extensão .dat.** Não interrompe a execução apenas ignora arquivos com extensão não permitida.

- Executar a aplicação e após copiar o lote de arquivos para o diretório no entrada.
- O resultado da análise dos dados do lote de arquivos é gerado em um diretório de saída. O relatório possui sempre os dados acumulados dos lotes de arquivos inseridos na aplicação após a sua execução.

```
Relatório de saída de dados: 
 %user.home%/data/out/relatorio.done.dat
```
- Os dados do relatório são cumulativos, demonstram os números totais durante toda a execução da aplicação.

## Bugs conhecidos na aplicação

- **[INTERMITENTE]** Em algumas oportunidades os arquivos monitorados pelos eventos **WatchKey** na classe **Aplicacao.java** ficam presos no processo de monitoramento do diretório e quando são percorridos para leitura dos dados geram a exception abaixo.

```
java.nio.file.FileSystemException: <caminho do arquivo>: O arquivo já está sendo usado por outro processo.
```

## Bugs conhecidos nos Unit Tests

- **[CORRIGIDO]** Na classe **Arquivo.java** os arquivos são aberto para leitura com padrão de ENCODE UTF-8. Nos testes realizados na aplicação não geram nenhum tipo de erro. Ao implementar os testes unitários automatizados foi percebido que com esta condificação os testes estavam levantando a seguinte exception:

```
java.nio.charset.MalformedInputException: Input length = 1
	at java.nio.charset.CoderResult.throwException(CoderResult.java:281)
	at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:339)
	at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
	at java.io.InputStreamReader.read(InputStreamReader.java:184)
	at java.io.BufferedReader.fill(BufferedReader.java:161)
	at java.io.BufferedReader.readLine(BufferedReader.java:324)
	at java.io.BufferedReader.readLine(BufferedReader.java:389)
	at java.nio.file.Files.readAllLines(Files.java:3205)
	at com.sinqia.desafio.arquivo.Arquivo.percorreArquivoEntrada(Arquivo.java:44)
```

- Alguns arquivos de dados são utilizados para os testes unitários. Eles ficam no caminho **/src/test/resources** do projeto. Melhorar a forma de apontar para pegar o caminho destes arquivos.

```
... ArquivoTest.class.getResource("../../../../entrada01.dat");
```

## Abordagem para performance 
- identificar entidades das linhas e criar listas separadas;
- calcular totais dos itens de venda ao percorrer a linha;
- calcular o total da venda identificando o vendedor e acumulando os valores pelo nome;
- evitar percorrer listas em memória muitas vezes;

## Outras notas:
- Cobertura de testes unitários está em +-70%
- JavaDoc criado foi versionado no projeto
- Foi utilizado o plugIn FindBugs para análise estática de código. Nenhuma issue reportada.

