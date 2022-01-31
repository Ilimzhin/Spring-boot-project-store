package com.example.service.controllers;

import com.example.service.models.Post;
import com.example.service.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class StoreDetailsController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/store/{id}")
    public String storeDetails(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)) {
            return "redirect:/store";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "store-details";
    }


}
