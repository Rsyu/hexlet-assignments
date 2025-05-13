package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
 // Получение всех постов
    @GetMapping("/posts")
    public List<Post> index() {
        return posts;
    }

    // Получение одного поста
    @GetMapping("/posts/{id}")
    public Post show(@PathVariable String id) {
        return posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    // Создание нового поста
    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        String newId = "post" + (posts.size() + 1);
        post.setId(newId);
        posts.add(post);
        return post;
    }

    // Обновление поста
    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post newPost) {
        for (var post : posts) {
            if (post.getId().equals(id)) {
                post.setTitle(newPost.getTitle());
                post.setBody(newPost.getBody());
                break;
            }
        }
        return newPost;
    }

    // Удаление поста
    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }    
    // END
}
