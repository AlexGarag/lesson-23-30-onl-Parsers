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

    public static void main(String[] args) throws SAXException, ParserConfigurationException {

//        Написать программу для парсинга xml документа. Необходимо распарсить xml документ и
//        содержимое тегов line записать в другой документ. Название файла для записи должно
//        состоять из значений тегов и имеет вид: <firstName>_<lastName>_<title>.txt

        Sonnet sonnet;
        try {
            sonnet = doXmlParsing(new File("text.xml"));
// todo вынести работу с файлом в отдельный метод с созданием своего потока
            Files.writeString(getPath(sonnet), getText(sonnet), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }

    }
}