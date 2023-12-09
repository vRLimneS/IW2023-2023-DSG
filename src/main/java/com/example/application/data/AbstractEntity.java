package com.example.application.data;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Version
    private int version;

    public UUID getId() {return id;}
    public void setId(UUID id) {
        this.id = id;
    }
    public int getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity that)) {
            return false; // null or not an AbstractEntity class
        }
        if (getId() != null) {
            return getId().equals(that.getId());
        }
        return super.equals(that);
    }
}