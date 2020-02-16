package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileProduct {
    private Integer errno;
    private List<String> data;
}
