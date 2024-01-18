/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.models.Blog;
import fpt.aptech.hotelapi.models.Room;
import fpt.aptech.hotelapi.repository.BlogRepository;
import fpt.aptech.hotelapi.repository.RoomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class BlogService {
      @Autowired
    private BlogRepository blogRepo;
      @Autowired
      private RoomRepository roomRepo;

    /**
     * Lấy danh sách tất cả bài đăng Blog.
     *
     * @return Danh sách bài đăng Blog.
     */
    public List<Blog> findAll() {
        return blogRepo.findAll();
    }
    public List<Room>findAllRoom(){
        return roomRepo.findAll();
    }

    /**
     * Tìm bài đăng Blog theo ID.
     *
     * @param id ID của bài đăng Blog cần tìm.
     * @return Bài đăng Blog nếu tồn tại, ngược lại trả về null.
     */
    public Blog findById(Integer id) {
        Optional<Blog> blogOptional = blogRepo.findById(id);
        return blogOptional.orElse(null);
    }

    /**
     * Tạo mới một bài đăng Blog.
     *
     * @param blog Thông tin bài đăng Blog cần tạo.
     * @return Bài đăng Blog đã được tạo.
     */
    public Blog create(Blog blog) {
        // Đặt ngày hiện tại làm ngày tạo
        blog.setCreatedAt(new java.util.Date());
        return blogRepo.save(blog);
    }

    /**
     * Cập nhật thông tin của một bài đăng Blog.
     *
     * @param id              ID của bài đăng Blog cần cập nhật.
     * @param updatedBlog     Thông tin bài đăng Blog đã được cập nhật.
     * @return Bài đăng Blog đã được cập nhật, hoặc null nếu không tìm thấy bài đăng.
     */
    public Blog update(Integer id, Blog updatedBlog) {
        Optional<Blog> blogOptional = blogRepo.findById(id);
        if (blogOptional.isPresent()) {
            Blog existingBlog = blogOptional.get();

            // Cập nhật chỉ các thuộc tính không là null
            if (updatedBlog.getTitle() != null) {
                existingBlog.setTitle(updatedBlog.getTitle());
            }
            if (updatedBlog.getContent() != null) {
                existingBlog.setContent(updatedBlog.getContent());
            }
            // Cập nhật các thuộc tính khác theo nhu cầu

            return blogRepo.save(existingBlog);
        } else {
            return null; // Không tìm thấy bài đăng
        }
    }

    /**
     * Xóa một bài đăng Blog theo ID.
     *
     * @param id ID của bài đăng Blog cần xóa.
     */
    public void delete(Integer id) {
        blogRepo.deleteById(id);
    }

    /**
     * Tìm kiếm bài đăng Blog theo tiêu đề.
     *
     * @param title Tiêu đề cần tìm kiếm.
     * @return Danh sách bài đăng Blog thỏa mãn điều kiện tiêu đề.
     */
    public List<Blog> searchByTitle(String title) {
        return blogRepo.findByTitleContaining(title);
    }
    
}
