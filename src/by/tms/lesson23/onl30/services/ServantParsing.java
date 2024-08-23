package by.tms.lesson23.onl30.services;

import by.tms.lesson23.onl30.parcers.model.Sonnet;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ServantParsing {

    public static Path getPath(Sonnet sonnet) {
        String nameFile = sonnet.getAuthor().getFirstName() + "_" +
                sonnet.getAuthor().getLastName() + "_" +
                sonnet.getTitle() + ".txt";
        return Paths.get(nameFile);
    }

    public static String getText(Sonnet sonnet) {
        StringBuilder text = new StringBuilder();
        for (String line : sonnet.getText()) {
            text.append(line).append("\n");
        }
        return text.toString();
    }
}
