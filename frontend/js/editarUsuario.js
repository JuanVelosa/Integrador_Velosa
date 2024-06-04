const API = "http://localhost:8080" + "/doctors";
const nameInput = document.getElementById('name');
const lastnameInput = document.getElementById('lastname');
const documentIdInput = document.getElementById('documentId');
const newDocumentInput = document.getElementById('newDocument');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const passwordConfirmationInput = document.getElementById('passwordConfirmation');
const editarButton = document.getElementById('editarButton');


async function editarUsuario(documentId, datosUsuario) {
    const url = API + `/${documentId}`;

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
editarButton.addEventListener('click', function(event) {
    event.preventDefault();

    // Obtener los valores de los campos del formulario
    const name = document.getElementById('name').value;
    const lastname = document.getElementById('lastname').value;
    const documentId = document.getElementById('documentId').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const passwordConfirmation = document.getElementById('passwordConfirmation').value;
    const newDocument = document.getElementById('newDocument').value;

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


async function buscarUsuario(userId) {
    const url = API + `/${userId}`;

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
searchButton.addEventListener('click', function(event) {
    event.preventDefault();

    // Obtener el valor del input de ID del usuario
    const userId = document.getElementById('documentId').value;

    // Llamar a la función para buscar el usuario
    buscarUsuario(userId);
});
