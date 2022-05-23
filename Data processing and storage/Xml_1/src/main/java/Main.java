import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class Main {

  private void readXml(String path) throws FileNotFoundException, XMLStreamException {
    XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
    XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));

    while (reader.hasNext()) {
      XMLEvent event = reader.nextEvent();

    }
  }

}
