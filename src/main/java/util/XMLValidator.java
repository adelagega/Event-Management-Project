package util;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class XMLValidator {
    public static void main(String[] args) {
        System.out.println("events.xml validates against events.xsd? " + validateXMLSchema("events.xsd","events.xml"));
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        try {
            SchemaFactory sf =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL xsdURL = XMLValidator.class.getClassLoader().getResource(xsdPath);
            URL xmlURL = XMLValidator.class.getClassLoader().getResource(xmlPath);
            if (xsdURL == null || xmlURL == null) {
                throw new IllegalArgumentException("Could not find resource files");
            }
            Schema schema = sf.newSchema(xsdURL);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlURL.toString()));

        } catch (IOException | SAXException e) {
            System.out.println("Exception" + e.getMessage());
            return false;
        }
          return true;
    }
}
