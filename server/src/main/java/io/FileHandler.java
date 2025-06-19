package io;

import models.SpaceMarine;
import collection.CollectionHandable;
import collection.CollectionHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;

@Deprecated
public class FileHandler implements FileHandable {
    @Override
    public String saveToFile() throws IOException {
        CollectionHandable collectionHandler = new CollectionHandler();
        Deque<SpaceMarine> collection = collectionHandler.getCollection();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setPrettyPrinting().create();

        try (BufferedWriter writer = Files.newBufferedWriter(new File("collection.json").toPath(), StandardCharsets.UTF_8)) {
            gson.toJson(collection, writer);
            return "Saved: " + gson.toJson(collection);
        } catch (IOException e) {
            System.err.println("Saving Error");
        }
        return "Error: Unable to save the collection to file";
    }

    @Override
    public String readFromFile() {
        CollectionHandable collectionHandler = new CollectionHandler();
        Deque<SpaceMarine> collection = collectionHandler.getCollection();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setPrettyPrinting().create();

        try (BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream("collection.json"));
             InputStreamReader reader = new InputStreamReader(bufferedInput, StandardCharsets.UTF_8);
        ) {
            Type type = new TypeToken<ArrayDeque<SpaceMarine>>() {}.getType();
            collectionHandler.setCollection(gson.fromJson(reader, type));
            return "Data loaded successfully";
        } catch (IOException e) {
            return "Data is lost";
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
