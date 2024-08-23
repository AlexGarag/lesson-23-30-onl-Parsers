package by.tms.lesson23.onl30.parcers;

import by.tms.lesson23.onl30.parcers.model.Sonnet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static by.tms.lesson23.onl30.parcers.xml_parsing.XmlParser.doXmlParsing;
import static by.tms.lesson23.onl30.services.ServantParsing.getPath;
import static by.tms.lesson23.onl30.services.ServantParsing.getText;

public class StarterParsing {
    static final String IO_ERROR_MESSAGE = "The problem with the file being processed";
    static final String PARSING_ERROR_MESSAGE = "The parsing process has failed";

    public static void main(String[] args) {

//        Написать программу для парсинга xml документа. Необходимо распарсить xml документ и
//        содержимое тегов line записать в другой документ. Название файла для записи должно
//        состоять из значений тегов и имеет вид: <firstName>_<lastName>_<title>.txt

        Sonnet sonnet;
        try {
            sonnet = doXmlParsing(new File("text.xml"));
            Thread doFile = new Thread(() -> {
                try {
                    Files.writeString(getPath(sonnet), getText(sonnet), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            doFile.start();
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        } catch (SAXException | ParserConfigurationException e) {
            System.out.println(PARSING_ERROR_MESSAGE);
        }

    }
}