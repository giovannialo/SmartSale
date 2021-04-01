# Smart Sale
Sistema desktop para registro de vendas, controle de estoque, clientes, produtos e fornecedores.

### Tecnologias e programas utilizados
* Java JDK 15;
* IDE IntelliJ da JetBrains;
* JavaFX no front-end;
* Banco de dados MySQL/MariaDB.

### Objetivo
Este projeto foi desenvolvido para a nota final da disciplina de Lógica em Linguagem de Programação do Instituto Federal de Alagoas - IFAL.

### Requisitos obrigatórios
* Ter um servidor de banco de dados MySQL/MariaDB rodando;
* Java JDK 15;
* Importação do JavaFX;
* Importação do MariaDB/MySQL.

### Importante
Os passos mencionados aqui foram desenvolvidos utilizando a IDE IntelliJ. Caso você utilize outra IDE, talvez seja necessário implementar mais alguns passos.

## Como rodar 💻
1. Levante um servidor de banco de dados MySQL/MariaDB;
2. Abra a pasta do projeto utilizando a IDE IntelliJ;
3. Clique no menu principal _File > Project Structure..._, uma janela irá abrir. <img src="https://github.com/giovannialo/SmartSale/blob/6a833c0166307958599cb2905c92d6e09f7e63ef/steps/1.jpg" alt="" style="max-width:100%;">

4. Na sidebar da direita, na categoria _Project Settings_, clique em _Libraries_. <img src="https://github.com/giovannialo/SmartSale/blob/6a833c0166307958599cb2905c92d6e09f7e63ef/steps/2.jpg" alt="" style="max-width:100%;">

5. Se as bibliotecas do JavaFX e MariaDB não estiverem implementadas, clique no sinal de _(+) > Java_. Se ambas estiverem implementadas, pode ir para o último passo (10). <img src="https://github.com/giovannialo/SmartSale/blob/4c56bfea9b0abe7f74334d478f8d596c0cdb204c/steps/3.jpg" alt="" style="max-width:100%;">

6. Continuando... Agora navegue até o seguinte caminho _pastaDoProjeto/java-libs/javafx-sdk-15.0.1/lib_. Deixe a pasta _lib_ selecionada e clique em _OK_. <img src="https://github.com/giovannialo/SmartSale/blob/6a833c0166307958599cb2905c92d6e09f7e63ef/steps/5.jpg" alt="" style="max-width:100%;">

7. Uma janela para confirmação de escolha do módulo irá abrir, clique em _OK_ para confirmar. <img src="https://github.com/giovannialo/SmartSale/blob/6a833c0166307958599cb2905c92d6e09f7e63ef/steps/6.jpg" alt="" style="max-width:100%;">

8. Execute o mesmo processo para adicionar a biblioteca do banco de dados MariaDB que, também, está na pasta java-libs.
9. Após isto, na janela _Project Structure_, clique no botão _APPLY_ e depois em _OK_.
10. PRONTO!!! Agora estamos prontos para rodar a aplicação. Navegue até a pasta _src_, clique com o botão direito do mouse no arquivo Launcher e execute o método _main()_.<img src="https://github.com/giovannialo/SmartSale/blob/6a833c0166307958599cb2905c92d6e09f7e63ef/steps/9.jpg" alt="" style="max-width:100%;">

11. A aplicação irá verificar a existência de um banco de dados chamado _smartsale_. Se ele não existir, o programa irá abrir uma janela solicitando a criação do mesmo. Clique no botão _Criar agora_ para que ele crie o banco de dados e todas as tabelas necessárias para o perfeito funcionamento do sistema. <br /><img src="https://github.com/giovannialo/SmartSale/blob/cc34408501214c9121eab35802a215b30ad4bfe8/steps/10.jpg" alt="" style="max-width:100%;">

12. YEAH!!!!!! Finalmente chegamos ao fim! Agora é só logar e usar o sistema.

<img src="https://github.com/giovannialo/SmartSale/blob/4bbcd5a949d2b9e8614ebf7c4465cd396e8514b3/steps/11.jpg" alt="" style="max-width:100%;">

_ENJOY_ 😙

## Alunos participantes
- YO, Giovanni Oliveira 😃
- Ruan Ramizez
- Pedro José
