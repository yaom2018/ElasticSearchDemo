package com.xproduct.esdemo.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;

public class Configuration {
    private  static final String CONF_FILE_PATH = "Es_config.xml";
    private EsSources esSources;
    private static XMLConfiguration config;
    private static Configuration instance = new Configuration();

    public Configuration() {
        esSources = new EsSources();
        this.init();
    }
    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public boolean init() {
        boolean result = false;
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(CONF_FILE_PATH);
            System.out.println("the load path is " + CONF_FILE_PATH);
            if (in == null) {
                System.out.println("the load path is Null");
                return false;
            }
            config = new XMLConfiguration();
            config.load(in);
            esSources.init();
            result = true;
        } catch(ConfigurationException e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }

    public EsSources getEsSources() {
        return esSources;
    }

    public class EsSources {
        private String esURL;
        private String esIndexName;
        private String esTyepName;


        public static final String ES_FILE_CONFIG = "es-file-config";

        public static final String ES_URL = ES_FILE_CONFIG + ".server-url";
        public static final String ES_INDEX = ES_FILE_CONFIG + ".index-name";
        public static final String ES_TYPE_NAME = ES_FILE_CONFIG + ".type-name";

        public void init() throws ConfigurationException {
            boolean isGood = true;
            esURL = config.getString(ES_URL);
            esIndexName = config.getString(ES_INDEX);
            esTyepName = config.getString(ES_TYPE_NAME);
            System.out.println("url ="+esURL);
            if (StringUtils.isBlank(esURL)||StringUtils.isBlank(esIndexName)||StringUtils.isBlank(esTyepName)){
                isGood = false;
            }
            if (!isGood) {
                System.err.println("No get paramete");
            }
        }



        public String getEsURL() {
            return esURL;
        }

        public void setEsURL(String esURL) {
            this.esURL = esURL;
        }

        public  String getEsIndexName() {
            return  esIndexName;
        }

        public void setEsIndexName(String esIndexName) {
            this.esIndexName = esIndexName;
        }

        public String getEsTyepName() {
            return esTyepName;
        }

        public void setEsTyepName(String esTyepName) {
            this.esTyepName = esTyepName;
        }

    }

}
