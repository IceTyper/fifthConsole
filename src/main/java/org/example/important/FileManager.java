package org.example.important;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.interfaces.FileManagable;
import org.example.models.SpaceMarine;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;

public class FileManager implements FileManagable {
    @Override
    public void saveToFile(File file, CollectionManager collectionManager) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        if (!file.exists()) {
            file.getParentFile().mkdirs();
        } else {
            file.delete();
            file.createNewFile();
        }


        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setPrettyPrinting().create();

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            gson.toJson(collectionManager.getCollection(), writer);
            System.out.println("Запись данных выполнена, записалось:");
            System.out.println(gson.toJson(collectionManager.getCollection()));
            System.out.println("Всё сохранено в " + file.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Запись данных провалена :(((");
        }
    }

    //Имя файла должно передаваться программе с помощью:**аргумент командной строки**.
    //Сделай java -jar джарник название файла
    @Override
    public void readFromFile(File file, CollectionManager collectionManager) {
        if (file == null || !file.exists()) {
            return;
        }

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setPrettyPrinting().create();

        try (BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream(file));
             InputStreamReader reader = new InputStreamReader(bufferedInput, StandardCharsets.UTF_8);
        ) {
            Type type = new TypeToken<ArrayDeque<SpaceMarine>>() {}.getType();
            collectionManager.setCollection(gson.fromJson(reader, type));
            System.out.println("Данные из файла загружены и готовы к работе");
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            System.out.println("Данные из файлы загрузиться не сумели");
        }

    }

    static class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
            if (localDate == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(formatter.format(localDate));
            }
        }

        @Override
        public LocalDate read(JsonReader jsonReader) throws IOException {
            return LocalDate.parse(jsonReader.nextString(), formatter);
        }
    }
}
