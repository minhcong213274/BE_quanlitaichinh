package com.example.quanlitaichinh.controller;

import com.example.quanlitaichinh.model.Detail;
import com.example.quanlitaichinh.service.detail.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detail")
@RequiredArgsConstructor
@CrossOrigin("*")
public class detailController {
    @Autowired
    private DetailService detailService;
    @GetMapping
    public ResponseEntity<List<Detail>> getAllDetail(){
        return new ResponseEntity<>((List<Detail>) detailService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Detail> createDetail(@RequestBody Detail detail) {
        Detail isSuccessfullyCreated = detailService.createDetail(detail);
        if (isSuccessfullyCreated != null) return new ResponseEntity<>(isSuccessfullyCreated, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
