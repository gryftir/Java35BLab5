package lab5;
import java.io.*;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*; 

public class HandleFile {

	
	public static File getFile(String str)
	{
		try
		{
			new HandleFile().createXmlDocument(str);
			return new File("Output.xml");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	public Document createDocument() throws IOException, ParserConfigurationException
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		return doc;
	}
	
	public void processDocument(Document doc, String sfile) throws Exception
	{
		File inFile  = new File(sfile);
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        String sline = reader.readLine();
        String[] sheaders = sline.split(",");
        sline = reader.readLine();
        String[] snodes = sline.split(",");
		Element aroot;
		Element achild;
		aroot = doc.createElement(sheaders[0]);
		doc.appendChild(aroot);
        while ((sline=reader.readLine()) != null) 
        {    		
        	achild = doc.createElement(sheaders[1]);
        	String[] sdata = sline.split(",");
        	for (int x=0; x<snodes.length; ++x)
        	{
				Element c = doc.createElement(snodes[x]);  
				c.appendChild(doc.createTextNode(sdata[x]));
				achild.appendChild(c);
        	}
        	aroot.appendChild(achild);
        }
        reader.close();
	}
	
	public void createXML(Document doc) throws Exception
	{
		//TransformerFactory instance is used to create Transformer objects. 
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		// create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = sw.toString();
		File file = new File("Output.xml");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		bw.write(xmlString);
		bw.flush();
		bw.close();		
	}

	public void createXmlDocument(String infile) throws Exception 
	{
		Document doc = createDocument();
		processDocument(doc,infile);
		createXML(doc);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 HandleFile.getFile("Data.csv");
	}

}
