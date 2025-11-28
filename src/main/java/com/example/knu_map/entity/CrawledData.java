package com.example.knu_map.entity;

import com.example.knu_map.domain.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class CrawledData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;
    private Integer date;
    private String link;

    public CrawledData(String title, Category category, Integer date, String link) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.link = link;
    }

}
