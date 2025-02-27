// Practica 2

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
// 6. Crea una mapa mutable llamado nombres que contenga los siguiente
//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
// 6 a . Imprime todas la llaves del mapa
// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)

// Practica 3

// def isEven(num:Int): Boolean = {
//     return num%2 == 0
// }
// def isEven(num:Int): num%2 == 0
// println(isEven(6))
// println(isEven(3))
// Practice 3, analyse the following code with your own words

//Clasifica cada número como par o impar
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

//3 7 afortunado

def afortunado(list:List[Int]): Int={
    var res=0
    for(n <- list){
        if(n==7){
            res = res + 14
        }else{
            res = res + n
        }
    }
    return res
}

val af= List(1,7,7)
println(afortunado(af))

def balance(list:List[Int]): Boolean={
    var primera = 0
    var segunda = 0

    segunda = list.sum

    for(i <- Range(0,list.length)){
        primera = primera + list(i)
        segunda = segunda - list(i)

        if(primera == segunda){
            return true
        }
    }
    return false 
}

val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

balance(bl)
balance(bl2)
balance(bl3)

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
