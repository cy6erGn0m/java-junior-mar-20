function loadUserStat() {
    var request = new XMLHttpRequest();
    request.open('get', '/unnamed/api/user-stat', true)
    request.onreadystatechange = function() {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var response = JSON.parse(request.responseText);
                handleLoadUsersResponse(response);
            } else {
                console.log("Failed to load users stat: ", request)
            }
        }
    };
    request.send(null);
}

function handleLoadUsersResponse(response) {
    document.getElementById('title').textContent = response.title;
    document.getElementById('users-count').textContent = response.usersCount;
    document.getElementById('user-login').textContent = response.someone.login;
}
