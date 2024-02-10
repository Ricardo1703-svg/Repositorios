// Mouseover
document.getElementById('box1').addEventListener('mouseover', function() {
    this.style.backgroundColor = 'lightgreen';
    document.getElementById('description1').innerText = "Este cuadro cambia de color cuando pasas el ratón sobre él.";
});

// Click
document.getElementById('box2').addEventListener('click', function() {
    this.style.backgroundColor = 'lightcoral';
    document.getElementById('description2').innerText = "Este cuadro cambia de color cuando haces clic en él.";
});

// Focus
document.getElementById('box3').addEventListener('focus', function() {
    this.style.backgroundColor = 'lightyellow';
    document.getElementById('description3').innerText = "Este cuadro cambia de color cuando recibes el enfoque.";
});