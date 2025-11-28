package com.example.knu_map.service;

import com.example.knu_map.dto.CrawledDataForm;
import com.example.knu_map.entity.CrawledData;
import com.example.knu_map.repository.CrawledDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    private CrawledDataRepository repository;

    @Autowired
    public MapService(CrawledDataRepository repository) {
        this.repository = repository;
    }

    public void save(CrawledDataForm dto) {
        CrawledData crawledData = dto.toEntity();
        repository.save(crawledData);
    }

    public List<CrawledData> getCrawledDataByCategory(String category) {
        return repository.findByCategory(category);
    }

}
