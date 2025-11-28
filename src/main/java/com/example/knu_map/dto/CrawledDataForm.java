package com.example.knu_map.dto;

import com.example.knu_map.domain.Category;
import com.example.knu_map.entity.CrawledData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CrawledDataForm {

    private Category category;
    private String title;
    private Integer date;
    private String link;

    public CrawledData toEntity(){
        return new CrawledData(title,category,date,link);
    }
}
