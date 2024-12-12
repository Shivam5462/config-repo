package com.coforge.finance.controller;

import com.coforge.finance.model.Admin;
import com.coforge.finance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
		Admin savedAdmin = adminService.saveAdmin(admin);
		return ResponseEntity.ok(savedAdmin);
	}

	@GetMapping("/{username}")
	public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username) {
		Optional<Admin> admin = adminService.findAdminByUsername(username);
		return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/active")
	public ResponseEntity<List<Admin>> getActiveAdmins() {
		List<Admin> activeAdmins = adminService.findActiveAdmins();
		return ResponseEntity.ok(activeAdmins);
	}

	@PutMapping("/deactivate/{adminId}")
	public ResponseEntity<Admin> deactivateAdmin(@PathVariable Long adminId) {
		Admin admin = adminService.deactivateAdmin(adminId);
		return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
	}

	@PutMapping("/activate/{adminId}")
	public ResponseEntity<Admin> activateAdmin(@PathVariable Long adminId) {
		Admin admin = adminService.activateAdmin(adminId);
		return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{adminId}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
		adminService.deleteAdmin(adminId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		List<Admin> admins = adminService.getAllAdmins();
		return ResponseEntity.ok(admins);
	}
}