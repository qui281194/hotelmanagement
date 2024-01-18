/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package fpt.aptech.hotelapi.controller;

import fpt.aptech.hotelapi.models.Blog;
import fpt.aptech.hotelapi.models.Room;
import fpt.aptech.hotelapi.service.BlogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api/blogcontroller")
public class BlogRestController {
     @Autowired
    private BlogService blogService;

    // Endpoint để lấy danh sách tất cả bài đăng Blog
    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.findAll();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRoom() {
        List<Room> rooms = blogService.findAllRoom();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Endpoint để lấy thông tin một bài đăng Blog theo ID
    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Integer id) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint để tạo mới một bài đăng Blog
    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.create(blog);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    // Endpoint để cập nhật thông tin một bài đăng Blog theo ID
    @PutMapping("/blogs/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Integer id, @RequestBody Blog updatedBlog) {
        Blog updated = blogService.update(id, updatedBlog);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint để xóa một bài đăng Blog theo ID
    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer id) {
        blogService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint để tìm kiếm bài đăng Blog theo tiêu đề
    @GetMapping("/blogs/search")
    public ResponseEntity<List<Blog>> searchBlogsByTitle(@RequestParam String title) {
        List<Blog> blogs = blogService.searchByTitle(title);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}
