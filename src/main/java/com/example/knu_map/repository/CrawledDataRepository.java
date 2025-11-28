package com.example.knu_map.repository;

import com.example.knu_map.domain.Category;
import com.example.knu_map.entity.CrawledData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CrawledDataRepository extends JpaRepository<CrawledData, Long> {
    List<CrawledData> findByCategory(Category category);
}
