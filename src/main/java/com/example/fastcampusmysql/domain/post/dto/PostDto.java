package com.example.fastcampusmysql.domain.post.dto;

import java.time.LocalDate;

public record PostDto(
        Long id,
        String contents,
        LocalDate createdDate,
        Long likeCount
) {
}
