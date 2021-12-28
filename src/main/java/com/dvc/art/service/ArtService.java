package com.dvc.art.service;

import com.dvc.art.model.jpa.Art;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("art-service")
public interface ArtService {

    Page<Art> fetchAll(Pageable pageable);
    List<Art> fetchAll(Art art, Pageable pageable);
    Art fetchArtById(long id) throws IllegalArgumentException;
    long createArt(Art art) throws IllegalArgumentException;
    void deleteArtById(long id) throws IllegalArgumentException;
    void updateArtById(long id, Art art) throws IllegalArgumentException;
}
