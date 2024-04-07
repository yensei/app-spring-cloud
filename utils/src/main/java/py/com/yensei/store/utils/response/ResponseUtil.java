package py.com.yensei.store.utils.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.com.yensei.store.utils.constants.ErrorMessage;

import org.springframework.validation.BindingResult;

public class ResponseUtil {

    public static String formatMessage(BindingResult result){
        List<Map<String, String>> messages = result.getFieldErrors().stream()
            .map(err -> {
                Map<String,String> error = new HashMap<>();
                error.put(err.getField(), err.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
            .code("01")
            .messages(messages)
            .build();

        // Convert JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonString ="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;

    }
}
