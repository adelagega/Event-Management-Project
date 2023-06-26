package XML;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLValidatorWithDOM {
    private static final Logger logger = Logger.getLogger(XMLValidatorWithDOM.class);

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/main/java/XML/booking.xml");
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Source schemaFile = new StreamSource("src/main/java/XML/booking.xsd");
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            logger.info("XML document is valid.");
        } catch (Exception e) {
            logger.error("Error occurred while validating XML document: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
