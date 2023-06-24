# projeto_mc322
Participantes
  <table>
      <thead>
          <tr>
              <th>Nome</th>
              <th>RA</th>
          </tr>
      </thead>
      <tbody>
          <tr>
              <td>Eliel Oliveira da Silva</td>
              <td>221437</td>
          </tr>
          <tr>
              <td>Luiz Guilherme Nascimento</td>
              <td>789012</td>
          </tr>
          <tr>
              <td>Victor Wu</td>
              <td>345678</td>
          </tr>
      </tbody>
  </table>

  
# Frameworks e funcionamento geral do sistema
O sistema foi dividido em duas partes: o backend (na pasta api), contendo as regras de negócio da aplicação, bem como o gerenciamento dos dados em
arquivo, feito com o framework <b>Spring Boot</b> em java, e o frontend (pasta frontend :D), contendo a contrução da parte gráfica utilizando o framework web <b>Angular</b>, em <b>Typescript</b>.

Basicamente, o backend consiste em uma <b>API Rest</b>, que permite a criação de rotas especificias para obter e enviar dados, enquanto que essas
rotas são acessadas pelo frontend através de requisições <b>HTTP</b> (get, post, delete, put,...)

![diagrama-funcionamento-api](https://github.com/LuizGuilhermeNascimento/projeto_mc322/assets/52840354/c06a8972-f3df-457f-b593-5a86d56b367a)

 
 
># O Projeto
O projeto consiste em uma aplicação de uma academia, que permite um que um professor acompanhe o desenvolvimento do aluno. O professor receita um treino ao aluno, e quando a meta for atingida, o professor passa um novo treino.

Segue o diagrama UML do projeto:
....
