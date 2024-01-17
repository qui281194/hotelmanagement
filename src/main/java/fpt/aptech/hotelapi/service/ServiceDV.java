/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package fpt.aptech.hotelapi.service;

import fpt.aptech.hotelapi.dto.ServiceCategoryDto;
import fpt.aptech.hotelapi.dto.ServicedvDto;
import fpt.aptech.hotelapi.models.ServiceCategory;
import fpt.aptech.hotelapi.models.Servicedv;
import fpt.aptech.hotelapi.repository.ServiceCategoryRepository;
import fpt.aptech.hotelapi.repository.ServicedvRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ServiceDV {

   @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    private ServicedvRepository servicedvRepository;
    @PersistenceContext
    private EntityManager entity;

    // Lấy danh sách tất cả các loại dịch vụ
    public List<ServiceCategoryDto> getAllServiceCategories() {
        List<ServiceCategory> categories = serviceCategoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm loại dịch vụ theo tên (không phân biệt chữ hoa/chữ thường)
    public List<ServiceCategory> searchServiceCategoriesByName(String name) {
        return serviceCategoryRepository.findByName(name);
    }

    // Tạo mới một loại dịch vụ
    public ServiceCategoryDto createServiceCategory(ServiceCategoryDto categoryDto) {
        ServiceCategory category = convertToEntity(categoryDto);
        ServiceCategory savedCategory = serviceCategoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    
    // Phương thức chuyển đổi entity thành DTO cho ServiceCategory
    private ServiceCategoryDto convertToDto(ServiceCategory category) {
        ServiceCategoryDto categoryDto = new ServiceCategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }

    // Phương thức chuyển đổi DTO thành entity cho ServiceCategory
    private ServiceCategory convertToEntity(ServiceCategoryDto categoryDto) {
        ServiceCategory category = new ServiceCategory();
        BeanUtils.copyProperties(categoryDto, category);
        return category;
    }

    

    

    // Xóa một loại dịch vụ dựa trên ID
    public void deleteServiceCategory(Integer id) {
        serviceCategoryRepository.deleteById(id);
    }
     @Transactional
    public int deleteConfig(List<Integer>codeList){
    
         Query query = entity.createQuery("DELETE FROM ServiceCategory p WHERE p.id IN (:codeList)");
                query.setParameter("codeList", codeList);
                return query.executeUpdate();
    }
    public ServiceCategoryDto findByIdCategory(Integer id) {
    return serviceCategoryRepository.findById(id)
            .map(this::convertToDto)
            .orElse(null); // Hoặc bạn có thể throw một exception nếu muốn
}

    

    // Sửa thông tin một loại dịch vụ
    public ServiceCategoryDto updateServiceCategory(Integer id, ServiceCategoryDto updatedCategoryDto) {
        // Kiểm tra xem loại dịch vụ có tồn tại hay không
        ServiceCategory existingCategory = serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại dịch vụ"));

        // Cập nhật thông tin loại dịch vụ
        BeanUtils.copyProperties(updatedCategoryDto, existingCategory);
        ServiceCategory savedCategory = serviceCategoryRepository.save(existingCategory);

        // Chuyển đổi và trả về DTO của loại dịch vụ đã được cập nhật
        return convertToDto(savedCategory);
    }

    // Xóa một dịch vụ dựa trên ID
    public void deleteService(Integer serviceId) {
        servicedvRepository.deleteById(serviceId);
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
    
    
}

