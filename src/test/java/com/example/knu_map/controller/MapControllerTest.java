package com.example.knu_map.controller;

import com.example.knu_map.domain.Category;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

            expectOk(performPost(body));
        }

        @Test
        void Crawled_데이터_정상_조회() throws Exception {
            expectOk(performGet("EVENT"));
        }
    }

    @Nested
    class 실패 {

        @Test
        void 저장_카테고리_없음() throws Exception {
            String body = """
            {
                "title": "테스트 제목",
                "date": 20251201,
                "link": "http://example.com"
            }
            """;

            expectBadRequestMessage(performPost(body), "카테고리를 선택해주세요.");
        }

        @Test
        void 저장_존재하지_않는_카테고리() throws Exception {
            String body = """
            {
                "category": "WRONG",
                "title": "테스트 제목",
                "date": 20251201,
                "link": "http://example.com"
            }
            """;

            expectBadRequestMessage(performPost(body), "필드 타입을 확인해주세요.");
        }

        @Test
        void 저장_제목_없음() throws Exception {
            String body = """
            {
                "category": "EVENT",
                "date": 20251201,
                "link": "http://example.com"
            }
            """;

            expectBadRequestMessage(performPost(body), "제목을 입력해주세요.");
        }

        @Test
        void 저장_제목_200자_초과() throws Exception {
            String longTitle = "*".repeat(201);
            String body = """
            {
                "category": "EVENT",
                "title": "%s",
                "date": 20251201,
                "link": "http://example.com"
            }
            """.formatted(longTitle);

            expectBadRequestMessage(performPost(body), "제목은 최대 200자까지 가능합니다.");
        }

        @Test
        void 저장_날짜_없음() throws Exception {
            String body = """
            {
                "category": "EVENT",
                "title": "테스트 제목",
                "link": "http://example.com"
            }
            """;

            expectBadRequestMessage(performPost(body), "날짜를 입력해주세요.");
        }

        @ParameterizedTest
        @ValueSource(longs = {18991231L, 100000101L})
        void 저장_날짜_초과(long invalidDate) throws Exception {
            String body = """
            {
                "category": "EVENT",
                "title": "테스트 제목",
                "date": "%d",
                "link": "http://example.com"
            }
            """.formatted(invalidDate);

            expectBadRequestMessage(performPost(body), "날짜 형식이 올바르지 않습니다.");
        }

        @Test
        void 저장_링크_없음() throws Exception {
            String body = """
            {
                "category": "EVENT",
                "title": "테스트 제목",
                "date": 20251201
            }
            """;

            expectBadRequestMessage(performPost(body), "링크를 입력해주세요.");
        }

        @Test
        void 저장_링크_1000자_초과() throws Exception {
            String longLink = "http://" + "*".repeat(1001);
            String body = """
            {
                "category": "EVENT",
                "title": "테스트 제목",
                "date": 20251201,
                "link": "%s"
            }
            """.formatted(longLink);

            expectBadRequestMessage(performPost(body), "링크 길이는 최대 1000자까지 가능합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"htt://", "a.com", "://"})
        void 저장_링크_형식_다름(String invalidLink) throws Exception {
            String body = """
            {
                "category": "EVENT",
                "title": "테스트 제목",
                "date": 20251201,
                "link": "%s"
            }
            """.formatted(invalidLink);

            expectBadRequestMessage(performPost(body), "링크는 http:// 또는 https://로 시작해야 합니다.");
        }

        @Test
        void 조회_잘못된_category() throws Exception {
            expectBadRequestMessage(performGet("WRONG"), "요청한 category 값이 올바르지 않습니다. 가능한 값: " + Arrays.toString(Category.values()));
        }
    }

    private ResultActions performPost(String body) throws Exception {
        return mockMvc.perform(
                post("/knu/crawledData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        );
    }

    private ResultActions performGet(String category) throws Exception {
        return mockMvc.perform(
                get("/knu/crawledData")
                        .param("category", category)
        );
    }

    private void expectOk(ResultActions actions) throws Exception {
        actions.andExpect(status().isOk());
    }

    private void expectBadRequestMessage(ResultActions actions, String message) throws Exception {
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(message));
    }
}
