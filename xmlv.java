import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
public class Xmlv {
    public static void main(String[] args) {
        if(args.length !=2){
            System.out.println( "XSDValidator<fname.xsd><fname.xml>" );
        } else {
            boolean isV = validateXMLSchema(args[0],args[1]);
            if(isV){
                System.out.println(args[1] + " is well-formed " + args[0]);
            } else {
                System.out.println(args[1] + " is not well-formed  " + args[0]);
            }
        }
    }
    public static boolean validateXMLSchema(String xsdP, String xmlP){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema s = factory.newSchema(new File(xsdP));
            Validator validator = s.newValidator();
            validator.validate(new StreamSource(new File(xmlP)));
        } catch (IOException e1){
            System.out.println("Exception: "+e1.getMessage());
            return false;
        }catch(SAXException e2){
            System.out.println("SAX Exception: "+e2.getMessage());
            return false;
        }
        return true;
    }
}