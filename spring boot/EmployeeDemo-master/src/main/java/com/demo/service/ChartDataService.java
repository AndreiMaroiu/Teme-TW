package com.demo.service;

import com.demo.model.BarDataDto;
import com.demo.model.DonutDataDto;

import java.util.List;

public interface ChartDataService {

    List<DonutDataDto> getDonutData();

    List<BarDataDto> getBarData();
}
