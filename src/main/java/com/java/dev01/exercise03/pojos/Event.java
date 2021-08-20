package com.java.dev01.exercise03.pojos;

import java.io.Serializable;
import java.util.Date;
import lombok.*;

@Setter @Getter
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Date dateStart;
    private int duration;
}