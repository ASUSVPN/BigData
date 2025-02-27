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