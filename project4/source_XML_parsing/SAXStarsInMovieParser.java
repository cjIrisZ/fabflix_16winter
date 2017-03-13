


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

public class SAXStarsInMovieParser extends DefaultHandler{

	ArrayList<Stars_in_Movie> RelationList;
	
	private String tempVal,movieid;
	
	//to maintain context
	private Stars_in_Movie tempRel;
	
	
	public SAXStarsInMovieParser(){
		RelationList = new ArrayList<Stars_in_Movie>();
	}
	
	public ArrayList<Stars_in_Movie> runExample() {
		parseDocument();
		return RelationList;
	}

	private void parseDocument() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser spmovie = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			InputStream input = new FileInputStream("casts124.xml");
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
		
		System.out.println("No of Star-Movie relationships '" + RelationList.size() + "'.");
		
		Iterator it = RelationList.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if (qName.equalsIgnoreCase("filmc"))
		{
			tempRel = new Stars_in_Movie();
//			System.out.println("new star");
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("filmc")) {
			tempRel.setMovieid(movieid);
			RelationList.add(tempRel);
		}else if (qName.equalsIgnoreCase("f")) {
			movieid = tempVal;
		}else if (qName.equalsIgnoreCase("a")) {
			tempRel.updateStarList(tempVal);
		}
		
	}
	
}



