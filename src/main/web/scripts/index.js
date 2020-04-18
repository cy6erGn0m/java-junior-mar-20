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

    // example, not running
    if (response.elements) {
        var tableBodyElement = document.getElementById('elements-table-body');
        var i;
        for (i = 0; i < response.elements.length; ++i) {
            var element = response.elements[i];
            // elements: title, id

            var cellTitle = document.createElement("td");
            cellTitle.textContent = element.title;
            var cellId = document.createElement("td")
            cellId.textContent = element.id;

            var row = document.createElement("tr");
            row.appendChild(cellTitle);
            row.appendChild(cellId);

            tableBodyElement.appendChild(row);
        }
    }
}
