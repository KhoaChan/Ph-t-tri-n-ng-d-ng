package com.example.controller.DataTest;

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

import java.util.Set;

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
            // 1. Kiểm tra và thêm dữ liệu sách mẫu
            if (bookRepository.count() == 0) {
                
                Book book1 = new Book();
                book1.setTitle("Đắc Nhân Tâm");
                book1.setAuthor("Dale Carnegie");
                book1.setImage("https://upload.wikimedia.org/wikipedia/vi/0/0a/%C4%90%E1%BA%AFc_nh%C3%A2n_t%C3%A2m.jpg");
                book1.setPrice(85000.0);

                Book book2 = new Book();
                book2.setTitle("Nhà Giả Kim");
                book2.setAuthor("Paulo Coelho");
                book2.setImage("https://translate.google.com/website?sl=en&tl=vi&hl=vi&client=srp&u=https://upload.wikimedia.org/wikipedia/en/3/3d/Possibly_the_Greatest_Alchemist_of_All_Time_light_novel_volume_1_cover.jpg");
                book2.setPrice(79000.0);

                Book book3 = new Book();
                book3.setTitle("Clean Code");
                book3.setAuthor("Robert C. Martin");
                book3.setImage("https://images-na.ssl-images-amazon.com/images/I/41jEbK-jG+L._SX373_BO1,204,203,200_.jpg");
                book3.setPrice(250000.0);

                // Lưu tất cả vào Database
                bookRepository.save(book1);
                bookRepository.save(book2);
                bookRepository.save(book3);

                System.out.println("==== ĐÃ THÊM DỮ LIỆU SÁCH MẪU THÀNH CÔNG! ====");
            } else {
                System.out.println("==== DỮ LIỆU SÁCH ĐÃ TỒN TẠI, BỎ QUA SEEDING ====");
            }

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