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

    // Lấy danh sách tất cả các dịch vụ
    public List<ServicedvDto> getAllServices() {
        List<Servicedv> services = servicedvRepository.findAll();
        return services.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Tạo mới một dịch vụ
    public ServicedvDto createService(ServicedvDto serviceDto) {
        Servicedv service = convertToEntity(serviceDto);
        Servicedv savedService = servicedvRepository.save(service);
        return convertToDto(savedService);
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

    // Phương thức chuyển đổi entity thành DTO cho Servicedv
    private ServicedvDto convertToDto(Servicedv service) {
        ServicedvDto serviceDto = new ServicedvDto();
        BeanUtils.copyProperties(service, serviceDto);
        // Chuyển đổi ServiceCategory thành ServiceCategoryDto
        serviceDto.setCategory(convertToDto(service.getCategory()));
        return serviceDto;
    }

    // Phương thức chuyển đổi DTO thành entity cho Servicedv
    private Servicedv convertToEntity(ServicedvDto serviceDto) {
        Servicedv service = new Servicedv();
        BeanUtils.copyProperties(serviceDto, service);
        // Chuyển đổi ServiceCategoryDto thành ServiceCategory
        service.setCategory(convertToEntity(serviceDto.getCategory()));
        return service;
    }
    // Xóa một loại dịch vụ dựa trên ID
    public void deleteServiceCategory(Integer categoryId) {
        serviceCategoryRepository.deleteById(categoryId);
    }

    // Sửa thông tin một loại dịch vụ
    public ServiceCategoryDto updateServiceCategory(Integer categoryId, ServiceCategoryDto updatedCategoryDto) {
        // Kiểm tra xem loại dịch vụ có tồn tại hay không
        ServiceCategory existingCategory = serviceCategoryRepository.findById(categoryId)
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

    // Sửa thông tin một dịch vụ
    public ServicedvDto updateService(Integer serviceId, ServicedvDto updatedServiceDto) {
        // Kiểm tra xem dịch vụ có tồn tại hay không
        Servicedv existingService = servicedvRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dịch vụ"));

        // Cập nhật thông tin dịch vụ
        BeanUtils.copyProperties(updatedServiceDto, existingService);
        Servicedv savedService = servicedvRepository.save(existingService);

        // Chuyển đổi và trả về DTO của dịch vụ đã được cập nhật
        return convertToDto(savedService);
    }

    // Tìm kiếm dịch vụ theo tên
    public List<ServicedvDto> searchServicesByName(String name) {
        List<Servicedv> services = servicedvRepository.findByNameIgnoreCaseContaining(name);
        return services.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    
}

