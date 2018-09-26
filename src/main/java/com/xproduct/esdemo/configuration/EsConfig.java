package com.xproduct.esdemo.configuration;

import com.xproduct.esdemo.esdatabase.EsClientParameter;

public class EsConfig {
    /**
     * get parameter of es configuration and connection with client
     */
    public  static  void conEsClient() {
        // ES server url
        String esURL = Configuration.getInstance().getEsSources().getEsURL();
        String esIndexName = Configuration.getInstance().getEsSources().getEsIndexName();
        String esTypeName =  Configuration.getInstance().getEsSources().getEsTyepName();
        EsClientParameter.setEsURL(esURL);
        EsClientParameter.setEsIndexName(esIndexName);
        EsClientParameter.setEsTypeName(esTypeName);

    }
}
