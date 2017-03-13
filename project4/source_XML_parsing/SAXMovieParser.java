


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

public class SAXMovieParser extends DefaultHandler{

	public ArrayList<Movie> MovieList;
	
	private String tempVal;
	private String directorName;
	
	//to maintain context
	private Movie tempMovie;
	
	
	public SAXMovieParser(){
		MovieList = new ArrayList<Movie>();
	}
	
	public ArrayList<Movie> runExample() {
		parseDocument();
		//printData();
		return MovieList;
	}

	private void parseDocument() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser spmovie = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			InputStream input = new FileInputStream("mains243.xml");
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
		
		System.out.println("No of Movies '" + MovieList.size() + "'.");
		
		Iterator it = MovieList.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if (qName.equalsIgnoreCase("film"))
		{
			tempMovie = new Movie();
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("dirname")){
			directorName = tempVal;
		}
		else if(qName.equalsIgnoreCase("film")) {
			tempMovie.setDirector(directorName);
			MovieList.add(tempMovie);
		}else if (qName.equalsIgnoreCase("fid")) {
			tempMovie.setId(tempVal);
		}else if (qName.equalsIgnoreCase("t")) {
			tempMovie.setTitle(tempVal);
		}else if (qName.equalsIgnoreCase("year")) {
			tempMovie.setYear(tempVal);
		}else if (qName.equalsIgnoreCase("cat")){
			tempMovie.updateGenres(tempVal);
		}
		
	}
//	public static void main(String[] args){
//	SAXMovieParser spe = new SAXMovieParser();
//	spe.runExample();
//}
}



