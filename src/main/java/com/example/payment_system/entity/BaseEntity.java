package com.example.payment_system.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    private UUID id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    @PrePersist
    public void prePersist(){
        if(id == null){
            id = UUID.randomUUID();
        }
        createDateTime = LocalDateTime.now();
        updateDateTime = createDateTime;
    }

    @PreUpdate
    public void preUpdate(){
        updateDateTime = LocalDateTime.now();
    }
}
