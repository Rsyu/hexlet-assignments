package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
         app.get("/users", ctx -> {
            int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            int per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            int fromIndex = (page - 1) * per;
            int toIndex = Math.min(fromIndex + per, USERS.size());

            if (fromIndex >= USERS.size() || fromIndex < 0) {
                ctx.json(List.of());
            } else {
                List<Map<String, String>> pageUsers = USERS.subList(fromIndex, toIndex);
                ctx.json(pageUsers);
            }
        });        
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
