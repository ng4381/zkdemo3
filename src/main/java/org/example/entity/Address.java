package org.example.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;


    @Column(name = "zip")
    private String zip;

    @NotEmpty
    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty
    @NotNull
    @Column(name = "street", nullable = false)
    private String street;


    @NotNull
    @Column(name = "house", nullable = false)
    private Integer house;

    @Column(name = "flat")
    private Integer flat;

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", zip, city, street, house, flat != null ? flat : "");
    }
}
