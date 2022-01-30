package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlertType {

    NONE("none"),
    ERROR("danger"),
    SUCCES("primary");

    private String cssClass;
}
