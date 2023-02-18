package org.example.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.zkoss.zk.ui.select.annotation.Listen;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @NotEmpty
    @NotNull
    @Column(name = "companyName", nullable = false)
    private String name;

    @NotNull
    @Column(name = "legalEntityType", nullable = false)
    private LegalEntityType legalEntityType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "company")
    private Set<Filial> filials;
}
