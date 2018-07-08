package org.morozov.monitoring.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * Better practice to use {@link java.util.UUID} as id,
     * with mapping it on some type of db (e.g. PostgreSQL has a special uuid type).
     * For simplicity we shall use Long as id type, with auto generation of values.
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
