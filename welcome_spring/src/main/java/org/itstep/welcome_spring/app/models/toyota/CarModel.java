package org.itstep.welcome_spring.app.models.toyota;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.itstep.welcome_spring.app.models.BaseEntity;
import org.itstep.welcome_spring.app.models.auth.Admin;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "toyota_car_models")
public class CarModel  extends BaseEntity {
    private String thumb;
}
