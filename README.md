# Como Executar a Aplicação:

Versões utilizadas:

  <table>
      <thead>
          <tr>
              <th>Dependencia</th>
              <th>Versão</th>
          </tr>
      </thead>
      <tbody>
          <tr>
              <td>Java</td>
              <td>17.0.6</td>
          </tr>
          <tr>
              <td>Maven</td>
              <td>3.9.2</td>
          </tr>
          <tr>
              <td>Node</td>
              <td>18.13.0</td>
          </tr>
          <tr>
              <td>npm</td>
              <td>9.7.1</td>
          </tr>
      </tbody>
  </table>

### Executando:

> na pasta projeto_mc322/api
```
mvn spring-boot:run
```

Se tudo der certo, a api deve rodar no localhost:8080. <i>(caso esse comando não funcione, é possível executar pelas IDE's vscode/intellij clicando no botão)</i>


> na pasta projeto_mc322/frontend
```
npm i
```
```
npm start
```
ou
```
npx ng serve
```

Se tudo der certo, o site deve rodar no localhost:4200

Utilizamos o Intellij para trabalhar na pasta api, e o Visual Studio Code para trabalhar na pasta frontend. No entanto, o Visual Studio Code pode ser utilizado para ambas.
  
# Frameworks e funcionamento geral do sistema
O sistema foi dividido em duas partes: o backend (na pasta api), contendo as regras de negócio da aplicação, bem como o gerenciamento dos dados em arquivo, feito com o framework <b>Spring Boot</b> em java, e o frontend (pasta frontend :D), contendo a contrução da parte gráfica utilizando o framework web <b>Angular</b>, em <b>Typescript</b>.

Basicamente, o backend consiste em uma <b>API Rest</b>, que permite a criação de rotas especificias para obter e enviar dados, enquanto que essas rotas são acessadas pelo frontend através de requisições <b>HTTP</b> (get, post, delete, put,...)

![diagrama-funcionamento-api](https://github.com/LuizGuilhermeNascimento/projeto_mc322/assets/52840354/5b44c84e-6f7b-498a-91cc-943636ac9155)

 
 
># O Projeto
O projeto consiste em uma aplicação de uma academia, que permite um que um professor acompanhe o desenvolvimento do aluno. O professor receita um treino ao aluno, e quando a meta for atingida, o professor troca o treino do aluno.

Segue o diagrama UML do projeto:
![UML_final_322 drawio](https://github.com/LuizGuilhermeNascimento/projeto_mc322/assets/52840354/0f2cad45-95db-4d02-b177-d678ac64e46d)

# Arquivos

Os dados são salvos em arquivos .json. Basicamente, os dados ficam salvos na pasta <b>dados/<Nome da Classe do Objeto\>/<id do Objeto\>.json</b>, sendo cada arquivo para um único objeto.
Pelo diagrama uml, algumas classes implementam a interface JsonSerializable
```java
public interface JsonSerializable {
  UUID getId();
  JsonObject writeJson();
}
```
Como o nome do arquivo recebe o id do objeto, os objetos serializaveis precisam ter um getId. 

Similarmente, cada classe escreve os dados em arquivos de uma forma diferente, por isso o método writeJson existe.

Assim, o método responsável por gravar o arquivo recebe um JsonSerializable, chamando os dois métodos


# Manipulação dos Dados
![UML_final_322-Página-2 drawio](https://github.com/LuizGuilhermeNascimento/projeto_mc322/assets/52840354/e9dfd50b-b39d-49bc-b590-af69537aa5d4)

Os dados são salvos e acessados a partir de classes 'Repository'

Os Repositories das classes Aluno, Professor e Acompanhamento herdam uma classe abstrata RepositoryBase, contendo implementações genéricas

Esses repositories são chamados em classes de serviço, que realizam operações que, por sua vez, são chamadas em classes controllers, que definem as rotas que serão chamadas no site.
