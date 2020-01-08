package com.example.persistence.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * An entity that holds an id. Extending classes
 * will inherit id capabilities.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@MappedSuperclass
public abstract class IdEntity extends TimestampEntity {
    private static final long serialVersionUID = 1947226630383174770L;

    /**
     * The id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdEntity idEntity = (IdEntity) o;
        return Objects.equal(id, idEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("id", id)
                .toString();
    }
}
