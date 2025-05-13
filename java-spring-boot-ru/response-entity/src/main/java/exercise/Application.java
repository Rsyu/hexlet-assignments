package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;
import lombok.Setter;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    @Setter
    private static  List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
 // GET /posts — список всех постов + заголовок X-Total-Count
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index() {
        return ResponseEntity
                .ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    // GET /posts/{id} – получение поста по id или 404
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /posts – создание поста и возврат 201
    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        String newId = "post" + (posts.size() + 1);
        post.setId(newId);
        posts.add(post);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(post);
    }

    // PUT /posts/{id} – обновление поста или 404
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post newPost) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                post.setTitle(newPost.getTitle());
                post.setBody(newPost.getBody());
                return ResponseEntity.ok(post);
            }
        }
        return ResponseEntity.notFound().build();
    }    
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
