// Mouseover
document.getElementById('box1').addEventListener('mouseover', function() {
    this.style.backgroundColor = 'lightgreen';
    document.getElementById('description1').innerText = "Es una función en la que un usuario mueve el cursor sobre un elemento y produce una respuesta.";
});

// Click
document.getElementById('box2').addEventListener('click', function() {
    this.style.backgroundColor = 'lightcoral';
    document.getElementById('description2').innerText = "Es una función en la que un usuario hace clic en él y produce una respuesta.";
});

// Focus
document.getElementById('box3').addEventListener('focus', function() {
    this.style.backgroundColor = 'lightyellow';
    document.getElementById('description3').innerText = "Este cuadro cambia de color cuando recibes el enfoque.";
});