package org.example.interfaces;

import org.example.important.CollectionManager;
import org.example.models.SpaceMarine;

import java.io.File;
import java.io.IOException;

public interface FileManagable {
    void saveToFile(File file, CollectionManager<SpaceMarine> collectionManager) throws IOException;
    void readFromFile(File file, CollectionManager<SpaceMarine> collectionManager);

}
