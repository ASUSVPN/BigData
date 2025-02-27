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

# Practica_3
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Implementa un programa en scala que calcule el radio de un circulo
```
def listEvens(list:List[Int]): String ={ 
    for(n <- list){ // Itera sobre cada elemento 'n' de la lista
        if(n%2==0){ // Evalua si 'n' es divisible por 2 
            println(s"$n is even") // Imprime que es par
        }else{ //si no es divisible por 2
            println(s"$n is odd") // Imprime que es impar
        }
    }
    return "Done" // Cuando de termina dice Done
}

val l = List(1,2,3,4,5,6,7,8)
val l2 = List(4,3,22,55,7,8)
listEvens(l)
listEvens(l2)
```
Ejecucion:
```
scala> listEvens(l)
1 is odd
2 is even
3 is odd
4 is even
5 is odd
6 is even
7 is odd
8 is even
val res0: String = Done

scala> listEvens(l2)
4 is even
3 is odd
22 is even
55 is odd
7 is odd
8 is even
val res1: String = Done
```
### 4. Detecta palabras que se pueden leer igual de izquierda a derecha y viceversa
```
//Detecta palabras que se pueden leer igual de izquierda a derecha y viceversa
def palindromo(palabra:String):Boolean ={ //La funcion recibe un String y devuelve un Boolean
    return (palabra == palabra.reverse) // Se compara la palabra original con su versión invertida
}

val palabra = "OSO" //igual en ambos sentidos
val palabra2 = "ANNA" //igual en ambos sentidos
val palabra3 = "JUAN" // no es igual en ambos sentidos

println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))
```
Ejecucion:
```
scala> println(palindromo(palabra))
true

scala> println(palindromo(palabra2))
true

scala> println(palindromo(palabra3))
false
```
