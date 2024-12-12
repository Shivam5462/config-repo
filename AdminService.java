package com.coforge.finance.service;

import com.coforge.finance.model.Admin;
import com.coforge.finance.repository.AdminRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
@Transactional
public class AdminService {
 
    @Autowired
    private AdminRepository adminRepository;
 
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
 
    public Optional<Admin> findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
 
    public List<Admin> findActiveAdmins() {
        return adminRepository.findByIsActive(true);
    }
 
    public Admin deactivateAdmin(Long adminId) {
        Optional<Admin> adminOpt = adminRepository.findById(adminId);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            admin.setIsActive(false); 
            return adminRepository.save(admin);
        }
        return null;
    }
 
    public Admin activateAdmin(Long adminId) {
        Optional<Admin> adminOpt = adminRepository.findById(adminId);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            admin.setIsActive(true);  
            return adminRepository.save(admin);
        }
        return null;
    }
 
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }
 
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}