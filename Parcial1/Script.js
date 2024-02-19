document.getElementById('box1').addEventListener('mouseover', function() {
    this.style.backgroundColor = 'Blue';
});

document.getElementById('box2').addEventListener('click', function() {
    alert('Haz hecho clic en la caja!');
});

document.getElementById('box1').addEventListener('focus', function() {
    this.style.border = '2px solid red';
});