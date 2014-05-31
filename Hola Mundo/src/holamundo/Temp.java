package holamundo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


////
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;
////


/**
 * @author Martin Quiroz
 * 
 * Reads and saves the settings Properties file for use by the MessageParser
 */
public class Temp {
	
	final static String PROPERTIES_FILE_PATH = "config.properties";
	final static String SEPARATOR_LABEL = "separator";
	final static String SEPARATOR_DEFAULT_VALUE = "-";
	final static String LEVEL_LABEL = "level";
	final static String LEVEL_DEFAULT_VALUE = "INFO";
	final static String FORMAT_LABEL = "format";
	final static String FORMAT_DEFAULT_VALUE = "%d{HH:mm:ss} - %p - %m";
	final static String LOG_PATH_LABEL = "path";
	final static String CONSOLE_USE_LABEL = "console";
	final static String CONSOLE_TRUE_LABEL = "true";
    final static String REGEX_SPACE = "\\s+";
    final static String REGEX_ADD_DEFAULT_SEPARATOR = "|%n";

    private boolean consoleLogging;
    private String separator;
	private String levelFilter;
	private String formatList;
	private String[] filePaths;
	private String loggerName;
	
	//////
	private String role1 = null;
	private String role2 = null;
	private String role3 = null;
	private String role4 = null;
	private ArrayList<String> rolev;
	//////
	
	
	public Temp(){
		consoleLogging = true;
		separator = SEPARATOR_DEFAULT_VALUE;
		levelFilter = LEVEL_DEFAULT_VALUE;
		formatList = FORMAT_DEFAULT_VALUE;
		filePaths = new String[0];
	}

	public String getLevelFilter(){
		return levelFilter.toString();
	}
	
	public String getSeparator(){
		return separator;
	}
	
	public String[] getFilePaths(){
		return filePaths;
	}
	
	public boolean consoleLogEnabled(){
		return consoleLogging;
	}
	
    public String getFormat(){
    	return formatList;
    }
    
    public boolean fileLogEnabled(){
    	return (filePaths.length > 0);
    }
	
	/**
	 * @author Martin Quiroz
	 * @param nivel - name of level
	 * @return if corresponds to a level
	 * 
	 * Valids if the parameter corresponds to a level on the right (top) or at the same level
	 */
	public boolean belongsToLevelFilter(String nivel){
		return true;
	}

	/**
	 * @author Martin Quiroz
	 * Loads the Properties file and saves the values readed
	 * On properties file error, loads default values
	 */
	public void fileUploadProperties(){
	    Properties properties = new Properties();

	    try {
	      properties.load(new FileInputStream(PROPERTIES_FILE_PATH));
	    } catch (IOException e) {
	    	System.out.println( "Error de archivo. Se cargan valores por defecto." );
	    }
	    
	   	loadPropertiesValues(properties);
	}

    private void loadPropertiesValues(Properties properties){
        separator = properties.getProperty(SEPARATOR_LABEL,SEPARATOR_DEFAULT_VALUE);
        String level = properties.getProperty(LEVEL_LABEL,LEVEL_DEFAULT_VALUE); 
        levelFilter =  level;
        if( !((properties.getProperty(CONSOLE_USE_LABEL,CONSOLE_TRUE_LABEL)).equals(CONSOLE_TRUE_LABEL)) ){
        	consoleLogging = false;
        }
        obtainFormats(properties);
        obtainPaths(properties);
    }

    private void obtainFormats(Properties properties){
    	formatList = properties.getProperty(FORMAT_LABEL,FORMAT_DEFAULT_VALUE);
    }

    private void obtainPaths(Properties properties){
    	String paths = properties.getProperty(LOG_PATH_LABEL);
    	if(paths != null) filePaths = divideStringWithSeparator(paths);
    }

    private String[] divideStringWithSeparator(String string){
    	string = string.replaceAll(REGEX_SPACE,"");
    	return string.split("["+separator+"]"+REGEX_ADD_DEFAULT_SEPARATOR);	
    }

	public String getLoggerName() {
		return loggerName;
	}
	
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}
   
	/////////////////////////
	
	public boolean readXML(String xml) {
        rolev = new ArrayList<String>();
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();

            role1 = getTextValue(role1, doc, "role1");
            if (role1 != null) {
                if (!role1.isEmpty())
                    rolev.add(role1);
            }
            role2 = getTextValue(role2, doc, "role2");
            if (role2 != null) {
                if (!role2.isEmpty())
                    rolev.add(role2);
            }
            role3 = getTextValue(role3, doc, "role3");
            if (role3 != null) {
                if (!role3.isEmpty())
                    rolev.add(role3);
            }
            role4 = getTextValue(role4, doc, "role4");
            if ( role4 != null) {
                if (!role4.isEmpty())
                    rolev.add(role4);
            }
            return true;

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }
	
	
	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}
	
	
}
