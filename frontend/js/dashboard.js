
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
        await getUsers();
    }
});

async function getUsers() {

    let response = await fetch(URL_BASE + "/user/list"); // HTTP Request
    let users = await response.json();

    container.innerHTML = '';
    users.forEach(doctor => container.appendChild(renderDoctor(doctor)));

}

// tipos: id, name, email
async function getUser(query) {

    const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    const idRegex = /^\d+$/;

    const nameRegex = /^[a-zA-Z\s]+$/;

    let queryUrl = URL_BASE + "/user";

    if (emailRegex.test(query)) {

        queryUrl += "/searchByEmail/" + query;

    } else if (idRegex.test(query)) {

        queryUrl += "/searchByDocumentId/" + query;

    } else if (nameRegex.test(query)) {

        queryUrl += "/searchByName/" + query;

    }

    const res = await fetch(queryUrl);

    // TODO: handle 404 (show nothing found) and 500 (server error)
    if (res.status === 404)
        console.error('Doctor no encontrado');

    if (res.status === 500)
        throw new Error('Respuesta del servidor inesperada');

    let doctorBox = await res.json();

    container.innerHTML = '';

    // es una lista
    if (Array.isArray(doctorBox))
        doctorBox.forEach(doctor => container.appendChild(renderDoctor(doctor)));

    else
        container.appendChild(renderDoctor(doctorBox));

}

function renderDoctor(doctor) {
    let container = document.createElement('div'); //<div></div>
    let nameWrapper = document.createElement('div'); //<div></div>
    let idWrapper = document.createElement('div'); //<div></div>
    let emailWrapper = document.createElement('div'); //<div></div>
    let action = document.createElement('button');
    let editButton = document.createElement('button'); // Nuevo botón de Editar

    const name = doctor.name + " " + doctor.lastname;

    container.appendChild(nameWrapper); //<div></div>
    container.appendChild(idWrapper); //<div></div>
    container.appendChild(document.createElement('br'));
    container.appendChild(emailWrapper); //<div></div>
    container.appendChild(document.createElement('br'));
    container.appendChild(editButton);
    container.appendChild(action); //<div><button></button></div>
    


    let nameText = document.createElement('p'); //<p></p>
    let idText = document.createElement('p'); //<p></p>
    let emailText = document.createElement('p'); //<p></p>


    nameWrapper.appendChild(nameText); //<div><h4></h4><p></p></div>
    idWrapper.appendChild(idText); //<div><h4></h4><p></p></div>
    emailWrapper.appendChild(emailText); //<div><h4></h4><p></p></div>


    nameText.innerHTML = name; //<p>***</p>
    idText.innerHTML = doctor.documentId; //<p>***</p>
    emailText.innerHTML = doctor.email; //<p>***</p>
    
    action.innerHTML = 'Eliminar'; //<button>Eliminar</button>
    editButton.innerHTML= 'Editar';

    action.addEventListener('click', async function () {
        alert("¿Estás seguro que quieres borrar a " + name + "?");
        await deleteUserById(doctor.id);
    });

    editButton.addEventListener('click', function () {
        // Redirigir a otra página al hacer clic en el botón "Editar"
        window.location.href = 'home.html';
    });
    
    container.classList.add("user-info");

    container.id = "doctor-" + doctor.id;

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

