//PRACTICA #3

//1. Clasifica cada número como par o impar
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

// 2. El 7 afortunado
def afortunado(list:List[Int]): Int={
    var res=0 //se define el valor inicial de resultado en 0
    for(n <- list){ //se recorre la lista el numero de elementos
        if(n==7){ //si el elemento es 7 se entra al if
            res = res + 14 //14 al elemento
            // segunda ejecucion entra entonces 1+14=15
            // tercera y ultima ejecuin entra entonces 15+14=29
        }else{
            res = res + n
            //primera ejecución entra = 0+1=1 (1 porque es la n que significa mismo numero del elemento iterado de la lista)
        }
    }
    return res
}

val af= List(1,7,7) //Lista de numeros para ejecucion
println(afortunado(af)) //Ejecucion

// 3. Balance: Esta funcion intenta buscar si de una lista tiene balance dividiendo en dos partes sus elementos
def balance(list:List[Int]): Boolean={
    //se declaran variables
    var primera = 0
    var segunda = 0

    segunda = list.sum //se suma el total de elementos de la lista proporcionada

    for(i <- Range(0,list.length)){ //se recorre la lista por el numero de elemento en la lista
        primera = primera + list(i) // se hace la suma de la cantidad de elementos indicados en el i,
        //primero solo suma un elemento, si se ejecuta de nuevo suma 2 elementos y asi sucesivamente 
        segunda = segunda - list(i) //se resta la suma de la cantidad de elementos en la iteracion (i)


        if(primera == segunda){
            return true
            //si en alguna iteracion la suma de (primera) y de (segunda) son iguales, la lista tiene un balance
        }
    }
    return false 
}

val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

balance(bl) //tiene balance
balance(bl2) //tiene balance
balance(bl3) //no tiene balance

// 4. Detecta palabras que se pueden leer igual de izquierda a derecha y viceversa
def palindromo(palabra:String):Boolean ={ //La funcion recibe un String y devuelve un Boolean
    return (palabra == palabra.reverse) // Se compara la palabra original con su versión invertida
}

val palabra = "OSO" //igual en ambos sentidos
val palabra2 = "ANNA" //igual en ambos sentidos
val palabra3 = "JUAN" // no es igual en ambos sentidos

println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))
