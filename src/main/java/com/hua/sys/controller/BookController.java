package com.hua.sys.controller;

import com.hua.sys.entity.Books;
import com.hua.sys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cyh
 * @date 2020/7/28 20:12
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/test")
    @ResponseBody
    public String list(){
        return "测试一下子";
    }

    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);
        return "allBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddPager(){
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addPager(Books books){
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model,int id){
        Books books = bookService.queryBookById(id);
        model.addAttribute("book",books);
        return "updateBook";
    }

    @RequestMapping("updateBook")
    public String updateBook(Model model,Books book){
        bookService.updateBook(book);
//        Books books = bookService.queryBookById(book.getBookID());
//        model.addAttribute("books",books);
        return "redirect:/book/allBook";
    }


    @RequestMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }


}
