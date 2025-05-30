// Assessment 1 / Practica 1
// 1. Implementa un programa en scala que calcule el radio de un circulo
// 2. Implementa un programa en scala que me diga si un numero es primo
// 3. Dada la variable bird = "tweet", utiliza interpolacion de strings 
// para imprimir "Estoy escribiendo un tweet"
// 4. Dada la variable  mensaje = "Hola Luke yo soy tu padre! utilizas 
//slice para extraer la secuencia "Luke"
// 5. Cual es la diferencia entre value una variable var en scala?
// 6. Dada la tupla (2, 4, 5, 1, 2, 3.1416, 3, 7) imprime "3.1416"

//3. Dada la variable bird = "tweet", utiliza interpolacion de strings 
// para imprimir "Estoy escribiendo un tweet"
val bird = "tweet"
val pr = "Estoy escribiendo un"
val fin = s"${pr} ${bird}"
println(fin)

//4. Dada la variable  mensaje = "Hola Luke yo soy tu padre! utilizas 
//slice para extraer la secuencia "Luke"
val phrase = "Hola Luke yo soy tu padre!"
st slice (5,9)

//5. Cual es la diferencia entre value (val) una variable (var) en scala?
// La diferencia principal es la "mutabilidad", es decir si permite modificar el valor, ya que, 
//val en inmutable (no se puede reasignar su valor despues de su inicializacion, es como una constante), 
//mientras que var permite modificar su valor despues de la inicializacion.

//6. Dada la tupla (2, 4, 5, 1, 2, 3.1416, 3, 7) imprime "3.1416"
val myTup = (2, 4, 5, 1, 2, 3.1416, 3, 7)
myTup._6