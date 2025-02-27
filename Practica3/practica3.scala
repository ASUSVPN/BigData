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

//Esta funcion intenta buscar si de una lista tiene balance dividiendo en dos partes sus elementos
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