package kz.eserzhanov.testtask.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapException {
    /**
     * @param map
     * @return ResponseEntity
     * Возвращение ошибки
     */
    public ResponseEntity getErrorResponse(Map<String, String> map){
        switch (map.get("type")) {
            case "NOT FOUND":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    /**
     * @param type
     * @return Map
     * Возвращает сообщение ошибки
     */
    public Map<String, String> getException(String type, String message) {
        Map<String, String> map;
        switch (type) {
            case "default":
                map = getDefaultException(message);
                return map;
            case "user":
                map = getUserException(message);
                return map;
            case "client":
                map = getClientException(message);
                return map;
        }

        return null;
    }


    private Map<String, String> getClientException(String message) {
        Map<String, String> map = new HashMap<>();
        switch (message){
            case "notFound":
                map.put("errorRu", "Клиент не найден");
                map.put("errorKz", "Пайдаланушы табылмады");
                map.put("type", "NOT FOUND");
                return map;
        }
        return null;
    }
    private Map<String, String> getUserException(String message) {
        Map<String, String> map = new HashMap<>();
        switch (message){
            case "notFound":
                map.put("errorRu", "Пользователь не найден");
                map.put("errorKz", "Пайдаланушы табылмады");
                map.put("type", "NOT FOUND");
                return map;
            case "emailExists":
                map.put("errorRu", "Такой \"E-mail\" уже существует");
                map.put("errorKz", "Мұндай \"E-mail\" бар");
                map.put("type", "BAD REQUEST");
                return map;
        }
        return null;
    }
    private Map<String, String> getDefaultException(String message) {
        Map<String, String> map = new HashMap<>();
        switch (message){
            case "notFound":
                map.put("errorRu", "Не найдено");
                map.put("errorKz", "Табылмады");
                map.put("type", "NOT FOUND");
                return map;
            case "permissionDenied":
                map.put("errorRu", "Отказано в доступе");
                map.put("errorKz", "Бас тартуға қол жеткізу");
                map.put("type", "BAD REQUEST");
                return map;
            case "wrong":
                map.put("errorRu", "Что-то пошло не так");
                map.put("errorKz", "Нәрсе емес");
                map.put("type", "BAD REQUEST");
                return map;
        }
        return null;
    }
}
