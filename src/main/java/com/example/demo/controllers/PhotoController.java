package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Photo;
import com.example.demo.repositories.PhotoRepository;

@Controller
@RequestMapping
public class PhotoController {
    
    @Autowired
    private PhotoRepository repo;

    @GetMapping(path="/photo/{photoId}", produces=MediaType.ALL_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getPhotos(@PathVariable Integer photoId) {
        Optional<Photo> opt = repo.findPhotoByID(photoId);
        if (opt.isEmpty())
            return ResponseEntity.notFound().build();
        Photo p = opt.get();
        return ResponseEntity.status(200)
                .header("Content-Type", p.contentType())
                //.header("Content-Length", p.content().length)
                .body(p.content());
    }
}
