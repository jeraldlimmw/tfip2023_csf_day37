package com.example.demo.repositories;

public class Queries {
    
    public static final String SQL_SAVE_PHOTO = """
            insert into photos(title, media_type, content) values (?, ?, ?)
            """;

    public static final String SQL_FIND_PHOTO_BY_ID = """
            select * from photos where m_id = ?
            """;
}
