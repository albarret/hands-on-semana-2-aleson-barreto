# hands-on-semana-2-aleson-barreto
## Atividade da semana 2 da trilha Eldorado Tech Training

### Instruções de execução

Utilizando o docker-compose ainda não consegui fazer funcionar mesmo replicando os arquivos
Dockerfile e local/docker-compose.yaml.

Nesse caso, é possível testar apenas subindo o container do MongoDB a aplicação localmente via IntelliJ.
Deixei incluso no projeto um arquivo .run para facilitar a subida do sistema pelo IntelliJ.
Nesse arquivo, o profile utilizado é dev.

### A API
A API faz o que é descrito na atividade. É possível criar e editar informações de cliente e medidas.
Ao se criar ou editar uma medição, automaticamente o sistema edita o IMC derivado dessa medição.

Além disso, ela também gera um relatório que aponta os dados de todas as medições feitas para o cliente
e também as variações de classificação do IMC em um intervalo de tempo.

Uma versão inicial da documentação da API está em `local/imc-menager.postman_collection.json` e pode ser importada para o Postman
