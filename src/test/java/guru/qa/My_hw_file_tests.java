package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.zip.ZipFile;

public class My_hw_file_tests {

    ClassLoader classLoader = My_hw_file_tests.class.getClassLoader();

    @DisplayName("check PDF file from zip")
    @Test
    void checkPdfTest() throws Exception {

        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("zip_archiv.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("pdf_sample.pdf");
        PDF pdf;
        InputStream inputStream = zipFile.getInputStream(entry);
        pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("Adobe PDF is an ideal format for electronic document");

    }

    @DisplayName("check XLS file from zip")
    @Test
    void checkXlsTest() throws Exception {

        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("zip_archiv.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("xls_drivers.xlsx");
        XLS xlsx;
        InputStream inputStream = zipFile.getInputStream(entry);
        xlsx = new XLS(inputStream);
        assertThat(xlsx.excel.getSheetAt(1).getRow(0).getCell(0).getStringCellValue())
                .contains("Сущность");
    }

    @DisplayName("check CSV file from zip")
    @Test
    void checkCsvTest() throws Exception {
        //с помощью класс лоудера ищем зип
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("zip_archiv.zip")).getFile());
        //достаем нужный файл в зипе
        ZipEntry entry = zipFile.getEntry("csv_drivers.csv");
        List<String[]> list;
        InputStream inputStream = zipFile.getInputStream(entry);
        //читаем csv
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        list = csvReader.readAll();
        assertThat(list).contains(
                new String[] {"16 july", " bmw", " Petrov"},
                new String[] {"19 july", " kamaz", " Korovin"}
        );
    }
}


