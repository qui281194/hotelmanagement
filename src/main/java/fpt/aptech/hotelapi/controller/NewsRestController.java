/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.models.News;
import fpt.aptech.hotelapi.service.NewsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/api/news")
public class NewsRestController {
    
    @Autowired
    private NewsService _newsService;
    
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<News> function_findAll() {
        return _newsService.findAllNews();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public News function_findById(@PathVariable("id") int id) {
        return _newsService.findByIdNews(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public News function_save(@RequestBody News news) {
        return _newsService.save(news);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public News editNews(@PathVariable int id, @RequestBody News news) {
        news.setId(id);
        return _newsService.save(news);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void function_deleteById(@PathVariable("id") Integer id) {
        _newsService.deleteById(id);
    }
    
}
