document.addEventListener('DOMContentLoaded', () => {
    const nameInput = document.getElementById('name');
    const lastnameInput = document.getElementById('lastname');
    const documentIdInput = document.getElementById('documentId');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const passwordConfirmationInput = document.getElementById('passwordConfirmation');
    const registerButton = document.getElementById('registerbutton');
    const cancelButton = document.getElementById('cancelarButton');
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

    // Evento de clic en el botón de registro
    registerButton.addEventListener('click', registrarUsuario);

    // Evento de clic en el botón de cancelar
    cancelButton.addEventListener('click', function (event) {
        event.preventDefault();
        resetForm();
        window.location.href = 'home.html';
    });

    // Evento de clic en el botón de hamburguesa para desplegar el menú
    menuToggle.addEventListener('click', function (event) {
        event.stopPropagation(); // Evita que el evento se propague y cierre el menú inmediatamente
        // Toggle para mostrar u ocultar el menú
        menu.classList.toggle('show-menu');
    });

    // Cerrar el menú al hacer clic fuera de él
    document.addEventListener('click', function (event) {
        if (!menu.contains(event.target) && !menuToggle.contains(event.target)) {
            menu.classList.remove('show-menu');
        }
    });

    // Función para enviar una solicitud POST para registrar un usuario
    async function postDoctor(doctor) {
        let json = JSON.stringify(doctor);
        console.log(json);

        let response = await fetch(url + 'user/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: json
        });

        console.log(response);
        return response;
    }

    // Función para registrar usuarios
    async function registrarUsuario(event) {
        event.preventDefault();

        const nombre = nameInput.value;
        const apellido = lastnameInput.value;
        const id = documentIdInput.value;
        const email = emailInput.value;
        const contraseña = passwordInput.value;
        const confirmacionContraseña = passwordConfirmationInput.value;

        if (nombre && apellido && id && email && contraseña && confirmacionContraseña) {
            if (contraseña === confirmacionContraseña) {
                let doctor = {
                    name: nombre,
                    lastname: apellido,
                    documentId: id,
                    email: email,
                    password: contraseña
                };

                try {
                    let response = await postDoctor(doctor);
                    if (response.ok) {
                        alert('Usuario registrado exitosamente');
                        resetForm();
                    } else {
                        let errorData = await response.json();
                        alert(`Error al registrar usuario: ${errorData.message}`);
                    }
                } catch (error) {
                    console.error('Error:', error);
                    alert('Error al registrar usuario.');
                }
            } else {
                alert('Las contraseñas no coinciden');
            }
        } else {
            alert('Por favor complete todos los campos');
        }
    }

    // Función para reiniciar el formulario
    function resetForm() {
        nameInput.value = '';
        lastnameInput.value = '';
        documentIdInput.value = '';
        emailInput.value = '';
        passwordInput.value = '';
        passwordConfirmationInput.value = '';
    }
});
