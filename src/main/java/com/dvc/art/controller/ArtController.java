package com.dvc.art.controller;

import com.dvc.art.controller.dto.*;
import com.dvc.art.controller.feign.*;
import com.dvc.art.model.enums.*;
import com.dvc.art.model.jpa.*;
import com.dvc.art.service.impl.ArtServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ArtController {

    private final ArtServiceImpl artService;

    @Autowired
    private final ArtstyleClient artstyleClient;

    @Autowired
    private final UserClient userClient;

    @Autowired
    private final AuthorClient authorClient;

    @GetMapping
    public ResponseEntity<Page<Art>> index(Pageable pageable) {
        final Page<Art> arts = artService.fetchAll(pageable);

        return ResponseEntity.ok(arts);
    }

    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable long id) {
        try {
            final Art art = artService.fetchArtById(id);

            return ResponseEntity.ok(art);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ArtPostDTO artRequest) {

        final String title = artRequest.getTitle();
        final Optional<String> description = Optional.ofNullable(artRequest.getDescription());
        final Optional<ArtCategory> artCategory = Optional.ofNullable(artRequest.getCategory());
        final Optional<ArtType> artType = Optional.ofNullable(artRequest.getType());
        final Optional<ArtStatus> artStatus = Optional.ofNullable(artRequest.getStatus());


        try {
            User user = userClient.findById(artRequest.getOwnerId());
            Art art = new Art(title, user);

            Optional<Long> maybeAuthorId = Optional.ofNullable(artRequest.getAuthorId());
            if (maybeAuthorId.isPresent()) { // id was provided, skip assigning if it was not
                Optional<Author> author = Optional.ofNullable(authorClient.findById(maybeAuthorId.get()));
                author.ifPresent(art::setAuthor);

                if (author.isEmpty()) throw new IllegalArgumentException("The author with such ownerId was not found");
            }
            Optional<Long> maybeArtstyleId = Optional.ofNullable(artRequest.getArtstyleId());
            if (maybeArtstyleId.isPresent()) {
                Optional<Artstyle> artstyle = Optional.ofNullable(artstyleClient.findById(maybeArtstyleId.get()));
                artstyle.ifPresent(art::setArtstyle);

                if (artstyle.isEmpty()) throw new IllegalArgumentException("The artstyle with such artstyleId was not found");
            }

            description.ifPresent(art::setDescription);
            artCategory.ifPresent(art::setCategory);
            artType.ifPresent(art::setType);
            artStatus.ifPresent(art::setStatus);

            final long createdArtId = artService.createArt(art);
            final String artUri = String.format("/arts/%d", createdArtId);

            return ResponseEntity.created(URI.create(artUri)).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @Valid @RequestBody ArtPatchDTO artRequest) {

        final Optional<String> title = Optional.ofNullable(artRequest.getTitle());
        final Optional<String> description = Optional.ofNullable(artRequest.getDescription());
        final Optional<ArtCategory> artCategory = Optional.ofNullable(artRequest.getCategory());
        final Optional<ArtType> artType = Optional.ofNullable(artRequest.getType());
        final Optional<ArtStatus> artStatus = Optional.ofNullable(artRequest.getStatus());

        try { // TODO: messy af
            Art art = new Art();

            Optional<Long> maybeOwnerId = Optional.ofNullable(artRequest.getOwnerId());
            if (maybeOwnerId.isPresent()) { // id was provided, skip assigning if it was not
                Optional<User> author = Optional.ofNullable(userClient.findById(maybeOwnerId.get()));
                author.ifPresent(art::setOwner);

                if (author.isEmpty()) throw new IllegalArgumentException("The user with such ownerId was not found");
            }
            Optional<Long> maybeAuthorId = Optional.ofNullable(artRequest.getAuthorId());
            if (maybeAuthorId.isPresent()) { // id was provided, skip assigning if it was not
                Optional<Author> author = Optional.ofNullable(authorClient.findById(maybeAuthorId.get()));
                author.ifPresent(art::setAuthor);

                if (author.isEmpty()) throw new IllegalArgumentException("The author with such authorId was not found");
            }
            Optional<Long> maybeArtstyleId = Optional.ofNullable(artRequest.getArtstyleId());
            if (maybeArtstyleId.isPresent()) {
                Optional<Artstyle> artstyle = Optional.ofNullable(artstyleClient.findById(maybeArtstyleId.get()));
                artstyle.ifPresent(art::setArtstyle);

                if (artstyle.isEmpty()) throw new IllegalArgumentException("The artstyle with such artstyleId was not found");
            }


            title.ifPresent(art::setTitle);
            description.ifPresent(art::setDescription);
            artCategory.ifPresent(art::setCategory);
            artType.ifPresent(art::setType);
            artStatus.ifPresent(art::setStatus);


            artService.updateArtById(id, art);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            artService.deleteArtById(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
