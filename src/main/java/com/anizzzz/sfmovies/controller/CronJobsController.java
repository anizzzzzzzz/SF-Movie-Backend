package com.anizzzz.sfmovies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cron")
public class CronJobsController {
    @GetMapping
    public ResponseEntity<?> alive(){
        return ResponseEntity.ok().build();
    }
}
