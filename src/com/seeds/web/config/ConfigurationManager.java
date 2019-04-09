package com.seeds.web.config;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seeds.web.utils.CookieManager;

/**
 * En lugar de como clase utilidad, se ha implementado como Singleton 
 * por si es preciso en el futuro para diversas estrategias de 
 * configuración (JNDI, XML, Spring...),
 * o para fragmentar configuracion, etc. etc.  
 */
public final class ConfigurationManager {
	
	private static Logger logger = LogManager.getLogger(ConfigurationManager.class.getName());
	
    private static final String WEB_CONFIGURATION_FILE =
        "WebConfiguration.properties";
    
    private static Map parameters;

    static {
        try {
            Class configurationParametersManagerClass = ConfigurationManager.class;
            ClassLoader classLoader = configurationParametersManagerClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(WEB_CONFIGURATION_FILE);
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            
            parameters = Collections.synchronizedMap(properties);
            
        } catch (Throwable t) {
        	logger.fatal(t.getMessage(), t);      
        }

    }

    private static ConfigurationManager instance = null;
    
    /**
     * Singleton Thread-Safe.
     */
    public static ConfigurationManager getInstance() {
    	if (instance == null) {
    		synchronized(ConfigurationManager.class) {
    			if (instance == null) { // Necesario para proteger una segunda instanciación
    				instance = new ConfigurationManager();
    			}
    		}
    	}
    	return instance;    	
    }
    
    private ConfigurationManager() {    	
    };

    /**
     * Obtiene el valor de un parámetro de configuración.
     * @param name Nombre del parámetro.
     * @return Valor del parámetro o null si no se ha encontrado.
     */
    public String getParameter(String name) {
        String value = (String) parameters.get(name);       
        return value;
    }
    
    
    /**
     * Para parametros que primero se buscan en una cookie, 
     * y si no, en el fichero.
     */
    public String getParameter(HttpServletRequest request, String name) {
    	Cookie c = CookieManager.getCookie(request, name);
    	if (c!=null) {
    		return c.getValue();
    	} else {
    		return getParameter(name);
    	}
    }
}
