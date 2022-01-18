package com.chs.boot.common.constant;

public enum SystemConstant {
    BOF("BOF","http://127.0.0.1:7070","/esp/bof");

    public String getSystemCode() {
        return systemCode;
    }

    public String getLocalBaseURL() {
        return localBaseURL;
    }

    public String getLocalUrlContext() {
        return localUrlContext;
    }

    private String systemCode;
    private String localBaseURL;
    private String localUrlContext;


    SystemConstant(String systemCode, String localBaseURL, String localUrlContext) {
        this.systemCode = systemCode;
        this.localBaseURL = localBaseURL;
        this.localUrlContext = localUrlContext;
    }
}
