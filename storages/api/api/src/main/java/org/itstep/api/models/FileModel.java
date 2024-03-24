package org.itstep.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileModel {
    @Id
    private Long id;

    private long size;

    private String httpContentType;
}
