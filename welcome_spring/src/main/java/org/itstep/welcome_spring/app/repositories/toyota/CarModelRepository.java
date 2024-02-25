package org.itstep.welcome_spring.app.repositories.toyota;

import org.itstep.welcome_spring.app.models.toyota.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long>
{
}
