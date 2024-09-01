package itstep.learning.fs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigWriterTemplate {
    public void createConfigFile(String filePath) {
        File configFile = new File(filePath);
        try {
            // Создаем родительские директории, если они не существуют
            configFile.getParentFile().mkdirs();

            // Создаем файл, если его не существует
            if (!configFile.exists()) {
                configFile.createNewFile();
            }

            // Записываем настройки в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
                writer.write("dbms=mysql\n");
                writer.write("host=localhost\n");
                writer.write("port=3308\n");
                writer.write("schema=java_pv222\n");
                writer.write("user=your_username\n");
                writer.write("password=your_password\n");
                writer.write("useUnicode=true\n");
                writer.write("characterEncoding=utf8\n");
            }

            System.out.println("Файл конфигурации успешно создан по пути: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла конфигурации: " + e.getMessage());
        }
    }
}
