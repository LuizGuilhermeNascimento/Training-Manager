# How to Run the Application:

Used versions:

  <table>
      <thead>
          <tr>
              <th>Dependency</th>
              <th>Version</th>
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

### Running:

> in project_mc322/api folder
```
mvn spring-boot:run
```

If all goes well, the api should run on localhost:8080. <i>(if this command doesn't work, you can run it through the IDE's vscode/intellij by clicking on the button)</i>


> in project_mc322/frontend folder
```
npm i
```
```
npm start
```
or
```
npx ng serves
```

If all goes well, the site should run on localhost:4200

We use Intellij to work in the api folder, and Visual Studio Code to work in the frontend folder. However, Visual Studio Code can be used for both.
  
# Frameworks and general functioning of the system
The system was divided into two parts: the backend (in the api folder), containing the application's business rules, as well as data management in a file, done with the <b>Spring Boot</b> framework in java, and the frontend (folder frontend :D), containing the construction of the graphic part using the <b>Angular</b> web framework, in <b>Typescript</b>.

Basically, the backend consists of a <b>Rest API</b>, which allows the creation of specific routes to obtain and send data, while these routes are accessed by the frontend through <b>HTTP</b> requests ( get, post, delete, put,...)

![diagrama-funcionamento-api](https://github.com/LuizGuilhermeNascimento/projeto_mc322/assets/52840354/5b44c84e-6f7b-498a-91cc-943636ac9155)

 
 
>> The Project
The project consists of an application of a gym, which allows a teacher to follow the student's development. The teacher prescribes training for the student, and when the goal is reached, the teacher changes the student's training.

Here is the UML diagram of the project:
![UML_final_322 drawio](https://github.com/LuizGuilhermeNascimento/projeto_mc322/assets/52840354/0f2cad45-95db-4d02-b177-d678ac64e46d)

# Files

Data is saved in .json files. Basically, the data is saved in the folder <b>data/<Object Class Name\>/<Object id\>.json</b>, each file being for a single object.
By uml diagram, some classes implement JsonSerializable interface
```java
public interface JsonSerializable {
  UUID getId();
  JsonObject writeJson();
}
```
As the file name takes the object id, serializable objects need to have a getId.

Similarly, each class writes data to files in a different way, which is why the writeJson method exists.

Thus, the method responsible for writing the file receives a JsonSerializable, calling both methods


# Data Manipulation
![UML_final_322-Página-2 drawio](https://github.com/LuizGuilhermeNascimento/projeto_mc322/assets/52840354/e9dfd50b-b39d-49bc-b590-af69537aa5d4)

Data is saved and accessed from 'Repository' classes

The Repositories of the Student, Teacher and Monitoring classes inherit an abstract RepositoryBase class, containing generic implementations

These repositories are called in service classes, which perform operations that, in turn, are called in controller classes, which define the routes that will be called on the site.
