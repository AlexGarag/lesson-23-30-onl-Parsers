package by.tms.lesson23.onl30.parcers;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static by.tms.lesson23.onl30.parcers.xml_parsing.XmlParser.doXmlParsing;

public class StarterParsing {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

//        Написать программу для парсинга xml документа. Необходимо распарсить xml документ и
//        содержимое тегов line записать в другой документ. Название файла для записи должно
//        состоять из значений тегов и имеет вид: <firstName>_<lastName>_<title>.txt

        doXmlParsing(new File("text.xml"));
    }
}