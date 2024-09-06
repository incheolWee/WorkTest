package com.example.worktest.work;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkDTO {
    private Long id;
    private Long userId;
    private String name;
    private String path;
    private Integer xSize;
    private Integer ySize;
    private Boolean shared;
    private Boolean trashed;
    private Boolean finish;
}
