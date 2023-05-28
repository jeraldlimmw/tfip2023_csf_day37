package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.PhotoService;

@Controller
@RequestMapping
public class UploadController {
    
    @Autowired
    private PhotoService svc;
    
    @PostMapping(path="/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postUpload(@RequestPart String title, 
            @RequestPart MultipartFile mypic, Model model) throws IOException {
        
        boolean result = svc.upload(title, mypic);
        
        model.addAttribute("filename", mypic.getOriginalFilename());
        model.addAttribute("size", mypic.getSize());
        model.addAttribute("contentType", mypic.getContentType());
        model.addAttribute("uploaded", result);
        return "uploaded";
    }

}
