# Practica_Evaluatoria_U2
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Cargar en un dataframe de la fuente de datos Iris.csv que se encuentra en
### https://github.com/jcromerohdz/iris, elaborar la limpieza de datos necesaria para
### ser procesado por el siguiente algoritmo (Importante, esta limpieza debe ser por
### medio de un script de Scala en Spark).
### a. Utilice el algoritmo de Machine Learning Multilayer Perceptron Classifier
### de la librería Mllib de Spark

```
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
```
Ejecución:
```

```
### 2 ¿Cuáles son los nombres de las columnas?
```
000
```
Ejecución:
```

```
### 3 ¿Cómo es el esquema?
```
000
```
Ejecución:
```

```
### 4. Imprime las primeras 5 columnas.
```
000
```
Ejecución:
```

```
### 5. Usa el método describe () para aprender más sobre los datos del DataFrame.
```
000
```
Ejecución:
```

```
### 6. Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
```
000
```
Ejecución:
```

```
### 7. Construya el modelo de clasificación y explique su arquitectura.
```
000
```
Ejecución:
```

```
### 8. Imprima los resultados del modelo y de sus observaciones.
```
000
```
Ejecución:
```

```