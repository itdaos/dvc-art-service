package com.dvc.art.service.impl;

import com.dvc.art.model.jpa.Art;
import com.dvc.art.model.repo.ArtRepo;
import com.dvc.art.service.ArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArtServiceImpl implements ArtService {

    @Autowired
    private ArtRepo artRepo;

    @Override
    public Page<Art> fetchAll(Pageable pageable) {
        return artRepo.findAll(pageable);
    }

    @Override
    public List<Art> fetchAll(Art art, Pageable pageable) {
        return artRepo.findAll(Example.of(art));
    }

    @Override
    public Art fetchArtById(long id) throws IllegalArgumentException {
        Optional<Art> maybeArt = artRepo.findById(id);

        if (maybeArt.isEmpty()) throw new IllegalArgumentException("The art object with such id was not found");
        return maybeArt.get();
    }

    @Override
    public long createArt(Art art) throws IllegalArgumentException {
        try {
            Art savedArt = artRepo.save(art);
            return savedArt.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void deleteArtById(long id) throws IllegalArgumentException {
        Optional<Art> maybeArt = artRepo.findById(id);

        if (maybeArt.isEmpty()) throw new IllegalArgumentException("The art object with such id was not found");
        artRepo.deleteById(id);
    }

    @Override
    public void updateArtById(long id, Art art) throws IllegalArgumentException {
        Optional<Art> maybeArt = artRepo.findById(id);

        if (maybeArt.isEmpty()) throw new IllegalArgumentException("The art object with such id was not found");
        Art toBeSaved = maybeArt.get();

        // fix me pls
        Optional.of(art.getOwner()).ifPresent(toBeSaved::setOwner);
        Optional.of(art.getTitle()).ifPresent(toBeSaved::setTitle);

        Optional.ofNullable(art.getArtstyle()).ifPresent(toBeSaved::setArtstyle);
        Optional.ofNullable(art.getAuthor()).ifPresent(toBeSaved::setAuthor);
        Optional.ofNullable(art.getCategory()).ifPresent(toBeSaved::setCategory);
        Optional.ofNullable(art.getType()).ifPresent(toBeSaved::setType);
        Optional.ofNullable(art.getStatus()).ifPresent(toBeSaved::setStatus);
        Optional.ofNullable(art.getDescription()).ifPresent(toBeSaved::setDescription);

        toBeSaved.setId(id);
        artRepo.save(toBeSaved);
    }
}
