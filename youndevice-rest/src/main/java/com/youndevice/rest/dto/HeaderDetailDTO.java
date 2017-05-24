package com.youndevice.rest.dto;


import java.util.Map;

public class HeaderDetailDTO {

    private static final String X_AUTH_TOKEN_KEY_NAME = "X-AUTH-TOKEN";
    private static final String CONTENT_TYPE_KEY_NAME = "Content-Type";

    private String xAuthToken;
    private String applicationType;
    private String contentType;

    public static HeaderDetailDTO getInstance(Map<String, String> headerDetailAsMap) {
        HeaderDetailDTO headerDetailDTO = new HeaderDetailDTO();
        headerDetailDTO.xAuthToken = headerDetailAsMap.get(X_AUTH_TOKEN_KEY_NAME);
        headerDetailDTO.contentType = headerDetailAsMap.get(CONTENT_TYPE_KEY_NAME);
        return headerDetailDTO;
    }


    public String getxAuthToken() {
        return xAuthToken;
    }

    public void setxAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
