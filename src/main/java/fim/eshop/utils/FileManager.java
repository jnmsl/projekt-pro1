package fim.eshop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import fim.eshop.model.Item;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManager {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveItemsToFile(List<Item> items, String fileName) throws IOException {
        objectMapper.writeValue(new File(fileName), items);
    }

    public static List<Item> loadItemsFromFile(String fileName) throws IOException {
        return objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
    }
}