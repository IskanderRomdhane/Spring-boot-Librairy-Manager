package com.example.demo;

import java.util.Date;

record NewBookRequest(
        String name,
        String publisher,
        Date published_date,
        Integer stock,
        String description
){}
