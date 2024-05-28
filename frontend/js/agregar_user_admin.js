const nameInput = document.getElementById('name');
const lastnameInput = document.getElementById('lastname');
const documentIdInput = document.getElementById('documentId');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const passwordConfirmationInput = document.getElementById('passwordConfirmation');
const registerButton = document.getElementById('registerbutton');
const cancelButton = document.getElementById('cancelarButton');


const url = "http://localhost:8080/"

registerButton.addEventListener('click', registrarUsuario);



async function postDoctor(doctor) {
    //Obj a JSON
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
    location.href = "index.html";

}


// Función para registrar usuarios
function registrarUsuario(event) {
    // Evitar que el formulario se envíe y la página se actualice
    event.preventDefault();

    // Obtener los valores de los campos de entrada
    const nombre = nameInput.value;
    const apellido = lastnameInput.value;
    const id = documentIdInput.value;
    const email = emailInput.value;
    const contraseña = passwordInput.value;
    const confirmacionContraseña = passwordConfirmationInput.value;

    // Validar que los campos no estén vacíos
    if (nombre && apellido && id && email && contraseña && confirmacionContraseña) {
        // Validar que las contraseñas coincidan
        if (contraseña === confirmacionContraseña) {


            let doctor = {
                name: nombre,
                lastname: apellido,
                documentId: id,
                email: email,
                password: contraseña

            }
            postDoctor(doctor)

            // Enviar los datos del usuario al servidor o hacer cualquier otra acción necesaria
            alert('Usuario registrado exitosamente');
        } else {
            // Mostrar un mensaje de error si las contraseñas no coinciden
            alert('Las contraseñas no coinciden');
        }
    } else {
        // Mostrar un mensaje de error si algún campo está vacío
        alert('Por favor complete todos los campos');
    }
}


cancelButton.addEventListener('click', function (event) {
    // Prevenir el comportamiento predeterminado del botón (por ejemplo, enviar un formulario)
    event.preventDefault();

    // Obtener referencia a todos los inputs del formulario
    const inputs = document.querySelectorAll('.login-form input');

    // Iterar sobre cada input y establecer su valor en vacío
    inputs.forEach(input => {
        input.value = '';
    });

    // Redirigir a otra página
    window.location.href = 'home.html';
});
