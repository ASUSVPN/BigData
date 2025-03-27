# Practica_1
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Implementa un programa en scala que calcule el radio de un circulo
```
var d = 10
var radio = d / 2
var resultado = "Teniendo "+d+" de diametro el radio es" + " " + radio 
println(resultado)
```
Ejecución:
```
scala> var d = 10
var d: Int = 10

scala> var radio = d / 2
var radio: Int = 5

scala> var resultado = "Teniendo "+d+" de diametro el radio es" + " " + radio
var resultado: String = Teniendo 10 de diametro el radio es 5
```

### 2. Implementa un programa en scala que me diga si un numero es primo
Se consideran las reglas para que un numero sea primo, que sea mayor a 1 y se verifica que no sea divisible por los numeros en un rango desde 2 hasta el numero proporcionado -1.

```
var numero = 7

if (numero > 1 && (2 until numero).forall(numero % _ != 0)) {
    println(s"$numero Es primo.")
} else {
    println(s"$numero No es primo.")
}
```
Ejecución:
```
scala> if (numero > 1 && (2 until numero).forall(numero % _ != 0)) {
     |     println(s"$numero Es primo.")
     | } else {
     |     println(s"$numero No es primo.")
     | }
7 Es primo.
```


### 3. Dada la variable bird = "tweet", utiliza interpolacion de strings para imprimir "Estoy escribiendo un tweet"

```
val bird = "tweet"
val pr = "Estoy escribiendo un"
val fin = s"${pr} ${bird}"
println(fin)
```
Ejecución:
```
scala> val bird = "tweet"
val bird: String = tweet

scala> val pr = "Estoy escribiendo un"
val pr: String = Estoy escribiendo un

scala> val fin = s"${pr} ${bird}"
val fin: String = Estoy escribiendo un tweet
```
### 4. Dada la variable  mensaje = "Hola Luke yo soy tu padre!" utilizas slice para extraer la secuencia "Luke"
```
val phrase = "Hola Luke yo soy tu padre!"
phrase slice (5,9)
```
Ejecución:
``` 
scala> val phrase = "Hola Luke yo soy tu padre!"
val phrase: String = Hola Luke yo soy tu padre!

scala> phrase slice (5,9)
val res20: String = Luke
```
#### 5. Cual es la diferencia entre value (val) una variable (var) en scala?
```
La diferencia principal es la "mutabilidad", es decir si permite modificar el valor, ya que,
val en inmutable (no se puede reasignar su valor despues de su inicializacion, es como una
constante), mientras que var permite modificar su valor despues de la inicializacion.
```
#### 6. Dada la tupla (2, 4, 5, 1, 2, 3.1416, 3, 7) imprime "3.1416"
```
val myTup = (2, 4, 5, 1, 2, 3.1416, 3, 7)
myTup._6
```
Ejecución:
```
scala> val myTup = (2, 4, 5, 1, 2, 3.1416, 3, 7)
scala> myTup._6
val res18: Double = 3.1416
```