package by.tms.lesson23.onl30.parcers.model;

import java.util.List;

public class Sonnet {
    private String type;
    private Author author;
    private String title;
    private List<String> lines;

    public String getType() {
        return type;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "Sonnet{" +
                "type='" + type + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", lines=" + lines +
                '}';
    }
}
