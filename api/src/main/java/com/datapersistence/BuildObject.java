package com.datapersistence;

import com.projeto_mc322.api.models.acompanhamento.Acompanhamento;
import com.projeto_mc322.api.models.treino.Treino;
import com.projeto_mc322.api.models.user.Aluno;
import com.projeto_mc322.api.models.user.Professor;

import javax.json.JsonObject;
import javax.json.JsonString;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class BuildObject {
    private static Professor buildProfessor(JsonObject jsonObject, HashMap<String, Object> visitados){
        if (visitados.containsKey(jsonObject.getString("id"))){
            return (Professor) visitados.get(jsonObject.getString("id"));
        }
        Professor professor = new Professor(
                UUID.fromString(jsonObject.getString("id")),
                jsonObject.getString("nome"),
                jsonObject.getString("cpf"),
                jsonObject.getString("email"),
                jsonObject.getString("senha"),
                jsonObject.getString("cref")
        );
        visitados.put(professor.getId().toString(), professor);

        jsonObject.getJsonArray("acompanhamentos").forEach(acompanhamento -> {
            String id = ((JsonString)acompanhamento).getString();
            if (visitados.containsKey(id)){
                professor.getAcompanhamentos().add((Acompanhamento) visitados.get(id));
            }else{
                try{
                    JsonObject jsonObject1 = JsonManager.readFile("dados/Acompanhamento/" + id + ".json");
                    Acompanhamento acompanhamento1 = buildAcompanhamento(jsonObject1, visitados);
                    professor.getAcompanhamentos().add(acompanhamento1);
                }catch (Exception ignored){
                }
            }
        });
        return professor;
    }

    private static Acompanhamento buildAcompanhamento(JsonObject jsonObject, HashMap<String, Object> visitados){
        if (visitados.containsKey(jsonObject.getString("id"))){
            return (Acompanhamento) visitados.get(jsonObject.getString("id"));
        }
        Acompanhamento acompanhamento = new Acompanhamento(
                UUID.fromString(jsonObject.getString("id")),
                jsonObject.getInt("treinosRealizados"),
                jsonObject.getInt("treinosMeta")
        );
        visitados.put(acompanhamento.getId().toString(), acompanhamento);

        jsonObject.getJsonArray("treinos").forEach( treino -> {
            JsonObject jsonObjectTreino = (JsonObject) treino;
            acompanhamento.getTreinos().add(
                    new Treino(
                            jsonObjectTreino.getString("tipo"),
                            jsonObjectTreino.getString("nome"),
                            jsonObjectTreino.getString("descricao")
                            )
            );
        });

        if (visitados.containsKey(jsonObject.getString("alunoId"))){
            acompanhamento.setAluno((Aluno) visitados.get(jsonObject.getString("alunoId")));
        }else{
            try{
                JsonObject jsonObject1 = JsonManager.readFile("dados/Aluno/" + jsonObject.getString("alunoId") + ".json");
                Aluno aluno = buildAluno(jsonObject1, visitados);
                acompanhamento.setAluno(aluno);
            }catch (Exception ignored){
            }
        }
        if (visitados.containsKey(jsonObject.getString("professorId"))){
            acompanhamento.setProfessor((Professor) visitados.get(jsonObject.getString("professorId")));
        }else{
            try{
                JsonObject jsonObject1 = JsonManager.readFile("dados/Professor/" + jsonObject.getString("professorId") + ".json");
                Professor professor = buildProfessor(jsonObject1, visitados);
                acompanhamento.setProfessor(professor);
            }catch (Exception ignored){
            }
        }
        return acompanhamento;
    }

    private static Aluno buildAluno(JsonObject jsonObject, HashMap<String, Object> visitados){
        if (visitados.containsKey(jsonObject.getString("id"))){
            return (Aluno) visitados.get(jsonObject.getString("id"));
        }
        Aluno aluno = new Aluno(
                UUID.fromString(jsonObject.getString("id")),
                jsonObject.getString("nome"),
                jsonObject.getString("cpf"),
                jsonObject.getString("email"),
                jsonObject.getString("senha")
        );
        visitados.put(aluno.getId().toString(), aluno);

        if (visitados.containsKey(jsonObject.getString("acompanhamento"))){
            aluno.setAcompanhamento(Optional.of((Acompanhamento) visitados.get(jsonObject.getString("acompanhamento"))));
        }else{
            try{
                JsonObject jsonObject1 = JsonManager.readFile("dados/Acompanhamento/" + jsonObject.getString("acompanhamento") + ".json");
                aluno.setAcompanhamento(Optional.of(buildAcompanhamento(jsonObject1, visitados)));
            }catch (Exception ignored) {
            }
        }
        return aluno;
    }

    public static Professor buildProfessor(JsonObject jsonObject){
        return buildProfessor(jsonObject, new HashMap<>());
    }

    public static Aluno buildAluno(JsonObject jsonObject){
        return buildAluno(jsonObject, new HashMap<>());
    }

    public static Acompanhamento buildAcompanhamento(JsonObject jsonObject){
        return buildAcompanhamento(jsonObject, new HashMap<>());
    }

    public static <T> T build(Class<T> clazz, JsonObject jsonObject){
        if (clazz.equals(Aluno.class)){
            return clazz.cast(buildAluno(jsonObject));
        }else if (clazz.equals(Professor.class)){
            return clazz.cast(buildProfessor(jsonObject));
        }
        return clazz.cast(buildAcompanhamento(jsonObject));
    }
}
