package com.dvc.art.model.repo;

import com.dvc.art.model.jpa.Art;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtRepo extends JpaRepository<Art, Long> {

    Page<Art> findAll(Pageable pageable);

}
