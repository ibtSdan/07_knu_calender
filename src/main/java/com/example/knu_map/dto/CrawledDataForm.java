package com.example.knu_map.dto;

import com.example.knu_map.domain.Category;
import com.example.knu_map.entity.CrawledData;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class CrawledDataForm {

    @NotNull(message = "카테고리를 선택해주세요.")
    private Category category;

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 200, message = "제목은 최대 200자까지 가능합니다.")
    private String title;

    @NotNull(message = "날짜를 입력해주세요.")
    @Min(value = 19000101L, message = "날짜 형식이 올바르지 않습니다.")
    @Max(value = 99991231L, message = "날짜 형식이 올바르지 않습니다.")
    private Integer date;

    @NotBlank(message = "링크를 입력해주세요.")
    @Size(max = 1000, message = "링크 길이는 최대 1000자까지 가능합니다.")
    @Pattern(
            regexp = "^(https?://).+",
            message = "링크는 http:// 또는 https://로 시작해야 합니다."
    )
    private String link;

    public CrawledData toEntity(){
        return new CrawledData(title,category,date,link);
    }
}
