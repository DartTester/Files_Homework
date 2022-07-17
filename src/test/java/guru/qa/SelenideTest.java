package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideTest {

    @DisplayName("download test")
    @Test
    void downloadTest() throws Exception {
       Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File file = $("#raw-url").download();
        try (InputStream is = new FileInputStream(file)) {
            byte[] fileContent = is.readAllBytes();
            assertThat(new String(fileContent, UTF_8)).contains("Contributions to Junit 5");
        }
    }

    @DisplayName("upload test")
    @Test
    void uploadTest() {
        Selenide.open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("files/1.txt");
        $$("div.qq-dialog-message-selector")
                .find(text(
                        "1.txt has an invalid extension. " +
                                "Valid extension(s): jpeg, jpg, gif, png."
                )).shouldBe(visible);
    }
}
