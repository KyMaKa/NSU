import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Main {

  Map<String, Person> people = new HashMap<>();
  private void readXml(String path) throws FileNotFoundException, XMLStreamException {
    XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
    XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));

    while (reader.hasNext()) {
      Person person = new Person();
      XMLEvent event = reader.nextEvent();
      if (event.isStartElement()) {
        StartElement startElement = event.asStartElement();
        switch (startElement.getName().getLocalPart()) {
          case "person":
            person = new Person();
            Attribute id = startElement.getAttributeByName(new QName("id"));
            if (id != null)
              person.setId(id.getValue());
            Attribute name = startElement.getAttributeByName(new QName("name"));
            if (name != null) {
              person.setName(name.getValue());
              person.setFirstName(name.getValue().split("\\s+")[0]);
              person.setFamilyName(name.getValue().split("\\s+")[1]);
              person.setSurname(name.getValue().split("\\s+")[1]);
            }
            break;
          case "wife":
            Attribute value = startElement.getAttributeByName(new QName("value"));
            if (value != null)
              person.setWife(value.getValue());
            break;
          case "husband":
            value = startElement.getAttributeByName(new QName("value"));
            if (value != null)
              person.setHusband(value.getValue());
            break;
          case "parent":
            value = startElement.getAttributeByName(new QName("value"));
            if (value != null)
              person.setParent(value.getValue());
            else {
              event = reader.nextEvent();
              person.setParent(event.asCharacters().getData());
            }
            break;
          case "surname":
            value = startElement.getAttributeByName(new QName("value"));
            if (value != null)
              person.setSurname(value.getValue());
            else {
              event = reader.nextEvent();
              person.setSurname(event.asCharacters().getData());
            }
            break;
          case "family-name":
            value = startElement.getAttributeByName(new QName("value"));
            if (value != null)
              person.setFamilyName(value.getValue());
            else {
              event = reader.nextEvent();
              person.setFamilyName(event.asCharacters().getData());
            }
            break;
          case "firstname":
            value = startElement.getAttributeByName(new QName("value"));
            if (value != null)
              person.setFirstName(value.getValue());
            else {
              event = reader.nextEvent();
              person.setFirstName(event.asCharacters().getData());
            }
            break;
          case "fullname":
            reader.nextEvent();
            event = reader.nextEvent();
            person.setFirstName(event.asCharacters().getData());
            reader.nextEvent();
            event = reader.nextEvent();
            person.setSurname(event.asCharacters().getData());
            break;
          case "id":
            value = startElement.getAttributeByName(new QName("value"));
            if (value != null)
              person.setId(value.getValue());
            break;
        }

      }
    }
  }

}
