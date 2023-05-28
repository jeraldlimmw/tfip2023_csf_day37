package com.example.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Photo;

import static com.example.demo.repositories.Queries.*;

import java.util.Optional;

@Repository
public class PhotoRepository {
    
    @Autowired
    private JdbcTemplate template;

    public boolean save(String title, String contentType, byte[] content) {
        return template.update(SQL_SAVE_PHOTO, title, contentType, content) > 0;
    }

    public Optional<Photo> findPhotoByID(Integer photoId) {
        // byte[] cannot use rowset. Instead use ResultSetExtractor
        Optional<Photo> opt = template.query(SQL_FIND_PHOTO_BY_ID,
            rs -> {
                if (!rs.next())
                    return Optional.empty();
                Photo p = new Photo(photoId, rs.getString("title")
                    , rs.getString("media_type"), rs.getBytes("content"));
                return Optional.of(p);
            },
            photoId);

        return opt;
    }
}
