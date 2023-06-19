package com.datapersistence;

import javax.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public class JsonManager {

    public static boolean excluirArquivo(String path){
        return new File(path).delete();
    }

    public static JsonObject readFile(String path) throws NullPointerException, FileNotFoundException {
        File file = new File(path);
        JsonReader jsonReader = Json.createReader(new FileReader(file));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }

    public static boolean writeFile(String path, JsonObject jsonObject) {
        createFolderIfNotExists(path);
        try (FileWriter fileWriter = new FileWriter(path)){
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();
            return true;
        }catch (Exception ignore){
            return false;
        }
    }

    public static boolean writeFile(JsonSerializable jsonSerializable) {
        return writeFile(getPath(jsonSerializable), jsonSerializable.writeJson());
    }

    public static String getPath(JsonSerializable jsonSerializable){
        return buildPath(jsonSerializable.getClass(), jsonSerializable.getId());
    }

    public static <T> String buildPath(Class<T> clazz, UUID id){
        return "dados/" + clazz.getSimpleName() + "/" + id + ".json";
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
        try {
            for (File file : Objects.requireNonNull(new File(pasta).listFiles())) {
                try {
                    JsonObject jsonObject = readFile(file.getPath());
                    if (jsonObject.getString(campo).equals(valor)) {
                        return false;
                    }
                } catch (Exception ignored) {
                }
            }
        }catch (Exception ignored){
            return true;
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

    public static JsonObject buscarPorCampo(String pasta, String campo, String valor) throws Exception{
        for (File file : Objects.requireNonNull(new File(pasta).listFiles())) {
            try {
                JsonObject jsonObject = readFile(file.getPath());
                if (jsonObject.getString(campo).equals(valor)) {
                    return jsonObject;
                }
            } catch (Exception ignored) {
            }
        }
        throw new Exception();
    }
}
