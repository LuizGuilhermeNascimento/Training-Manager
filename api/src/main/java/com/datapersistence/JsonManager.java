package com.datapersistence;

import com.projeto_mc322.api.models.sala.Sala;
import com.projeto_mc322.api.models.secao.Secao;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;

import javax.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

public class JsonManager {
    public static void main(String[] args) {
        try{
            Professor professor = new Professor("Kratos", "23866461536", "51629996", "eafad", "adfasdf");
            Aluno aluno = new Aluno("eADFADFA", "Q2342Q4", "ADFADFAS", "adfadsfadf");
            Secao secao = professor.criarSecao("ADFDASFASDFASDF", "AAAAAAAAAAAAAAAAAAAA", 20, new Sala(), new Date(), 60);
            secao.getAlunos().add(aluno);
            writeFile(professor);
            writeFile(secao);
            writeFile(aluno);
            System.out.println(readFile("dados/Aluno/" + aluno.getId() + ".json"));
            System.out.println(readFile("dados/Professor/" + professor.getId() + ".json"));
            System.out.println(readFile("dados/Secao/" + secao.getId() + ".json"));
//            readFile("data/exemplo.json");
//            System.out.println(crefUnico("51629996"));
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

    public static boolean campoUnico(String pasta, String campo, String valor){
        for (File file: Objects.requireNonNull(new File(pasta).listFiles())){
            try{
                JsonObject jsonObject = readFile(file.getPath());
                if (jsonObject.getString(campo).equals(valor)){
                    return false;
                }
            } catch (Exception ignored) {
            }
        }
        return true;
    }

    public static boolean cpfUnico(String cpf){
        if (!campoUnico("dados/Professor", "cpf", cpf)){
            return false;
        }
        return campoUnico("dados/Aluno", "cpf", cpf);
    }
    public static boolean crefUnico(String cref){
        return campoUnico("dados/Professor", "cref", cref);
    }

    public static boolean emailUnico(String email){
        if (!campoUnico("dados/Professor", "email", email)){
            return false;
        }
        return campoUnico("dados/Aluno", "email", email);
    }
}
