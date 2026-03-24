package com.example.controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.Models.Book;
import com.example.controller.Models.CartItem;
import com.example.controller.Models.Order;
import com.example.controller.Services.BookService;
import com.example.controller.Services.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam(defaultValue = "1") int quantity, HttpSession session) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new java.util.LinkedHashMap<>();
        }
        java.util.Optional<Book> opt = bookService.getBookById(id);
        if (opt.isPresent()) {
            Book book = opt.get();
            Long bid = book.getId();
            CartItem item = cart.get(bid);
            if (item == null) {
                item = new CartItem(bid, book.getTitle(), book.getPrice(), quantity);
                cart.put(bid, item);
            } else {
                item.setQuantity(item.getQuantity() + quantity);
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/books";
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        List<CartItem> items = cart == null ? new ArrayList<>() : new ArrayList<>(cart.values());
        double total = items.stream().mapToDouble(CartItem::getTotal).sum();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Map<String,String> params, HttpSession session) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        if (cart != null) {
            // expects params like qty_{bookId}
            for (String key : params.keySet()) {
                if (key.startsWith("qty_")) {
                    try {
                        Long bookId = Long.valueOf(key.substring(4));
                        int qty = Integer.parseInt(params.get(key));
                        if (qty <= 0) cart.remove(bookId);
                        else {
                            CartItem ci = cart.get(bookId);
                            if (ci != null) ci.setQuantity(qty);
                        }
                    } catch (Exception e) {}
                }
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String customerName, HttpSession session, Model model) {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        List<CartItem> items = cart == null ? new ArrayList<>() : new ArrayList<>(cart.values());
        if (items.isEmpty()) {
            return "redirect:/cart";
        }
        Order order = orderService.createOrder(customerName, items);
        session.removeAttribute("cart");
        model.addAttribute("order", order);
        return "order-success";
    }
}
