package com.example.service.controllers;

import com.example.service.models.Post;
import com.example.service.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class StoreEditController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/store/{id}/edit")
    public String storeEdit(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)) {
            return "redirect:/store";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "store-edit";
    }

    @PostMapping("/store/{id}/edit")
    public String storePostUpdate(@PathVariable(value = "id") long id, @RequestParam String title,
                                  @RequestParam String anons, @RequestParam String full_text,
                                  Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/store";
    }

}
