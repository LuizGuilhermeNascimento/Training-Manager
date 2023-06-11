package com.datapersistence;

import javax.json.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


class Exemplo implements JsonSerializable{
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
                .add("email", getEmail())
                .add("materias", jsonArrayBuilder).build();
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
            writeFile("data/exemplo.json", exemplo);
//            readFile("data/exemplo.json");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static JsonArray readFile(String path) throws Exception{
        File file = new File(path);
        if (file.createNewFile()){
            JsonArray emptyJsonArray = Json.createArrayBuilder().build();
            writeFile(path, emptyJsonArray);
        }
        JsonReader jsonReader = Json.createReader(new FileReader(file));
        JsonArray jsonArray = jsonReader.readArray();
        jsonReader.close();
        return jsonArray;
    }

    public static void writeFile(String path, JsonArray jsonArray) {
        try (FileWriter fileWriter = new FileWriter(path)){
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeArray(jsonArray);
            jsonWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeFile(String path, JsonSerializable jsonSerializable) throws Exception {
        JsonArray jsonArray = readFile(path);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        jsonArray.forEach(arrayBuilder::add);
        arrayBuilder.add(jsonSerializable.writeJson());
        writeFile(path, arrayBuilder.build());

//        try (FileWriter fileWriter = new FileWriter(path)){
//
//            JsonWriter jsonWriter = Json.createWriter(fileWriter);
////            jsonArray.add(jsonSerializable.writeJson());
////            jsonWriter.writeArray(jsonArray);
//            jsonWriter.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
