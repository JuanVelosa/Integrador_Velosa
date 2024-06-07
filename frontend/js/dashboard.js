const URL_BASE = "http://localhost:8080";
const container = document.getElementById('users');
const searchInput = document.getElementById('searchInput');
const searchButton = document.getElementById('searchButton');
const confirmModal = document.getElementById('confirmModal');
const confirmMessage = document.getElementById('confirmMessage');
const confirmYes = document.getElementById('confirmYes');
const confirmNo = document.getElementById('confirmNo');
let currentUserId = null;

document.addEventListener('DOMContentLoaded', () => {
    const menuToggle = document.getElementById('menuToggle');
    const menu = document.getElementById('menu');

    if (menuToggle && menu) {
        menuToggle.addEventListener('click', () => {
            menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
        });

        window.addEventListener('click', (event) => {
            if (!menu.contains(event.target) && !menuToggle.contains(event.target)) {
                menu.style.display = 'none';
            }
        });
    }

    searchButton.addEventListener('click', async () => {
        const query = searchInput.value.trim();

        if (query) {
            await getUser(query);
        } else {
            console.log('El campo de búsqueda está vacío');
            await getUsers();
        }
    });

    async function getUsers() {
        try {
            let response = await fetch(URL_BASE + "/user/list");
            let users = await response.json();

            container.innerHTML = '';
            users.forEach(doctor => container.appendChild(renderDoctor(doctor)));
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    }

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

        try {
            const res = await fetch(queryUrl);

            if (res.status === 404) {
                console.error('Doctor no encontrado');
                return;
            }

            if (res.status === 500) {
                throw new Error('Respuesta del servidor inesperada');
            }

            let doctorBox = await res.json();

            container.innerHTML = '';

            if (Array.isArray(doctorBox)) {
                doctorBox.forEach(doctor => container.appendChild(renderDoctor(doctor)));
            } else {
                container.appendChild(renderDoctor(doctorBox));
            }
        } catch (error) {
            console.error('Error fetching user:', error);
        }
    }

    function renderDoctor(doctor) {
        let container = document.createElement('div');
        let nameWrapper = document.createElement('div');
        let idWrapper = document.createElement('div');
        let emailWrapper = document.createElement('div');
        let action = document.createElement('button');
        let editButton = document.createElement('button');

        const name = doctor.name + " " + doctor.lastname;

        container.appendChild(nameWrapper);
        container.appendChild(idWrapper);
        container.appendChild(document.createElement('br'));
        container.appendChild(emailWrapper);
        container.appendChild(document.createElement('br'));
        container.appendChild(editButton);
        container.appendChild(action);

        let nameText = document.createElement('p');
        let idText = document.createElement('p');
        let emailText = document.createElement('p');

        nameWrapper.appendChild(nameText);
        idWrapper.appendChild(idText);
        emailWrapper.appendChild(emailText);

        nameText.innerHTML = name;
        idText.innerHTML = doctor.documentId;
        emailText.innerHTML = doctor.email;

        action.innerHTML = 'Eliminar';
        editButton.innerHTML = 'Editar';

        action.addEventListener('click', function () {
            confirmMessage.textContent = "¿Estás seguro que quieres borrar a " + name + "?";
            confirmModal.style.display = "block";
            currentUserId = doctor.id;
        });

        editButton.addEventListener('click', function () {
            window.location.href = 'editarUsuario.html';
        });

        container.classList.add("user-info");
        container.id = "doctor-" + doctor.id;

        return container;
    }

    confirmYes.addEventListener('click', async function () {
        if (currentUserId !== null) {
            await deleteUserById(currentUserId);
            confirmModal.style.display = "none";
            currentUserId = null;
        }
    });

    confirmNo.addEventListener('click', function () {
        confirmModal.style.display = "none";
        currentUserId = null;
    });

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

        if (!res.ok) {
            msg = "No fue posible eliminar el doctor.";
            console.error(msg);
            return;
        }

        const name = await res.json().name;
        msg = "Se eliminó a " + name;
        console.log(msg);

        location.href = "dashboard.html";
    }

    window.addEventListener('load', getUsers);
});
