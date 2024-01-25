//1- Declarar dos variables, una para un número entero y otra para un
//número decimal. Luego con estos números realiza las operaciones de suma, resta, multiplicación, división y exponente.

var numeroEntero = 10;
var numeroDecimal = 5.5;

var suma = numeroEntero + numeroDecimal;
var resta = numeroEntero - numeroDecimal;
var multiplicacion = numeroEntero * numeroDecimal;
var division = numeroEntero / numeroDecimal;
var exponente = Math.pow(numeroEntero, 2);

console.log("-----------------------------1------------------------------------------")
console.log("Suma:", suma);
console.log("Resta:", resta);
console.log("Multiplicación:", multiplicacion);
console.log("División:", division);
console.log("Exponente:", exponente);
console.log("-------------------------------2----------------------------------------")

//2- Crear dos variables de tipo cadena de texto una con su nombre
//completo y la otra con su carné de estudiante, luego concatena ambas variables.

var nombre = "RICARDO ALEXANDER ALVAREZ PORTILLO";
var carnet = "SMSS054322"

console.log("Hola", nombre + " su Codigo es:", carnet)
console.log("--------------------------------3---------------------------------------")

//3- Declarar un número luego convertir ese número a una cadena de texto y mostrar el resultado.

var numero = 15;
var palabra = numero.toString();

console.log("Su numero en texto es: ", palabra);
console.log("--------------------------------4---------------------------------------")

//4- Declarar una cadena que represente un número luego convertir esa cadena a un número y mostrar el resultado.

var cadenaNumero = "123.45";
var numeroDesdeCadena1 = Number(cadenaNumero);

console.log("Número desde cadena (con Number()):", numeroDesdeCadena1);
console.log("--------------------------------5---------------------------------------")

//5- En física el peso(N) se calcula con el valor de la masa (Kg)*valor de la gravedad(m/s).
//En la tierra el valor de la gravedad es de 9.8m/s. Realiza el cálculo del peso declarando una variable para un valor de masa en
//kilogramos y cuatro constantes para el valor de la gravedad en: la tierra, la luna, marte y mercurio. La unidad del peso es Newtons (N).

var peso = 45;

const gravedadTierra = 9.8;
const gravedadLuna = 1.625;
const gravedadMarte = 3.721;
const gravedadMercurio = 3.7;

var pesoTierra = peso * gravedadTierra;
var pesoLuna = peso * gravedadLuna;
var pesoMarte = peso * gravedadMarte;
var pesoMercurio = peso * gravedadMercurio;

console.log("Peso en la Tierra:", pesoTierra.toFixed(2), "N");
console.log("Peso en la Luna:", pesoLuna.toFixed(2), "N");
console.log("Peso en Marte:", pesoMarte.toFixed(2), "N");
console.log("Peso en Mercurio:", pesoMercurio.toFixed(2), "N");
console.log("-----------------------------------------------------------------------")