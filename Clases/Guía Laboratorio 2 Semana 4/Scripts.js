// Mouseover
document.getElementById('box1').addEventListener('mouseover', function() {
    this.style.backgroundColor = 'red';
});
document.getElementById('box2').addEventListener('mouseover', function() {
    this.style.backgroundColor = 'blue';
});
document.getElementById('box3').addEventListener('mouseover', function() {
    this.style.backgroundColor = 'green';
});

// Click
document.getElementById('btn1').addEventListener('click', function() {
    alert('Haz hecho clic en el botón 1');
});
document.getElementById('btn2').addEventListener('click', function() {
    alert('Haz hecho clic en el botón 2');
});
document.getElementById('btn3').addEventListener('click', function() {
    alert('Haz hecho clic en el botón 3');
});

// Focus
document.getElementById('input1').addEventListener('focus', function() {
    this.classList.add('focused');
});
document.getElementById('input1').addEventListener('blur', function() {
    this.classList.remove('focused');
});
document.getElementById('input2').addEventListener('focus', function() {
    this.classList.add('focused2');
});
document.getElementById('input2').addEventListener('blur', function() {
    this.classList.remove('focused2');
});
document.getElementById('input3').addEventListener('focus', function() {
    this.classList.add('focused3');
});
document.getElementById('input3').addEventListener('blur', function() {
    this.classList.remove('focused3');
});