/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.zad2a;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author A613431
 */
public class XmlReader {

    public List<Element> usersList;
    
    public void parse()
    {
        usersList = new ArrayList<Element>();

        File xmlFile = new File("..\\JavaSe-Zad2a\\users.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc= null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        doc.getDocumentElement().normalize();
        
        NodeList nList = doc.getElementsByTagName("User");
	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
                        usersList.add(eElement);
		}        


        }
    
    }
    
    public int verify(String login, String password)
    {
        parse();
        int result = 0;
        for(int i = 0; i <usersList.size(); i++)
        {

            if(usersList.get(i).getElementsByTagName("Login").item(0).getTextContent().equals(login) && usersList.get(i).getElementsByTagName("Password").item(0).getTextContent().equals(password))
            {
                result = 1;
                break;
            }
            
            else 
                result = 0;
        }
        return result;
    }
}
