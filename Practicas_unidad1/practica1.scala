// 1. Implementa un programa en scala que calcule el radio de un circulo

// Teniendo el diametro como dato
var diametro = 10
var radio = diametro / 2
println(radio)


// 2. Implementa un programa en scala que me diga si un numero es primo

// variable se modifica por el numero a comprobar si es primo o no
var numero = 7

if (numero > 1 && (2 until numero).forall(numero % _ != 0)) {
    println(s"$numero Es Primo.")
} else {
    println(s"$numero no es primo")
}