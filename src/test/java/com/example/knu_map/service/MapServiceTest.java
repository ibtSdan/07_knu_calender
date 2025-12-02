package com.example.knu_map.service;

import com.example.knu_map.domain.Category;
import com.example.knu_map.dto.CrawledDataForm;
import com.example.knu_map.entity.CrawledData;
import com.example.knu_map.repository.CrawledDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MapServiceTest {

    @Mock
    private CrawledDataRepository repository;

    @InjectMocks
    private MapService service;

    @Test
    void save_호출시_repository_save_호출() {
        CrawledDataForm form = new CrawledDataForm(Category.NOTICE, "title", 20251202, "url");

        service.save(form);

        verify(repository).save(any(CrawledData.class));
    }

    @Test
    void 카테고리_조회시_repository_findByCategory_호출되고_값반환() {
        Category category = Category.NOTICE;
        List<CrawledData> mockData = List.of(new CrawledData());

        when(repository.findByCategory(category)).thenReturn(mockData);
        List<CrawledData> result = service.getCrawledDataByCategory(category);

        Assertions.assertThat(result).isEqualTo(mockData);
        verify(repository).findByCategory(category);
    }

}
