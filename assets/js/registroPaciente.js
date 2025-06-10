// Initialization for ES Users


document.getElementById('registroForm').addEventListener('submit', async function(e){
    e.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const dni = document.getElementById('dni').value;
    const correo = document.getElementById('correo').value;
    const contraseña = document.getElementById('contraseña').value;

    const paciente = {
        nombre: nombre,
        apellido: apellido,
        dni: dni,
        correo: correo,
        contraseña: contraseña
    };

    
   try {
    const respuesta = await fetch('http://localhost:8080/api/paciente/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(paciente)
    });

    if (respuesta.ok) {
        let data = null;
        try {
            data = await respuesta.json(); // intenta parsear JSON si existe
            console.log(data);
        } catch (jsonError) {
            console.warn("No se pudo parsear JSON:", jsonError);
        }

        // Mostrar mensaje y modal
        document.getElementById('mensaje').textContent = 'Paciente registrado con éxito';

        // Mostrar el modal de éxito
        const modal = new mdb.Modal(document.getElementById('modalRegistroExitoso'));
        modal.show();

        // Redirigir al login cuando se cierra el modal
        const modalElement = document.getElementById('modalRegistroExitoso');
        modalElement.addEventListener('hidden.mdb.modal', () => {
            window.location.href = '/pages/loguinPaciente.html'; // Asegúrate de que este sea el archivo correcto
        });

    } else {
        document.getElementById('mensaje').textContent = 'Error al registrar el paciente';
    }

} catch (error) {
    console.error('Error de conexión:', error);
    document.getElementById('mensaje').textContent = 'Error de conexión con el servidor';
}

});