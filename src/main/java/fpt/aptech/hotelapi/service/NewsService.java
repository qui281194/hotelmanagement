/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.models.News;
import fpt.aptech.hotelapi.repository.NewsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class NewsService {
    private NewsRepository _newsRepo;
    
    @Autowired
    public NewsService(NewsRepository _newsRepo){
        this._newsRepo= _newsRepo;
    }
    
    
    public List<News> findAllNews() {
        return _newsRepo.findAll();
    }

    
    public News findByIdNews(int id) {
        return _newsRepo.findById(id).get();
    }

    
    public News save(News news) {
        return _newsRepo.save(news);
    }

    
    public void deleteById(int id) {
        News news = _newsRepo.findById(id).get();
        _newsRepo.delete(news);
    }
}
