package com.example.demo.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repositories.PhotoRepository;

@Service
public class PhotoService {
    
    @Autowired
    private PhotoRepository repo;

    public boolean upload(String title, MultipartFile multipart) throws IOException {
        try {
            return repo.save(title, multipart.getContentType(), multipart.getBytes());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }


}
