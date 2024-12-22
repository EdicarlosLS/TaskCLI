# TaskCLI

TaskCLI é uma ferramenta de linha de comando para gerenciar tarefas, escrita em Java. Ela permite que os usuários criem, listem, atualizem e excluam tarefas facilmente.

## Instalação

Para usar o TaskCLI, clone o repositório, compile e empacote os arquivos.

```cli
git clone
https://github.com/EdicarlosLS/TaskCLI.git
cd TaskCLI
```

Localize os arquivos `.java`  e liste-os em um arquivo `dotjava.txt`.

```cli
find . -name *.java > dotjava.txt
```

Compile os arquivos `.java` listados para a pasta de destino `./out` .

```cli
javac -d ./out @dotjava.txt
```

Empacote os arquivos `.class` compilados em um arquivo `.jar`   definindo a classe `com.vulpslab.taskcli.Main` como classe principal.

```cli
jar cfe taskcli.jar com.vulpslab.taskcli.Main -C ./out .
```
Com o arquivo  `.jar` é possível executar a ferramenta via linha de comando em qualquer sistema operacional que tenha o java (JRE) instalado. Basta chama-lo da seguinte forma:

```cli
java -jar taskcli.jar <ação>
```

Caso esteja usando Linux e querira tornar a ferramenta um executável, siga os próximos passos:

```cli
echo '#!usr/bin/java -jar' > taskcli
cat taskcli.jar >> taskcli
chmod +x taskcli
```

## Uso

### Listar todas as tarefas

Para listar todas as tarefas, você pode usar o seguinte comando.

```cli
./taskcli list
```
Este comando retornará todas as tarefas armazenadas. Se você quiser filtrar as tarefas por status, você pode usar um dos seguintes filtros opcionais: todo, in-progress, done.

```cli
./taskcli list <filtro>
```

#### Exemplo:

- Para listar todas as tarefas pendentes (todo):
```cli
./taskcli list todo
```

- Para listar todas as tarefas em andamento (in-progress):
```cli
./taskcli list in-progress
```

- Para listar todas as tarefas concluídas (done):
```cli
./taskcli list done
```

### Criar uma tarefa
Para criar uma nova tarefa, use o comando add seguido pela descrição da tarefa entre aspas.

```cli
./taskcli add <descrição>
```

### Atualizar uma tarefa
Para atualizar a descrição de uma tarefa existente, use o comando update seguido pelo ID da tarefa e a nova descrição.

```cli
./taskcli update <id> <nova descrição>
```
### Atualizar status da tarefa
Para alterar o status de uma tarefa para "em andamento", use o seguinte comando:

```cli
./taskcli mark-in-progress <id>
```

Para marcar uma tarefa como concluída, use:
```cli
./taskcli mark-done <id>
```

### Excluir uma tarefa
Para excluir uma tarefa, use o seguinte comando com o ID da tarefa:

```cli
./taskcli delete <id>
```