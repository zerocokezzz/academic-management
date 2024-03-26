package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Curriculum {

    @Id
    private String name;

    @ManyToOne
    private Category category;

    private int time;
    private int day;

}
