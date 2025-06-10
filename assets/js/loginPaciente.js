document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const correo = document.getElementById('correo').value.trim();
    const contraseña = document.getElementById('contraseña').value.trim();

    
    if (!correo || !contraseña){
        document.getElementById('mensaje').textContent = "Correo y contraseña son obligatorios";
        return;
    }
    
    const paciente = { correo, contraseña };
    
    try {
        const respuesta = await fetch('http://localhost:8080/api/paciente/login', {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(paciente)
        });

        if(respuesta.ok){
            const usuaruio = await respuesta.json();

            //Guardando en local storage
            localStorage.setItem('usuario', JSON.stringify(usuaruio));

            //redirigimos a pacienteDashboard
            window.location.href="/pages/pacientesDashboard.html";
        }else {
            const error = await respuesta.text();
            document.getElementById('mensaje').textContent = error;
        }
    } catch (error) {
        console.log('Error de conexion: ', error);
        document.getElementById('mensaje').textContent = "Error al conectar con el servidor";
        
    }
});