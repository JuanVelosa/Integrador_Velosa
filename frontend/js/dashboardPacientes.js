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