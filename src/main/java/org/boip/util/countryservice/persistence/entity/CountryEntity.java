package org.boip.util.countryservice.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "country")
public class CountryEntity implements Persistable<String> {

    @Id
    @Column(name="alpha2code", nullable = false)
    private String alpha2code;

    @Column(name="active", nullable = false)
    private boolean active;

    @Column(name="dutch_name", nullable = false)
    private String dutchName;

    @Column(name="english_name", nullable = false)
    private String englishName;

    @Column(name="french_name", nullable = false)
    private String frenchName;

    @Column(name="creation_date", nullable = false)
    private Date creationDate;

    // stop Hibernate checking for update or insert
    @Transient
    private boolean isNew = false;

    @Override
    public String getId() {
        return alpha2code;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void markNew() {
        this.isNew = true;
    }
}
