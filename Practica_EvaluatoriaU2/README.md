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
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}

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

scala> import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}


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
val indexerModel = indexer.fit(data)
val indexedData = indexerModel.transform(data)
```
Ejecución:
```
scala> val indexer = new StringIndexer()
val indexer: org.apache.spark.ml.feature.StringIndexer = strIdx_f955b90603b0

scala>   .setInputCol("species")
val res4: indexer.type = strIdx_f955b90603b0

scala>   .setOutputCol("label")
val res5: res4.type = strIdx_f955b90603b0

scala> val indexerModel = indexer.fit(data)
val indexerModel: org.apache.spark.ml.feature.StringIndexerModel = StringIndexerModel: uid=strIdx_f955b90603b0, handleInvalid=error

scala> val indexedData = indexerModel.transform(data)
val indexedData: org.apache.spark.sql.DataFrame = [sepal_length: double, sepal_width: double ... 4 more fields]
```
### 7. Construya el modelo de clasificación y explique su arquitectura.
```
```
#### Crea la columna features combinando columnas numéricas
```

val assembler = new VectorAssembler()
  .setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width"))
  .setOutputCol("features")

val finalData = assembler.transform(indexedData)
```
Ejecución:
```
scala> val assembler = new VectorAssembler()
val assembler: org.apache.spark.ml.feature.VectorAssembler = VectorAssembler: uid=vecAssembler_c979d92b47b4, handleInvalid=error

scala>   .setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width"))
val res6: assembler.type = VectorAssembler: uid=vecAssembler_c979d92b47b4, handleInvalid=error, numInputCols=4

scala>   .setOutputCol("features")
val res7: res6.type = VectorAssembler: uid=vecAssembler_c979d92b47b4, handleInvalid=error, numInputCols=4

scala> val finalData = assembler.transform(indexedData)
val finalData: org.apache.spark.sql.DataFrame = [sepal_length: double, sepal_width: double ... 5 more fields]
```
#### Divide los datos en entrenamiento (70%) y prueba (30%)
```
val Array(train, test) = finalData.randomSplit(Array(0.7, 0.3), seed = 1234L)
```
Ejecución:
```
scala> val Array(train, test) = finalData.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [sepal_length: double, sepal_width: double ... 5 more fields]
val test: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [sepal_length: double, sepal_width: double ... 5 more fields]
```
#### Definie la arquitectura de la red neuronal: 4 entradas porque el conjunto iris tiene 4 características por flor, 5 y 4 neuronas como una configuración común y balanceada, por último 3 clases que queremos predecir.
```
val layers = Array[Int](4, 5, 4, 3)
```
Ejecución:
```
scala> val layers = Array[Int](4, 5, 4, 3)
val layers: Array[Int] = Array(4, 5, 4, 3)
```
#### Configura el modelo
```
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```
Ejecución:
```
scala> val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
val trainer: org.apache.spark.ml.classification.MultilayerPerceptronClassifier = mlpc_22e2df4ef192
```
#### Entrena el modelo
```
val model = trainer.fit(train)
```
Ejecución:
```
scala> val model = trainer.fit(train)
val model: org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel = MultilayerPerceptronClassificationModel: uid=mlpc_22e2df4ef192, numLayers=4, numClasses=3, numFeatures=4
```
#### Hace las predicciones con el conjunto de prueba
```
val result = model.transform(test)
```
Ejecución:
```
scala> val result = model.transform(test)
val result: org.apache.spark.sql.DataFrame = [sepal_length: double, sepal_width: double ... 8 more fields]
```
### 8. Imprime los resultados del modelo y de sus observaciones.
```
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"******** Test set accuracy = ${evaluator.evaluate(predictionAndLabels)} ********")
```
Ejecución:
```
scala> val predictionAndLabels = result.select("prediction", "label")
val predictionAndLabels: org.apache.spark.sql.DataFrame = [prediction: double, label: double]

scala> val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
val evaluator: org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator = MulticlassClassificationEvaluator: uid=mcEval_f6b2884919cb, metricName=accuracy, metricLabel=0.0, beta=1.0, eps=1.0E-15

scala> println(s"******** Test set accuracy = ${evaluator.evaluate(predictionAndLabels)} ********")
******** Test set accuracy = 0.9803921568627451 ********
```
#### Observaciones: El modelo clasificó el 98% de los ejemplos del conjunto de prueba, lo cual lo hace un resultado fiable, a continuación se muestra algunas filas para ver su predicción.
```
result.select("features", "label", "prediction").show(10)
```
Ejecución:
```
scala> result.select("features", "label", "prediction").show(10)
+-----------------+-----+----------+
|         features|label|prediction|
+-----------------+-----+----------+
|[4.3,3.0,1.1,0.1]|  0.0|       0.0|
|[4.4,2.9,1.4,0.2]|  0.0|       0.0|
|[4.5,2.3,1.3,0.3]|  0.0|       0.0|
|[4.9,3.1,1.5,0.1]|  0.0|       0.0|
|[5.0,3.0,1.6,0.2]|  0.0|       0.0|
|[5.0,3.2,1.2,0.2]|  0.0|       0.0|
|[5.0,3.3,1.4,0.2]|  0.0|       0.0|
|[5.0,3.4,1.5,0.2]|  0.0|       0.0|
|[5.0,3.5,1.3,0.3]|  0.0|       0.0|
|[5.0,3.6,1.4,0.2]|  0.0|       0.0|
+-----------------+-----+----------+
only showing top 10 rows
```