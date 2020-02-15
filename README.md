# Desafio JAVA

### Desenvolvedor: Juliano Freitas

## Notas sobre o desafio:

Algumas notas sobre a interpreta�ao do desafio.


- A aplica��o desenvolvida monitora o user.home do sistema operacional. Por exemplo no Windows o diret�rio pode ser **c:\Usuarios\<nome do usuario**

```
Diret�rio padr�o de entrada de dados: 
 %user.home%/data/in
```

- Faz an�lise dos dados somente dos arquivos com **extens�o .dat.** N�o interrompe a execu��o apenas ignora arquivos com extens�o n�o permitida.

- Executar a aplica��o e ap�s copiar o lote de arquivos para o diret�rio no entrada.
- O resultado da an�lise dos dados do lote de arquivos � gerado em um diret�rio de sa�da. O relat�rio possui sempre os dados acumulados dos lotes de arquivos inseridos na aplica��o ap�s a sua execu��o.

```
Relat�rio de sa�da de dados: 
 %user.home%/data/out/relatorio.done.dat
```
- Os dados do relat�rio s�o cumulativos, demonstram os n�meros totais durante toda a execu��o da aplica��o.

## Bugs conhecidos na aplica��o

- **[INTERMITENTE]** Em algumas oportunidades os arquivos monitorados pelos eventos **WatchKey** na classe **Aplicacao.java** ficam presos no processo de monitoramento do diret�rio e quando s�o percorridos para leitura dos dados geram a exception abaixo.

```
java.nio.file.FileSystemException: <caminho do arquivo>: O arquivo j� est� sendo usado por outro processo.
```

## Bugs conhecidos nos Unit Tests

- Na classe **Arquivo.java** os arquivos s�o aberto para leitura com padr�o de ENCODE UTF-8. Nos teste realizados na aplica��o n�o geram nenhum tipo de erro. Ao implementar os teste unit�rios automatizados foi percebido que com esta condifica��o os testes estavam levantando a seguinte exception:

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

- Nesta vers�o os testes unit�rios **ArquivoTest** e **ProcessoArquivoTest** est�o comentadas **@Ignore** at� ter o fix do problema. Para rodar estes teste basta retirar a anota��o.

```
@Ignore
public class ArquivoTest {
```

- Alguns arquivos de dados s�o utilizados para os testes unit�rios. Eles ficam no caminho **/src/test/resources** do projeto. Melhorar a forma de apontar para pegar o caminho destes arquivos.

```
... ArquivoTest.class.getResource("../../../../entrada01.dat");
```

## Abordagem para performance 
- identificar entidades das linhas e criar listas separadas;
- calcular totais dos itens de venda ao percorrer a linha;
- calcular o total da venda identificando o vendedor e acumulando os valores pelo nome;
- evitar percorrer lista em mem�ria muitas vezes;

## Outras notas:
- Cobertura de testes unit�rios est� em +-60% mesmo com dois teste ignorados conforme citado.
- JavaDoc criado foi versionado no projeto
- Foi utilizado o plugIn FindBugs para an�lise est�tica de c�digo. Nenhuma issue reportada.
