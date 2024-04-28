
const URL_BASE = "http://localhost:8080"
const editarImg = document.querySelector('.user-actions img:first-of-type');
const eliminarImg = document.querySelector('.user-actions img:last-of-type');
const container = document.getElementById('users');
const searchInput = document.getElementById('searchInput');
const searchButton = document.getElementById('searchButton');

searchButton.addEventListener('click', async () => {
    const query = searchInput.value.trim();

    // evitar requests vacios
    if (query) {
        await getUser(query);
    } else {
        console.log('El campo de búsqueda está vacío');
    }
});

async function getUsers() {
    let response = await fetch(URL_BASE + "/user/list"); // HTTP Request
    let users = await response.json();

    users.forEach(doctor => {

        const userContainer = renderDoctor(doctor);

        container.appendChild(userContainer);

    });
}

// tipos: id, name, email
async function getUser(query) {

    const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    const idRegex = /^\d+$/;

    let queryUrl = URL_BASE + "/user";

    if (emailRegex.test(query)) {
        queryUrl += "/searchByEmail/" + query;
    } else if (idRegex.test(query)) {
        queryUrl += "/searchByDocumentId/" + query;
    } else {
        queryUrl += "/searchByName/" + query;
    }

    let doctor = "";

    try {

        const response = await fetch(queryUrl);

        if (response.status === 404)
            console.error('Doctor no encontrado');

        if (response.status === 500)
            throw new Error('Respuesta del servidor inesperada');

        doctor = await response.json();

    } catch (error) {

        console.error('Error:', error);

    }

    container.innerHTML = '';
    container.appendChild(renderDoctor(doctor));

}

function renderDoctor(doctor) {
    let container = document.createElement('div'); //<div></div>
    let title = document.createElement('h3'); //<h3></h3>
    let sub = document.createElement('small'); //<small></small>
    let action = document.createElement('button');

    const name = doctor.name + " " + doctor.lastname;

    container.appendChild(title); //<div><h3></h3></div>
    container.appendChild(sub); //<div><h3></h3><small></small></div>
    container.appendChild(document.createElement('br'));
    container.appendChild(action);

    title.innerHTML = name; //<h3>***</h3>
    sub.innerHTML = doctor.email; //<small>***</small>
    action.innerHTML = 'Eliminar'; //<button>Eliminar</button>

    action.addEventListener('click', async function () {
        alert("Estás seguro que quieres borrar a " + name);
        await deleteUserById(doctor.id);
    });

    container.classList.add("user-info");

    return container;
}

// Función para eliminar un usuario por su ID
async function deleteUserById(id) {

    let res = null;
    let msg = "";

    try {
        res = await fetch(URL_BASE + '/user/delete/' + id, {
            method: 'DELETE'
        });
    } catch (error) {
        console.error('Error:', error);
    }

    if (!res.ok)
        msg = "No fue posible eliminar el doctor.";

    const name = await res.json().name;
    msg = "Se eliminó a " + name;

    // Actualizar la lista de usuarios después de eliminar uno
    location.href = "dashboard.html";

}

// Ejemplo de uso:
// Buscar usuarios al cargar la página
window.addEventListener('load', getUsers);

