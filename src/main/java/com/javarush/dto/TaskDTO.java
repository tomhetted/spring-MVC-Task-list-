package com.javarush.dto;

import com.javarush.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

    private String description;
    private Status status;
}
