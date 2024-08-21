package by.tms.lesson23.onl30.parcers.model;

import java.util.List;

public class SonnetModel {
//    private String type;
    private AuthorModel author;
    private String title;
    private List<String> lines;

//    public String getType() {
//        return type;
//    }

    public AuthorModel getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Sonnet{" +
//                "type='" + type + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", lines=" + lines +
                '}';
    }
}
