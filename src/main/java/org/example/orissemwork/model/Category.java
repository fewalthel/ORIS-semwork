package org.example.orissemwork.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    private Integer id;
    private String name;

    public Integer getId() { return id; }

    public String getName() { return name; }
}