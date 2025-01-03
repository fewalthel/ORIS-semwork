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

  /*  public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }*/

    public Integer getId() { return id; }

    public String getName() { return name; }
}
