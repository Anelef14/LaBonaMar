// ==============================
// ELEMENTOS HTML
// ==============================

const personas = document.getElementById("personas");
const fecha = document.getElementById("fecha");
const horaSeleccionada = document.getElementById("horaSeleccionada");
const listaHorarios = document.getElementById("listaHorarios");
const mensajeHorario = document.getElementById("mensajeHorario");
const btnReserva = document.getElementById("btnReserva");
const resumenReserva = document.getElementById("resumenReserva");


// ==============================
// HORARIOS DISPONIBLES
// ==============================

// Horarios de ejemplo para la reserva.
// Más adelante estos horarios pueden venir
// directamente desde el backend.

const horarios = [
    "11:15",
    "11:30",
    "11:45",
    "12:00",
    "12:15",
    "12:30",
    "12:45",
    "13:00",
    "13:15",
    "13:30",
    "13:45",
    "14:00",
    "14:15",
    "14:30",
    "14:45"
];


// ==============================
// FECHA MINIMA
// ==============================

// No permite seleccionar fechas anteriores a hoy.

const hoy = new Date();

const año = hoy.getFullYear();
const mes = String(hoy.getMonth() + 1).padStart(2, "0");
const dia = String(hoy.getDate()).padStart(2, "0");

const fechaHoy = `${año}-${mes}-${dia}`;

fecha.min = fechaHoy;

// Ponemos hoy como fecha inicial
fecha.value = fechaHoy;


// ==============================
// MOSTRAR HORARIOS
// ==============================

function mostrarHorarios() {

    // Limpiamos los horarios anteriores
    listaHorarios.innerHTML = "";

    // Eliminamos la hora seleccionada
    horaSeleccionada.value = "";

    mensajeHorario.textContent = "";

    // Obtenemos la fecha seleccionada
    const fechaElegida = new Date(fecha.value + "T00:00:00");

    const diaSemana = fechaElegida.getDay();


    // ==============================
    // LUNES
    // ==============================

    if (diaSemana === 1) {

        mensajeHorario.textContent =
            "El restaurante está cerrado los lunes.";

        return;
    }


    // ==============================
    // CREAR BOTONES DE HORARIO
    // ==============================

    horarios.forEach(function (hora) {

        const boton = document.createElement("button");

        boton.type = "button";

        boton.classList.add("hora");

        boton.textContent = hora;


        // ==============================
        // CLICK EN UNA HORA
        // ==============================

        boton.addEventListener("click", function () {

            // Quitamos la selección anterior
            const botones = document.querySelectorAll(".hora");

            botones.forEach(function (botonHora) {
                botonHora.classList.remove("seleccionada");
            });


            // Seleccionamos esta hora
            boton.classList.add("seleccionada");

            // Mostramos la hora arriba
            horaSeleccionada.value = hora;

            // Quitamos mensajes anteriores
            mensajeHorario.textContent = "";

            // Ocultamos resumen anterior
            resumenReserva.style.display = "none";
        });


        listaHorarios.appendChild(boton);

    });

}


// ==============================
// CAMBIO DE FECHA
// ==============================

fecha.addEventListener("change", function () {

    mostrarHorarios();

});


// ==============================
// BOTON RESERVA
// ==============================

btnReserva.addEventListener("click", function () {

    // Comprobamos la fecha
    if (fecha.value === "") {

        alert("Selecciona una fecha.");

        return;
    }


    // Comprobamos la hora
    if (horaSeleccionada.value === "") {

        alert("Selecciona una hora.");

        return;
    }


    // Obtenemos los datos
    const numeroPersonas = personas.value;
    const fechaReserva = fecha.value;
    const horaReserva = horaSeleccionada.value;


    // Convertimos la fecha a formato español
    const fechaObjeto = new Date(fechaReserva + "T00:00:00");

    const fechaFormateada = fechaObjeto.toLocaleDateString("es-ES");


    // ==============================
    // MOSTRAR RESUMEN
    // ==============================

    resumenReserva.innerHTML =
        "Reserva seleccionada: " +
        numeroPersonas +
        (numeroPersonas == 1 ? " persona" : " personas") +
        " - " +
        fechaFormateada +
        " - " +
        horaReserva;


    resumenReserva.style.display = "block";

});


// ==============================
// CAMBIO DE NUMERO DE PERSONAS
// ==============================

personas.addEventListener("change", function () {

    // Si ya había una reserva seleccionada,
    // ocultamos el resumen porque han cambiado los datos.

    resumenReserva.style.display = "none";

});


// ==============================
// CARGAR HORARIOS AL ABRIR
// ==============================

mostrarHorarios();