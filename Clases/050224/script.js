const boton1 = document.getElementById("alerta");
boton1.addEventListener("click",()=>{
    window.alert("Hiciste Click");
})

const text1 = document.getElementById("texto");
text1.addEventListener("mouseover",()=>{
    text1.innerHTML= "Texto Cambiado";
})
/*

function funcion(){
    window.alert("Hiciste Click")
}
*/