<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Users List</h1>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th>
            </tr>
        </thead>
        <tbody>
            @for(user : page.getUsers()) {
                <tr>
                    <td><a href="/users/@user.getId()">@user.getId()</a></td>
                    <td>@user.getFirstName()</td>
                    <td>@user.getLastName()</td>
                    <td>@user.getEmail()</td>
                </tr>
            }
        </tbody>
    </table>
</div>
</body>
</html>

