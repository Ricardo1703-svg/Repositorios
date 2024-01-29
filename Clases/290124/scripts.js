function funcion1(){
    num1 = (document.getElementById("num1")).value;
    num2 = (document.getElementById("num2")).value;
    //document.write(sumar());
    var suma = sumar(num1,num2);
    var result = document.getElementById("resultado");
    result.innerHTML =suma;
}

function sumar(n1,n2){
    try{
        var num1 = Number(n1);
        var num2 = Number(n2);
        console.log(n1)
        console.log(n2)
        document.write("La suma es: "+ (num1+num2));
    }catch(error){
        return("No se puede realizar la suma");
    }
    
}