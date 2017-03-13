


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class SAXStarParser extends DefaultHandler{

	public ArrayList<Star> StarList;
	
	private String tempVal;
	
	//to maintain context
	private Star tempStar;
	
	
	public SAXStarParser(){
		StarList = new ArrayList<Star>();
	}
	
	public ArrayList<Star> runExample() {
		parseDocument();
		return StarList;
	}

	private void parseDocument() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser spmovie = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			InputStream input = new FileInputStream("actors63.xml");
			InputStreamReader inputReader = new InputStreamReader(input,"ISO-8859-1");
			InputSource inputSource = new InputSource(inputReader);
			inputSource.setEncoding("ISO-8859-1");
			spmovie.parse(inputSource, this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Iterate through the list and print
	 * the contents
	 */
	private void printData(){
		
		System.out.println("No of Stars '" + StarList.size() + "'.");
		
		Iterator it = StarList.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if (qName.equalsIgnoreCase("actor"))
		{
			tempStar = new Star();
//			System.out.println("new star");
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("actor")) {
			if (tempStar!=null)	StarList.add(tempStar);
		}else if (qName.equalsIgnoreCase("stagename")) {
			tempStar.setStagename(tempVal);
		}else if (qName.equalsIgnoreCase("familyname")) {
			tempStar.setLastname(tempVal);
		}else if (qName.equalsIgnoreCase("firstname")) {
			tempStar.setFirstname(tempVal);
		}else if (qName.equalsIgnoreCase("dob")){
			tempStar.setDob(tempVal);
		}
		
	}
	
//	public static void main(String[] args){
//		SAXStarParser spe = new SAXStarParser();
//		spe.runExample();
//	}
	
}



