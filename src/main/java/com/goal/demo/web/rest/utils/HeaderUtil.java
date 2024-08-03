package com.goal.demo.web.rest.utils;

import org.springframework.http.HttpHeaders;

public class HeaderUtil {
    private HeaderUtil() {}

    public static HttpHeaders createEntityCreationAlert(String applicationName, String entityName, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Resource", applicationName + "/entities/" + entityName + "_created");
        headers.add("X-Resource-Id", param);
        return headers;
    }

    public static HttpHeaders createEntityUpdateAlert(String applicationName, String entityName, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Resource", applicationName + "/entities/" + entityName + "_updated");
        headers.add("X-Resource-Id", param);
        return headers;
    }

    public static HttpHeaders createEntityDeletionAlert(String applicationName, String entityName, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Resource", applicationName + "/entities/" + entityName + "_deleted");
        headers.add("X-Resource-Id", param);
        return headers;
    }
}

