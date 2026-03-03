package com.example.controller.DataTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.controller.Models.Book;
import com.example.controller.Repositories.BookRepository;

@Configuration 
public class DataSeeder {

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Kiểm tra xem database đã có dữ liệu chưa (tránh bị thêm trùng lặp khi chạy lại server)
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
                System.out.println("==== DỮ LIỆU ĐÃ TỒN TẠI, BỎ QUA SEEDING ====");
            }
        };
    }
}
