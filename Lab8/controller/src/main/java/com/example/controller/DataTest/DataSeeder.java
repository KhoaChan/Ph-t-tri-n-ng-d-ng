package com.example.controller.DataTest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.controller.Models.Account;
import com.example.controller.Models.Book;
import com.example.controller.Models.Role;
import com.example.controller.Repositories.AccountRepository;
import com.example.controller.Repositories.BookRepository;
import com.example.controller.Repositories.RoleRepository;

@Configuration 
public class DataSeeder {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // 1. Kiểm tra và thêm dữ liệu sản phẩm mẫu
            if (bookRepository.count() == 0) {
                
                // Thêm 20 sản phẩm mẫu (10 Điện thoại, 10 Laptop)
                String[][] items = new String[][]{
                    // Điện thoại
                    {"iPhone 15 Pro Max", "Apple", "Điện thoại"},
                    {"Galaxy S24 Ultra", "Samsung", "Điện thoại"},
                    {"Xiaomi 14 Pro", "Xiaomi", "Điện thoại"},
                    {"Oppo Find X7 Ultra", "Oppo", "Điện thoại"},
                    {"Google Pixel 8 Pro", "Google", "Điện thoại"},
                    {"iPhone 14", "Apple", "Điện thoại"},
                    {"Galaxy Z Fold 5", "Samsung", "Điện thoại"},
                    {"ROG Phone 8", "Asus", "Điện thoại"},
                    {"Vivo X100 Pro", "Vivo", "Điện thoại"},
                    {"OnePlus 12", "OnePlus", "Điện thoại"},
                    
                    // Laptop
                    {"MacBook Pro 16 M3", "Apple", "Laptop"},
                    {"Dell XPS 15", "Dell", "Laptop"},
                    {"ThinkPad X1 Carbon Gen 11", "Lenovo", "Laptop"},
                    {"ROG Strix Scar 16", "Asus", "Laptop"},
                    {"HP Spectre x360", "HP", "Laptop"},
                    {"MacBook Air M2", "Apple", "Laptop"},
                    {"Acer Predator Helios 16", "Acer", "Laptop"},
                    {"MSI Raider GE78", "MSI", "Laptop"},
                    {"LG Gram 16", "LG", "Laptop"},
                    {"Surface Laptop Studio 2", "Microsoft", "Laptop"}
                };
                
                for (int i = 0; i < items.length; i++) {
                    Book b = new Book();
                    b.setTitle(items[i][0]);
                    b.setAuthor(items[i][1]); // Tạm dùng field Author để lưu Hãng sản xuất
                    b.setCategory(items[i][2]);
                    b.setImage("https://picsum.photos/seed/tech" + (i+1) + "/200/300");
                    // Điều chỉnh giá cơ bản cho thực tế hơn (từ ~19.9 triệu VNĐ)
                    b.setPrice(19900000.0 + i * 1500000); 
                    bookRepository.save(b);
                }
                System.out.println("==== ĐÃ THÊM 20 SẢN PHẨM CÔNG NGHỆ MẪU THÀNH CÔNG! ====");
            } else {
                System.out.println("==== DỮ LIỆU SẢN PHẨM ĐÃ TỒN TẠI, BỎ QUA SEEDING ====");
            }

            // 2. Kiểm tra và thêm dữ liệu tài khoản
            if (roleRepository.count() == 0) {
                Role roleAdmin = new Role(); 
                roleAdmin.setName("ROLE_ADMIN");
                
                Role roleUser = new Role(); 
                roleUser.setName("ROLE_USER");
                
                roleRepository.save(roleAdmin);
                roleRepository.save(roleUser);

                Account admin = new Account();
                admin.setLoginName("admin"); 
                admin.setPassword(passwordEncoder.encode("123456")); 
                admin.setRoles(Set.of(roleAdmin));
                accountRepository.save(admin);

                Account user = new Account();
                user.setLoginName("user"); 
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRoles(Set.of(roleUser));
                accountRepository.save(user);

                System.out.println("==== ĐÃ TẠO TÀI KHOẢN MẪU ====");
                System.out.println("Admin: admin / 123456");
                System.out.println("User: user / 123456");
            } else {
                System.out.println("==== DỮ LIỆU TÀI KHOẢN ĐÃ TỒN TẠI, BỎ QUA SEEDING ====");
            }
        };
    }
}