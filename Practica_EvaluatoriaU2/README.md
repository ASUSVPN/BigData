# Practica_Evaluatoria_U2
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Cargar en un dataframe de la fuente de datos Iris.csv que se encuentra en
### https://github.com/jcromerohdz/iris, elaborar la limpieza de datos necesaria para
### ser procesado por el siguiente algoritmo (Importante, esta limpieza debe ser por
### medio de un script de Scala en Spark).
### a. Utilice el algoritmo de Machine Learning Multilayer Perceptron Classifier
### de la librería Mllib de Spark
####
```
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
```
Ejecución:
```

```
