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
public class StoreController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/store")
    public String storeMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "store-main";
    }

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

    @PostMapping("/store/{id}/remove")
    public String storePostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/store";
    }

}
