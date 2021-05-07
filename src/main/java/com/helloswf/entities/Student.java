package com.helloswf.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@EqualsAndHashCode()
@Entity
@Table(name = "students",
        uniqueConstraints = @UniqueConstraint(name = "uidx_rn_students", columnNames = "roll_number"),
        indexes = @Index(name = "idx_rn_students", columnList = "roll_number")
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "roll_number")
    private int rollNumber;

    public static Student getDefault() {
        return Student.builder()
                .id(0)
                .firstName("Rohan")
                .lastName("yadav")
                .rollNumber(1234)
                .build();
    }
}
