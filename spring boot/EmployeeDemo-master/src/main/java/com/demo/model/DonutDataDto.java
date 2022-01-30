package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DonutDataDto {

    private static final List<String> COLORS = List.of("#f56954", "#00a65a", "#f39c12", "#00c0ef", "#3c8dbc", "#d2d6de",
            "#EF0070", "#AB0A0A", "#89CB41", "#112F7C", "#7C7C81");

    private String label;
    private int value;
    private String color;

    public DonutDataDto(String label, int value, int index) {
        this.label = label;
        this.value = value;
        this.color = getNextColor(index);
    }

    private String getNextColor(int index) {
        while (index >= COLORS.size()){
            index = index - COLORS.size();
        }
        return COLORS.get(index);
    }

}
