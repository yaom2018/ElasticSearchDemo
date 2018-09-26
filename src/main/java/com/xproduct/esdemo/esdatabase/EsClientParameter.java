package com.xproduct.esdemo.esdatabase;

import org.apache.commons.lang.StringUtils;

public class EsClientParameter {
    protected static String esURL = null;
    protected static String esIndexName = null;
    protected static String esTypeName = null;

    public static void setEsURL(String esURL) {
        EsClientParameter.esURL = esURL;
    }
    public static void setEsIndexName(String esIndexName) {
        EsClientParameter.esIndexName = esIndexName;
    }
    public static void setEsTypeName(String esTypeName) {
        EsClientParameter.esTypeName = esTypeName;
    }

    protected static boolean checkPara() {
        if(StringUtils.isBlank(esURL)||StringUtils.isBlank(esIndexName)||StringUtils.isBlank(esTypeName)) {
            return false;
        } else {
            return true;
        }
    }

}
