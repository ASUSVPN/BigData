# Practica_2
## Katherynne Plessmann
## Salomon Cruz Vidal

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