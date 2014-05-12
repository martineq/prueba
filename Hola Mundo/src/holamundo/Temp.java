package holamundo;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class Temp {
    public static void main( String[] args )
    {
    	final String RUTA_ARCHIVO_PROPERTIES = "config.properties";
    	final String RUTA_ARCHIVO_LOG = "log.txt";
    	final String SEPARADOR_ETIQUETA = "separator";
    	final String SEPARADOR_VALOR_DEFAULT = "-";
    	final String NIVEL_ETIQUETA = "level";
    	final String NIVEL_VALOR_DEFAULT = "INFO";
    	final String FORMATO_ETIQUETA = "format";
    	final String FORMATO_VALOR_DEFAULT = "%d{HH:mm:ss} - %p - %m";
    	final String FECHA_VALOR_DEFAULT = "HH:mm:ss";
        final String REGEX_ESPACIOS = "\\s+";
        final String REGEX_AGREGAR_SEPARADOR_DEFAULT = "|%n";
        final String OPERADOR_PATRON = "%";
        final String INDICADOR_FECHA = "d";
    	final String INDICADOR_NIVEL = "p";
    	final String INDICADOR_HILO = "t";
    	final String INDICADOR_MENSAJE = "m";
    	final String INDICADOR_SEPARADOR = "n";
    	final String INDICADOR_LINEA = "L";
    	final String INDICADOR_ARCHIVO = "F";
    	final String INDICADOR_METODO = "M";

        Properties properties = new Properties();
        try {
          properties.load(new FileInputStream(RUTA_ARCHIVO_PROPERTIES));
        } catch (IOException e) {
        	System.out.println( "Error de archivo" );
        	// TODO: Se podr�an cargar todos valores por defecto sin leer nada
        }

        // cargarProperties()
        String separator = properties.getProperty(SEPARADOR_ETIQUETA,SEPARADOR_VALOR_DEFAULT);
        String level = properties.getProperty(NIVEL_ETIQUETA,NIVEL_VALOR_DEFAULT);

        // obtenerFormatos()
        String format = properties.getProperty(FORMATO_ETIQUETA,FORMATO_VALOR_DEFAULT);
        
        // dividirFormatos()
        format = format.replaceAll(REGEX_ESPACIOS, "");
        String[] formatList = format.split("["+separator+"]"+REGEX_AGREGAR_SEPARADOR_DEFAULT);
        
        // cargarFormatos()
        String rutaArchivoDestino=RUTA_ARCHIVO_LOG;
    	String dateFormat=FECHA_VALOR_DEFAULT;

        for(String formatUnit:formatList){
        	if(formatUnit.startsWith(OPERADOR_PATRON)==false) System.out.println("cargo literal: "+formatUnit);
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_FECHA)) System.out.println("cargo valor fecha: "+ (dateFormat=formatUnit.substring(3, dateFormat.length()-1)) );
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_NIVEL)) System.out.println("cargo %p");
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_HILO)) System.out.println("cargo %t");
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_MENSAJE)) System.out.println("cargo %m");
        	else if(formatUnit.startsWith(OPERADOR_PATRON+OPERADOR_PATRON)) System.out.println("cargo caracter: %");
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_SEPARADOR)) System.out.println("cargo separador");
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_LINEA)) System.out.println("cargo %L");
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_ARCHIVO)) System.out.println("cargo %F");
        	else if(formatUnit.startsWith(OPERADOR_PATRON+INDICADOR_METODO)) System.out.println("cargo %M");
         }

        // cargarFormatoHora()
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        System.out.println( simpleDateFormat.format(new Date()) );


    }
}
