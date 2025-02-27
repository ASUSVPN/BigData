//PRACTICA #2

// 1. Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"

val lista1 = List("rojo", "blanco", "negro")

// 2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"

val lista2 = List("rojo", "blanco", "negro","verde" ,"amarillo", "azul", "naranja", "perla")

// 3. Traer los elementos de "lista" "verde", "amarillo", "azul"

lista2(3)
lista2(4)
lista2(5)

// 4. Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5
val arreglo = Array.range(1, 1001, 5)

// 5. Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos

val lista = List(1, 3, 3, 4, 6, 7, 3, 7) 
val elementosUnicos = lista.toSet  // Convertimos la lista a un conjunto con valores unicos
println(elementosUnicos)  //Imprimimos el conjunto

// 6. Crea una mapa mutable llamado nombres que contenga los siguiente
//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

val nombres = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"))
println(nombres) //Imprime el mapa

// 6 a . Imprime todas la llaves del mapa
println(nombres.keys) //Imprime las llaves del mapa

// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)
nombres += ("Miguel" -> 23)
println(nombres) //Imprime el mapa con el valor agregado