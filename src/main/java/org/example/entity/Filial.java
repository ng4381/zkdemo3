package org.example.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "filial")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @NotNull
    @Column(name = "filialName", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;
}
