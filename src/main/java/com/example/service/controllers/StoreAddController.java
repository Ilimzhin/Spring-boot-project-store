package com.example.service.controllers;

import com.example.service.models.Post;
import com.example.service.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreAddController {


    @Autowired
    private PostRepository postRepository;

    @GetMapping("/store/add")
    public String storeAdd(Model model) {
        return "store-add";
    }


    @PostMapping("/store/add")
    public String storePostAdd(@RequestParam String title,
                               @RequestParam String anons,
                               @RequestParam String full_text,
                               Model model) {
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/store";
    }

}
