package com.datapersistence;

import javax.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


class Exemplo implements JsonSerializable{
    private UUID id = UUID.randomUUID();
    private String nome;
    private String email;
    private List<String> materias;

    public Exemplo(String nome, String email, List<String> materias) {
        this.nome = nome;
        this.email = email;
        this.materias = materias;
    }

    @Override
    public JsonObject writeJson() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        getMaterias().forEach(jsonArrayBuilder::add);
        return Json.createObjectBuilder()
                .add("nome", getNome())
                .add("id", getId().toString())
                .add("email", getEmail())
                .add("materias", jsonArrayBuilder).build();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }
}
public class ReadJson {
    public static void main(String[] args) {
        try{
//            JsonArray j = readFile("data/eliel.json");
            List<String> materias = new ArrayList<>();
            materias.add("mc358");
            materias.add("ma311");
            Exemplo exemplo = new Exemplo("Eliel Oliveira da Silva", "eliel.oliveira2004@gmail.com", materias);
//            writeFile(exemplo);
            System.out.println(readFile("dados/Exemplo/b4873914-7a66-4255-bee1-fbe097c78fab.json"));
//            readFile("data/exemplo.json");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static JsonObject readFile(String path) throws NullPointerException, FileNotFoundException {
        File file = new File(path);
        JsonReader jsonReader = Json.createReader(new FileReader(file));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }

    public static void writeFile(String path, JsonObject jsonObject) {
        createFolderIfNotExists(path);
        try (FileWriter fileWriter = new FileWriter(path)){
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeFile(JsonSerializable jsonSerializable) throws Exception {
        String path = "dados/" + jsonSerializable.getClass().getSimpleName() + "/" + jsonSerializable.getId() + ".json";
        writeFile(path, jsonSerializable.writeJson());
    }

    private static String getPath(JsonSerializable jsonSerializable){
        return "dados/" + jsonSerializable.getClass().getSimpleName() + "/" + jsonSerializable.getId() + ".json";
    }

    private static void createFolderIfNotExists(String path){
        String folderPath = getParentPath(path);
        new File(folderPath).mkdirs();
    }

    private static String getParentPath(String path){
        Path path1 = Paths.get(path);
        Path folderPath = path1.getParent();
        if (folderPath != null){
            return folderPath.toString();
        }
        return "";
    }
}
