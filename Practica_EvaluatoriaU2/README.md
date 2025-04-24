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
// Importar librerias
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

// Iniciar la sesion
val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierEvaluation").getOrCreate()

// Se cargan los datos
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
```
Ejecución:
```

scala> import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier

scala> import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

scala> import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession

scala> val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierEvaluation").getOrCreate()
warning: 1 deprecation (since 2.13.3); for details, enable `:setting -deprecation` or `:replay -deprecation`
25/04/23 20:53:04 WARN SparkSession: Using an existing Spark session; only runtime SQL configurations will take effect.
val spark: org.apache.spark.sql.SparkSession = org.apache.spark.sql.SparkSession@49c3f97e

scala> val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
val data: org.apache.spark.sql.DataFrame = [sepal_length: double, sepal_width: double ... 3 more fields]
```
### 2 ¿Cuáles son los nombres de las columnas?
```
data.columns
```
Ejecución:
```
scala> data.columns
val res0: Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species)
```
### 3 ¿Cómo es el esquema?
```
data.printSchema()
```
Ejecución:
```
scala> data.printSchema()
root
 |-- sepal_length: double (nullable = true)
 |-- sepal_width: double (nullable = true)
 |-- petal_length: double (nullable = true)
 |-- petal_width: double (nullable = true)
 |-- species: string (nullable = true)
```
### 4. Imprime las primeras 5 columnas.
```
data.show(5)
```
Ejecución:
```
scala> data.show(5)
+------------+-----------+------------+-----------+-------+
|sepal_length|sepal_width|petal_length|petal_width|species|
+------------+-----------+------------+-----------+-------+
|         5.1|        3.5|         1.4|        0.2| setosa|
|         4.9|        3.0|         1.4|        0.2| setosa|
|         4.7|        3.2|         1.3|        0.2| setosa|
|         4.6|        3.1|         1.5|        0.2| setosa|
|         5.0|        3.6|         1.4|        0.2| setosa|
+------------+-----------+------------+-----------+-------+
only showing top 5 rows

```
### 5. Usa el método describe () para aprender más sobre los datos del DataFrame.
```
data.describe()
```
Ejecución:
```
scala> data.describe().show()
25/04/23 21:11:29 WARN package: Truncated the string representation of a plan since it was too large. This behavior can be adjusted by setting 'spark.sql.debug.maxToStringFields'.
+-------+------------------+-------------------+------------------+------------------+---------+
|summary|      sepal_length|        sepal_width|      petal_length|       petal_width|  species|
+-------+------------------+-------------------+------------------+------------------+---------+
|  count|               150|                150|               150|               150|      150|
|   mean| 5.843333333333335| 3.0540000000000007|3.7586666666666693|1.1986666666666672|     null|
| stddev|0.8280661279778637|0.43359431136217375| 1.764420419952262|0.7631607417008414|     null|
|    min|               4.3|                2.0|               1.0|               0.1|   setosa|
|    max|               7.9|                4.4|               6.9|               2.5|virginica|
+-------+------------------+-------------------+------------------+------------------+---------+
```
### 6. Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
```
val indexer = new StringIndexer()
  .setInputCol("species")   
  .setOutputCol("label")    
  .fit(data)
```
Ejecución:
```
scala>   .setInputCol("species")
val res9: indexer.type = strIdx_a6a173466f12

scala>   .setOutputCol("label")
val res10: res9.type = strIdx_a6a173466f12

scala>   .fit(data)
val res11: org.apache.spark.ml.feature.StringIndexerModel = StringIndexerModel: uid=strIdx_a6a173466f12, handleInvalid=error
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