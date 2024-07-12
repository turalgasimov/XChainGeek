import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class XMLReader {

    public void XMLHandler(String date, String valuteCode, double amount) {

        int connCounter = 0;

        while (connCounter < 3) {

            try {

                String urlString = "https://www.cbar.az/currencies/"+date+".xml";
                URI uri = URI.create(urlString);
                URL url = uri.toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                InputStream is = conn.getInputStream();

                DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = df.newDocumentBuilder();
                Document doc = db.parse(conn.getInputStream());

                doc.getDocumentElement().normalize();

                Element root = doc.getDocumentElement();

                System.out.println(root.getAttribute("Date")+" üçün məzənnə:");

                NodeList nl = doc.getElementsByTagName("Valute");

                for (int i = 0; i < nl.getLength(); i++) {
                    Element element = (Element) nl.item(i);
                    String attributeValue = element.getAttribute("Code");
                    if (attributeValue.equals(valuteCode)) {
                        amount *= Double.parseDouble(element.getElementsByTagName("Value").item(0).getTextContent());
                        break;
                    }
                }

                System.out.printf("%.4f", amount);

                is.close();
                break;

            } catch (ParserConfigurationException | SAXException | IOException e) {
                connCounter++;
                e.printStackTrace();
            }
        }

    }

}
