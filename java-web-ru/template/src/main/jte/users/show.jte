<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>User Info</h1>
    <ul class="list-group">
        <li class="list-group-item"><strong>ID:</strong> @page.getUser().getId()</li>
        <li class="list-group-item"><strong>First Name:</strong> @page.getUser().getFirstName()</li>
        <li class="list-group-item"><strong>Last Name:</strong> @page.getUser().getLastName()</li>
        <li class="list-group-item"><strong>Email:</strong> @page.getUser().getEmail()</li>
    </ul>
    <a class="btn btn-secondary mt-3" href="/users">Back to list</a>
</div>
</body>
</html>

