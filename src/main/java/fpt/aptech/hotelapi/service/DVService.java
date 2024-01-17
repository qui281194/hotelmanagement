/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.ServiceCategoryDto;
import fpt.aptech.hotelapi.models.ServiceCategory;
import fpt.aptech.hotelapi.models.Servicedv;
import fpt.aptech.hotelapi.repository.ServiceCategoryRepository;
import fpt.aptech.hotelapi.repository.ServicedvRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class DVService {
     @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    private ServicedvRepository servicedvRepository;
    @PersistenceContext
    private EntityManager entity;

    
    public Servicedv saveImage(Servicedv newServicedv) {
        return servicedvRepository.save(newServicedv);
    }
    public List<Servicedv> findAll() {
        return servicedvRepository.findAll();
    }
     
    // Lấy danh sách tất cả các danh mục
    public List<ServiceCategoryDto> getAllCategories() {
        List<ServiceCategory> categories = serviceCategoryRepository.findAll();
        return mapCategoriesToDto(categories);
    }

    // Chuyển đổi danh sách ServiceCategory thành danh sách ServiceCategoryDto
    private List<ServiceCategoryDto> mapCategoriesToDto(List<ServiceCategory> categories) {
        // Cài đặt logic chuyển đổi ở đây
        // Dưới đây là một ví dụ đơn giản
        return categories.stream()
                .map(category -> new ServiceCategoryDto(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }
    // Xóa dịch vụ theo ID
    public void deleteById(Integer id) {
        servicedvRepository.deleteById(id);
    }
      @Transactional
    public int deleteConfig(List<Integer>codeList){
    
         Query query = entity.createQuery("DELETE FROM Servicedv p WHERE p.id IN (:codeList)");
                query.setParameter("codeList", codeList);
                return query.executeUpdate();
    }
    // Lấy thông tin chi tiết của một dịch vụ
    public Servicedv getServiceDetails(Integer id) {
        Optional<Servicedv> optionalService = servicedvRepository.findById(id);
        return optionalService.orElse(null);
    }
    // Chỉnh sửa thông tin dịch vụ
    public Servicedv editService(Integer id, Servicedv editedService) {
        Optional<Servicedv> optionalService = servicedvRepository.findById(id);
        if (optionalService.isPresent()) {
            Servicedv existingService = optionalService.get();
            BeanUtils.copyProperties(editedService, existingService, "id", "category");
            return servicedvRepository.save(existingService);
        }
        return null;
    }
    // Tìm kiếm dịch vụ theo từ khóa
    public List<Servicedv> searchServices(String name) {
        return servicedvRepository.searchByNameIgnoreCaseContaining(name);
    }
}
