package exercise;

import exercise.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class Application {

    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();
    private AtomicInteger idCounter = new AtomicInteger(posts.size() + 1); // Счётчик для id новых постов

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Получить список всех постов
    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> limit) {
        int pageSize = limit.orElse(10);
        int pageNumber = page.orElse(1);
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, posts.size());
        return posts.subList(start, end);
    }

    // Получить пост по id
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable String id) {
        return posts.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    // Создать новый пост
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        post.setId("post" + idCounter.getAndIncrement());
        posts.add(post);
        return post;
    }

    // Обновить существующий пост
    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        Post post = posts.stream()
                         .filter(p -> p.getId().equals(id))
                         .findFirst()
                         .orElse(null);
        if (post != null) {
            post.setTitle(updatedPost.getTitle());
            post.setBody(updatedPost.getBody());
            return post;
        }
        return null; // Если пост не найден, возвращаем null
    }

    // Удалить пост
    @DeleteMapping("/posts/{id}")
    public boolean deletePost(@PathVariable String id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }
}
