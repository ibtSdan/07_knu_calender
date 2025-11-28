package com.example.knu_map.controller;

import com.example.knu_map.dto.CrawledDataForm;
import com.example.knu_map.entity.CrawledData;
import com.example.knu_map.repository.CrawledDataRepository;
import com.example.knu_map.service.MapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/knu")

public class MapController {

    private MapService service;

    @Autowired
    public MapController(MapService service) {
        this.service = service;
    }

    @PostMapping("/crawledData")
    public String saveData(@RequestBody CrawledDataForm form){
        log.info("저장합니다.: "+form.getTitle());
        service.save(form);
        return "성공적으로 저장되었습니다.";
    }

    @GetMapping("/crawledData")
    public List<CrawledData> getData(@RequestParam String category) {
        return service.getCrawledDataByCategory(category);
    }
}
