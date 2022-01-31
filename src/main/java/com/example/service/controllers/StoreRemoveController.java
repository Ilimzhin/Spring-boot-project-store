package com.example.service.controllers;

import com.example.service.models.Post;
import com.example.service.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreRemoveController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/store/{id}/remove")
    public String storePostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/store";
    }

}
