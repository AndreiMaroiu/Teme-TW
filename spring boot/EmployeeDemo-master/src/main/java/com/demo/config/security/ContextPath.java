package com.demo.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContextPath {

    BOWER("/bower_components/**"),
    BUILD("/build/**"),
    CUSTOM("/custom/**"),
    DIST("/dist/**"),
    PLUGINS("/plugins/**"),
    FAVICON("/favicon.ico");

    private String contextString;

}
