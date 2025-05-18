package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
                app.get("/articles/build", ctx -> {
            var page = new BuildArticlePage(null, null, List.of());
            ctx.render("articles/build.jte", model("page", page));
        });

        app.post("/articles", ctx -> {
            String title = ctx.formParam("title", String.class)
                .check(t -> t != null && t.trim().length() >= 2, "Название не должно быть короче двух символов")
                .getOrDefault(null);

            String content = ctx.formParam("content", String.class)
                .check(c -> c != null && c.trim().length() >= 10, "Статья должна быть не короче 10 символов")
                .getOrDefault(null);

            List<String> errors = new java.util.ArrayList<>();

            if (title == null) {
                errors.add("Название не должно быть короче двух символов");
            }
            if (content == null) {
                errors.add("Статья должна быть не короче 10 символов");
            }
            if (title != null && ArticleRepository.existsByTitle(title)) {
                errors.add("Статья с таким названием уже существует");
            }

            if (!errors.isEmpty()) {
                ctx.status(422);
                var page = new BuildArticlePage(title, content, errors);
                ctx.render("articles/build.jte", model("page", page));
                return;
            }

            var article = new Article(title.trim(), content.trim());
            ArticleRepository.save(article);
            ctx.redirect("/articles");
        });

        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
