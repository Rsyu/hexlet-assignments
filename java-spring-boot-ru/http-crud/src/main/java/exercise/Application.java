package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import exercise.model.Post;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@RestController
@RequestMapping("/posts")
public class Application {

    private static final List<Post> posts = new ArrayList<>(Data.getPosts());
    private static final AtomicInteger idCounter = new AtomicInteger(31); // т.к. в Data создаются post1 - post30

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Получение всех постов
    @GetMapping
    public List<Post> index() {
        return posts;
    }

    // Получение одного поста по ID
    @GetMapping("/{id}")
    public Post show(@PathVariable String id) {
        return posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    // Обновление поста
    @PutMapping("/{id}")
    public Post update(@PathVariable String id, @RequestBody Post updatedPost) {
        posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .ifPresent(post -> {
                post.setTitle(updatedPost.getTitle());
                post.setBody(updatedPost.getBody());
            });

        return updatedPost;
    }

    // Создание нового поста
    @PostMapping
    public Post create(@RequestBody Post post) {
        String id = "post" + idCounter.getAndIncrement();
        post.setId(id);
        posts.add(post);
        return post;
    }

    // Удаление поста
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}

