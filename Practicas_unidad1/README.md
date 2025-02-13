# Practica_1
## Katherynne Plessmann
## Salomon Cruz Vidal


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