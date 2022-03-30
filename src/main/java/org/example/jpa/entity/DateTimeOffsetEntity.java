package org.example.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class DateTimeOffsetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(columnDefinition = "DATETIMEOFFSET", nullable = false)
    private OffsetDateTime value;
}
