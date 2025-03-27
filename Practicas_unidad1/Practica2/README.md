# Practica_2
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"
```
val lista1 = List("rojo", "blanco", "negro")
```
Ejecución:
```
scala> val lista1 = List("rojo", "blanco", "negro")
val lista1: List[String] = List(rojo, blanco, negro)
```

### 2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
```
val lista2 = List("rojo", "blanco", "negro","verde" ,"amarillo", "azul", "naranja", "perla")
```
Ejecución:
```
val lista2 = List("rojo", "blanco", "negro","verde" ,"amarillo", "azul", "naranja", "perla")
val lista2: List[String] = List(rojo, blanco, negro, verde, amarillo, azul, naranja, perla)
```

### 3. Traer los elementos de "lista" "verde", "amarillo", "azul"
```
lista2(3)
lista2(4)
lista2(5)
```
Ejecución:
```
scala> lista2(3)
val res12: String = verde

scala> lista2(4)
val res13: String = amarillo

scala> lista2(5)
val res14: String = azul
```

### 4. Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5
```
val arreglo = Array.range(1, 1001, 5)
```
Ejecución:
```
scala> val arreglo = Array.range(1, 1001, 5)
val arreglo: Array[Int] = Array(1, 6, 11, 16, 21, 26, 31, 36, 41, 46, 51, 56, 61, 66, 71, 76, 81, 86, 91, 96, 101, 106, 111, 116, 121, 126, 131, 136, 141, 146, 151, 156, 161, 166, 171, 176, 181, 186, 191, 196, 201, 206, 211, 216, 221, 226, 231, 236, 241, 246, 251, 256, 261, 266, 271, 276, 281, 286, 291, 296, 301, 306, 311, 316, 321, 326, 331, 336, 341, 346, 351, 356, 361, 366, 371, 376, 381, 386, 391, 396, 401, 406, 411, 416, 421, 426, 431, 436, 441, 446, 451, 456, 461, 466, 471, 476, 481, 486, 491, 496, 501, 506, 511, 516, 521, 526, 531, 536, 541, 546, 551, 556, 561, 566, 571, 576, 581, 586, 591, 596, 601, 606, 611, 616, 621, 626, 631, 636, 641, 646, 651, 656, 661, 666, 671, 676, 681, 686, 691, 696, 701, 706, 711, 716, 721, 726, 731, 736, 741, 746, 751, 756, 761, 766, 771, 776, 781, 78...
```

### 5. Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos
```
val lista = List(1, 3, 3, 4, 6, 7, 3, 7) 
val elementosUnicos = lista.ToSet()        // Convertimos la lista a un conjunto con valores unicos
println(elementosUnicos)  //Imprimimos el conjunto
```
Ejecución:
```
scala> val lista = List(1, 3, 3, 4, 6, 7, 3, 7)
val lista: List[Int] = List(1, 3, 3, 4, 6, 7, 3, 7)

scala> val elementosUnicos = lista.toSet   
val elementosUnicos: scala.collection.immutable.Set[Int] = HashSet(1, 6, 7, 3, 4)

scala> println(elementosUnicos)
HashSet(1, 6, 7, 3, 4)
```

### 6. Crea una mapa mutable llamado nombres que contenga los siguiente "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
```
val nombres = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"))
println(nombres) //Imprime el mapa
```
Ejecución:
```
scala> val nombres = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"))
val nombres: scala.collection.mutable.Map[String,Any] = HashMap(Susana -> 27, Jose -> 20, Ana -> 23, Luis -> 24)

scala> println(nombres)
HashMap(Susana -> 27, Jose -> 20, Ana -> 23, Luis -> 24)
```

### 6 a . Imprime todas la llaves del mapa

```
println(nombres.keys)
```
Ejecución:
```
scala> println(nombres.keys)
Set(Susana, Jose, Ana, Luis)
```

### 7 b . Agrega el siguiente valor al mapa("Miguel", 23)

```
nombres += ("Miguel" -> 23)
println(nombres) //Imprime el mapa con el valor agregado
```
Ejecución:
``` 
scala> nombres += ("Miguel" -> 23)
val res18: nombres.type = HashMap(Susana -> 27, Miguel -> 23, Jose -> 20, Ana -> 23, Luis -> 24)

scala> println(nombres)
HashMap(Susana -> 27, Miguel -> 23, Jose -> 20, Ana -> 23, Luis -> 24)
```