@layout("layout/page.jte")

<h1>Создание нового пользователя</h1>

<form action="/users" method="post" class="mt-4">
    <div class="mb-3">
        <label for="firstName" class="form-label">Имя</label>
        <input type="text" class="form-control" id="firstName" name="firstName" required>
    </div>

    <div class="mb-3">
        <label for="lastName" class="form-label">Фамилия</label>
        <input type="text" class="form-control" id="lastName" name="lastName" required>
    </div>

    <div class="mb-3">
        <label for="email" class="form-label">Электронная почта</label>
        <input type="email" class="form-control" id="email" name="email" required>
    </div>

    <div class="mb-3">
        <label for="password" class="form-label">Пароль</label>
        <input type="password" class="form-control" id="password" name="password" required>
    </div>

    <button type="submit" class="btn btn-primary">Создать</button>
</form>

