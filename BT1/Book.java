import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Book {
    private int id;
    private String title;
    private String author;
    private long price;

    public Book() {
    }

    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }

    public void input() {
        Scanner x = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        this.id = Integer.parseInt(x.nextLine());
        
        System.out.print("Nhập tên sách: ");
        this.title = x.nextLine();
        
        System.out.print("Nhập Tác giả: ");
        this.author = x.nextLine();
        
        System.out.print("Nhập Đơn giá: ");
        this.price = x.nextLong();

        x.nextLine();
    }

    public void output() {
        String msg = """
            BOOK: id= %d, title=%s, author=%s, price=%d""".formatted(id, title, author, price);
        System.out.println(msg);
    }

    public static void main(String[] args) {
    List<Book> listBook = new ArrayList<>();
    Scanner x = new Scanner(System.in);
    
    String msg = """
        Chương trình quản lý sách
        1. Thêm 1 cuốn sách
        2. Xóa 1 cuốn sách
        3. Thay đổi sách
        4. Xuất thông tin
        5. Tìm sách Lập trình
        6. Lấy sách tối đa theo giá
        7. Tìm kiếm theo tác giả
        0. Thoát
        Chọn chức năng: """;

    int chon = 0;
    do {
        System.out.printf(msg);
        chon = x.nextInt();
        x.nextLine();

        switch (chon) {
            case 1 -> {
                Book newBook = new Book();
                newBook.input();
                listBook.add(newBook);
            }
            case 2 -> {
                System.out.print("Nhập vào ma sách cần xóa:");
                int bookid = x.nextInt();
                x.nextLine();
                
                Book find = listBook.stream()
                        .filter(p -> p.getId() == bookid)
                        .findFirst()
                        .orElseThrow();
                        
                listBook.remove(find);
                System.out.println("Đã xóa sách thành công");
            }
            case 3 -> {
                System.out.print("Nhập vào mã sách cần điều chỉnh:");
                    int bookid = x.nextInt();
                    x.nextLine();

                    Book find = listBook.stream().filter(p -> p.getId() == bookid).findFirst().orElse(null);
                    if (find != null) {
                        System.out.println("Đã tìm thấy sách, nhập thông tin mới:");
                        find.input();
                    } else {
                        System.out.println("Không tìm thấy sách!");
                    }
            }
            case 4 -> {
                System.out.println("\n Xuất thông tin danh sách ");
                listBook.forEach(p -> p.output());
            }
            case 5 -> {
                List<Book> list5 = listBook.stream().filter(u->u.getTitle().toLowerCase().contains("lập trình")).toList();
                list5.forEach(Book::output);
            }
            case 6 -> {
                    System.out.print("Nhập mức giá tối thiểu: ");
                    long minPrice = x.nextLong();
                    System.out.print("Nhập số lượng sách muốn lấy (Top): ");
                    int limitNum = x.nextInt();
                    x.nextLine();

                    System.out.println("Kết quả:");
                    listBook.stream()
                            .filter(b -> b.getPrice() >= minPrice)
                            .sorted(Comparator.comparingLong(Book::getPrice).reversed())
                            .limit(limitNum)
                            .forEach(Book::output);
                }

            case 7 -> {
                System.out.print("Nhập tên các tác giả cần tìm: ");
                String inputStr = x.nextLine();
                Set<String> authorsToFind = Arrays.stream(inputStr.split(",")).map(String::trim).collect(Collectors.toSet());
                System.out.println("Kết quả tìm kiếm:");
                listBook.stream().filter(b -> authorsToFind.contains(b.getAuthor())) .forEach(Book::output);
                }
        }
    } while (chon != 0);
}
}
