package com.example.knu_map.dto;

import com.example.knu_map.domain.Category;
import com.example.knu_map.entity.CrawledData;
import com.example.knu_map.exception.ErrorCode;
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

    @NotNull(message = "{category.required}")
    private Category category;

    @NotBlank(message = "{title.required}")
    @Size(max = 200, message = "{title.too.long}")
    private String title;

    @NotNull(message = "{date.required}")
    @Min(value = 19000101L, message = "{date.invalid}")
    @Max(value = 99991231L, message = "{date.invalid}")
    private Integer date;

    @NotBlank(message = "{link.required}")
    @Size(max = 1000, message = "{link.too.long}")
    @Pattern(
            regexp = "^(https?://).+",
            message = "{link.invalid}"
    )
    private String link;

    public CrawledData toEntity(){
        return new CrawledData(title,category,date,link);
    }
}
