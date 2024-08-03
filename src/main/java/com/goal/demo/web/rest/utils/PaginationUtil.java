package com.goal.demo.web.rest.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class PaginationUtil {

    private PaginationUtil() {}

    public static HttpHeaders generatePaginationHttpHeaders(UriComponentsBuilder uriBuilder, Page<?> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        headers.add("X-Page-Size", Integer.toString(page.getSize()));
        headers.add("X-Page-Number", Integer.toString(page.getNumber()));
        headers.add("X-Total-Pages", Integer.toString(page.getTotalPages()));
        return headers;
    }
}

