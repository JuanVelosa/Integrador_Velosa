// Función para generar un nombre aleatorio
function genRandName() {
    const names = ['Juan', 'Maria', 'Pedro', 'Ana', 'Luis', 'Laura', 'Carlos', 'Sofia', 'Diego', 'Elena'];
    return names[Math.floor(Math.random() * names.length)];
}

// Función para generar un apellido aleatorio
function genRandLastName() {
    const lastNames = ['Garcia', 'Martinez', 'Lopez', 'Rodriguez', 'Perez', 'Sanchez', 'Gonzalez', 'Fernandez', 'Moreno', 'Alvarez'];
    return lastNames[Math.floor(Math.random() * lastNames.length)];
}

// Función para generar un email aleatorio
function genRandEmail() {
    const domains = ['gmail.com', 'hotmail.com', 'yahoo.com', 'outlook.com', 'live.com'];
    const name = genRandName().toLowerCase();
    const lastName = genRandLastName().toLowerCase();
    const domain = domains[Math.floor(Math.random() * domains.length)];
    return `${name}${lastName}@${domain}`;
}

// Función para generar un email aleatorio
function genRandId() {
    const digits = ['1', '2', '3', '4', '5', "6", "7", "8", "9", "0"];
    let id = "";

    for (let i = 0; i < 9; i++)
        id = digits[Math.floor(Math.random() * digits.length)];

    return id;
}

function genUsers(n) {
    const users = [];
    for (let i = 1; i <= n; i++) {
        const user = {
            id: 0,
            name: genRandName(),
            lastname: genRandLastName(),
            email: genRandEmail(),
            documentId: genRandId,
            password: "password",
            listPatient: []
        };
        users.push(user);
    }
    return users;
}

window.addEventListener("load", () => {
    const users = genUsers(50);
    users.forEach(async (user) => {

        let json = JSON.stringify(user);

        await fetch('http://localhost:8080/doctors', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: json
        });
        console.log(json);
    });

    // location.href = "index.html";
})