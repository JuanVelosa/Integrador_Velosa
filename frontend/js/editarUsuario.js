document.addEventListener('DOMContentLoaded', () => {
    const nameInput = document.getElementById('name');
    const lastnameInput = document.getElementById('lastname');
    const documentIdInput = document.getElementById('documentId');
    const newDocumentInput = document.getElementById('newDocument');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const passwordConfirmationInput = document.getElementById('passwordConfirmation');
    const editarButton = document.getElementById('editarButton');
    const searchButton = document.getElementById('searchButton');
    const menuToggle = document.getElementById('menuToggle');
    const menu = document.getElementById('menu');

    // URL del servidor
    const url = "http://localhost:8080/";

    if (menuToggle && menu) {
        menuToggle.addEventListener('click', (event) => {
            event.stopPropagation(); // Evita que el evento se propague y cierre el menú inmediatamente
            menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
        });

        window.addEventListener('click', (event) => {
            if (!menu.contains(event.target) && !menuToggle.contains(event.target)) {
                menu.style.display = 'none';
            }
        });
    }

    // Función para editar usuario
    async function editarUsuario(documentId, datosUsuario) {
        const url = `http://localhost:8080/user/edit/${documentId}`;

        try {
            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datosUsuario)
            });

            if (!response.ok) {
                throw new Error('No se pudo editar el usuario');
            }

            const usuarioEditado = await response.json();
            console.log('Usuario editado:', usuarioEditado);
            // Realizar acciones adicionales después de editar el usuario, si es necesario

        } catch (error) {
            console.error('Error al editar el usuario:', error);
        }
    }

    // Escuchar el evento de clic en el botón de editar
    editarButton.addEventListener('click', function (event) {
        event.preventDefault();

        // Obtener los valores de los campos del formulario
        const name = nameInput.value;
        const lastname = lastnameInput.value;
        const documentId = documentIdInput.value;
        const email = emailInput.value;
        const password = passwordInput.value;
        const passwordConfirmation = passwordConfirmationInput.value;
        const newDocument = newDocumentInput.value;

        // Verificar si las contraseñas coinciden
        if (password !== passwordConfirmation) {
            alert("Las contraseñas no coinciden");
            return; // Detener el flujo de ejecución si las contraseñas no coinciden
        }

        // Construir el objeto de datos del usuario
        let datosUsuario = {
            name: name,
            lastname: lastname,
            email: email,
            password: password,
            documentId: newDocument
        };

        // Llamar a la función para editar el usuario
        editarUsuario(documentId, datosUsuario);
        alert("Usuario editado");
    });

    // Función para buscar usuario
    async function buscarUsuario(userId) {
        const url = `http://localhost:8080/user/search/${userId}`;

        try {
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error('Usuario no encontrado');
            }

            const usuario = await response.json();
            // Actualizar los inputs con la información del usuario
            nameInput.value = usuario.name;
            lastnameInput.value = usuario.lastname;
            newDocumentInput.value = usuario.documentId;
            emailInput.value = usuario.email;
            passwordInput.value = usuario.password;

        } catch (error) {
            console.error('Error al buscar el usuario:', error);
            alert('Usuario no encontrado');
        }
    }

    // Escuchar el evento de clic en el botón de búsqueda
    searchButton.addEventListener('click', function (event) {
        event.preventDefault();

        // Obtener el valor del input de ID del usuario
        const userId = documentIdInput.value;

        // Llamar a la función para buscar el usuario
        buscarUsuario(userId);
    });
});
