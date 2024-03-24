package org.itstep.api.repositories;

import org.itstep.api.models.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface fileRepository extends JpaRepository<FileModel, Long>
{
}
