package guru.qa;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class My_hw_json_test {

    ClassLoader classLoader = My_hw_file_tests.class.getClassLoader();

    @DisplayName("check Json with Gson")
    @Test
    void jsonTest() {
        InputStream is = classLoader.getResourceAsStream("json_example.json");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(new InputStreamReader(is), JsonObject.class);
        assertThat(jsonObject.get("name").getAsString()).isEqualTo("Tester");
        assertThat(jsonObject.get("number").getAsJsonObject().get("homeNum").getAsInt()).isEqualTo(1234567);
        assertThat(jsonObject.get("isClosed").getAsBoolean()).isEqualTo(false);
    }

    @DisplayName("check Json with Jackson")
    @Test
    void jsonJacksonTest() throws Exception {
        //с помощью класс лоудера находим json
        InputStream inputStream = classLoader.getResourceAsStream("json_example.json");

        //инициализацируем обьект ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //вызываем readTree с джейсоном как параметром
        JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(inputStream));

        //проверка содержимого файла
        assertThat(jsonNode.get("name").asText()).isEqualTo("Tester");
        assertThat(jsonNode.get("age").asInt()).isEqualTo(27);
        assertThat(jsonNode.get("isClosed").asBoolean()).isEqualTo(false);
        assertThat(jsonNode.findValue("number").findValue("workNum")
                .asInt()).isEqualTo(123456789);
    }
}
