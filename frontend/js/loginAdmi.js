const signInButton = document.getElementById('signin');

signInButton.addEventListener('click', async () => {

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    await signIn(email, password)

});

async function signIn(email, password) {
    const URL = "http://localhost:8080/signinAdmin";

    const body = JSON.stringify(
        {
            email: email,
            password: password
        }
    );

    try {
        const res = await fetch(URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: body
        });

        let msg = "";

        if (res.status == 404)
            msg = "No existe un usuario con ese correo";

        else if (res.status == 401)
            msg = "Correo o contraseña incorrectos";

        else if (res.status == 200) {
            msg = "Inicio de sesión exitoso";
            window.location.href = 'home.html';
        }


        alert(msg);

    } catch (error) {
        console.error('Error:', error);
    }
}
