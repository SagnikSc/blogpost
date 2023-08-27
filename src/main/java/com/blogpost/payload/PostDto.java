package com.blogpost.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message ="Title should be at least 2 chars")
    private String title;

    @NotEmpty
    @Size(min = 6, message ="description should be at least 6 chars")
    private String description;

    @NotEmpty
    private String content;
}
