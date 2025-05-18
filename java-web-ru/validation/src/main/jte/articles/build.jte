@import exercise.dto.articles.BuildArticlePage

@params BuildArticlePage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание статьи</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

    <h1>Создать новую статью</h1>

    <!-- Ошибки -->
    @if (page.getErrors() != null && !page.getErrors().isEmpty()) {
        <div class="alert alert-danger">
            <ul>
                @for (error : page.getErrors()) {
                    <li>${error}</li>
                }
            </ul>
        </div>
    }

    <!-- Форма -->
    <form action="/articles" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">Заголовок</label>
            <input type="text" class="form-control" id="title" name="title" value="${page.getTitle()}">
        </div>

        <div class="mb-3">
            <label for="content" class="form-label">Содержимое</label>
            <textarea class="form-control" id="content" name="content" rows="5">${page.getContent()}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Создать</button>
    </form>

</body>
</html>

