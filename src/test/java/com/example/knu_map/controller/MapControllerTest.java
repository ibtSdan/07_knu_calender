package com.example.knu_map.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MapControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Nested
    class 성공 {

        @Test
        void Crawled_데이터_정상_저장() throws Exception {
            String body = """
            {
                "category": "EVENT",
                "title": "테스트 제목",
                "date": 20251201,
                "link": "http://example.com"
            }
            """;

            mockMvc.perform(
                    post("/knu/crawledData")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body)
            )
                    .andExpect(status().isOk());
        }

        @Test
        void Crawled_데이터_정상_조회() throws Exception {
            mockMvc.perform(
                    get("/knu/crawledData")
                            .param("category", "EVENT")
            )
                    .andExpect(status().isOk());
        }
    }
}
