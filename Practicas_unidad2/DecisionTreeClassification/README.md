# Practica_DecisionTreeClassification
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Estas líneas se usan para importar las librerias necesarias para construir, entrenar, transformar y evaluar el modelo Árbol de decisión en Spark.
```
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```
Ejecución:
```
scala> import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.Pipeline

scala> import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassificationModel

scala> import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.classification.DecisionTreeClassifier

scala> import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

scala> import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```
### 2. Esta línea se usa para crear y manejar la sesión principal de Spark.
```
 import org.apache.spark.sql.SparkSession
```
Ejecución:
```
scala> import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession
```
### 3. Esta línea se usa para cargar los datos almacenados en formato LIBSVM como un DataFrame.
```
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```
Ejecución:
```
scala> val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
25/04/23 18:52:19 WARN LibSVMFileFormat: 'numFeatures' option not specified, determining the number of features by going though the input. If you know the number in advance, please specify it via 'numFeatures' option to avoid the extra scan.
val data: org.apache.spark.sql.DataFrame = [label: double, features: vector]
``` 
### 4. Esta línea se usa para indexar las etiquetas, añadiendo metadatos a la columna label. Se ajusta sobre todo el conjunto de datos para incluir todas las etiquetas posibles.
```
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```
Ejecución:
```
scala>     val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
val labelIndexer: org.apache.spark.ml.feature.StringIndexerModel = StringIndexerModel: uid=strIdx_dd29d8569648, handleInvalid=error
```     
### 5. Esta línea se usa para identificar automáticamente las características categóricas y asignarles un índice.
```
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```
Ejecución:
```
scala>     val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
val featureIndexer: org.apache.spark.ml.feature.VectorIndexerModel = VectorIndexerModel: uid=vecIdx_7510a38bbe66, numFeatures=692, handleInvalid=error
``` 
### 6. Esta línea se usa para dividir los datos en conjuntos de entrenamiento y prueba (30% se reserva para pruebas).

```
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

```
Ejecución:
```
scala>     val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
val trainingData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
val testData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
``` 
### 7. Esta línea se usa para entrenar un modelo de árbol de decisión.
```
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")
```
Ejecución:
```
scala>     val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")
val dt: org.apache.spark.ml.classification.DecisionTreeClassifier = dtc_ee2e45ba8df4
```
### 8. Esta línea se usa para convertir las etiquetas indexadas de nuevo a sus valores originales.
 
```
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labelsArray(0))

```
Ejecución:
```
scala>     val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labelsArray(0))
val labelConverter: org.apache.spark.ml.feature.IndexToString = idxToStr_104c44678c08
```
### 9. Esta línea se usa para encadenar los indexadores y el modelo en un Pipeline
```
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
```
Ejecución:
```
scala>     val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
val pipeline: org.apache.spark.ml.Pipeline = pipeline_09fb34f1d179
```
### 10. Esta línea se usa para entrenar el modelo. También ejecuta los indexadores automáticamente.
```
val model = pipeline.fit(trainingData)
```
Ejecución:
```
scala> val model = pipeline.fit(trainingData)
val model: org.apache.spark.ml.PipelineModel = pipeline_09fb34f1d179
```
### 11. Esta línea se usa para hacer predicciones con el modelo entrenado.
```
val predictions = model.transform(testData)
```
Ejecución:
```
scala> val predictions = model.transform(testData)
val predictions: org.apache.spark.sql.DataFrame = [label: double, features: vector ... 6 more fields]
```
### 12. Esta línea se usa para seleccionar y mostrar filas de ejemplo.
```
predictions.select("predictedLabel", "label", "features").show()
```
Ejecución:
```
scala> predictions.select("predictedLabel", "label", "features").show()
+--------------+-----+--------------------+
|predictedLabel|label|            features|
+--------------+-----+--------------------+
|           0.0|  0.0|(692,[121,122,123...|
|           0.0|  0.0|(692,[122,123,148...|
|           0.0|  0.0|(692,[123,124,125...|
|           0.0|  0.0|(692,[124,125,126...|
|           0.0|  0.0|(692,[124,125,126...|
|           0.0|  0.0|(692,[124,125,126...|
|           0.0|  0.0|(692,[125,126,127...|
|           0.0|  0.0|(692,[126,127,128...|
|           0.0|  0.0|(692,[126,127,128...|
|           0.0|  0.0|(692,[127,128,129...|
|           0.0|  0.0|(692,[154,155,156...|
|           0.0|  0.0|(692,[155,156,180...|
|           1.0|  1.0|(692,[100,101,102...|
|           1.0|  1.0|(692,[123,124,125...|
|           1.0|  1.0|(692,[124,125,126...|
|           1.0|  1.0|(692,[124,125,126...|
|           1.0|  1.0|(692,[124,125,126...|
|           1.0|  1.0|(692,[125,126,127...|
|           1.0|  1.0|(692,[125,126,127...|
|           1.0|  1.0|(692,[126,127,128...|
+--------------+-----+--------------------+
only showing top 20 rows
```
### 13. Esta línea se usa para seleccionar las columnas prediction y label, y calcular el error de prueba.
```
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

val accuracy = evaluator.evaluate(predictions)

println(s"Test Error = ${(1.0 - accuracy)}")
```
Ejecución:
```
scala> val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val evaluator: org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator = MulticlassClassificationEvaluator: uid=mcEval_6167a3da0dd7, metricName=accuracy, metricLabel=0.0, beta=1.0, eps=1.0E-15

scala>     val accuracy = evaluator.evaluate(predictions)
val accuracy: Double = 1.0

scala>     println(s"Test Error = ${(1.0 - accuracy)}")
Test Error = 0.0
``` 
### 14. Esta línea se usa para acceder al modelo de árbol de decisión entrenado y mostrar su estructura aprendida.
```
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")
```
Ejecución:
```
scala>     val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
val treeModel: org.apache.spark.ml.classification.DecisionTreeClassificationModel = DecisionTreeClassificationModel: uid=dtc_ee2e45ba8df4, depth=2, numNodes=5, numClasses=2, numFeatures=692

scala> println(s"Learned classification tree model:\n ${treeModel.toDebugString}")
Learned classification tree model:
 DecisionTreeClassificationModel: uid=dtc_ee2e45ba8df4, depth=2, numNodes=5, numClasses=2, numFeatures=692
  If (feature 434 <= 70.5)
   If (feature 99 in {2.0})
    Predict: 0.0
   Else (feature 99 not in {2.0})
    Predict: 1.0
  Else (feature 434 > 70.5)
   Predict: 0.0
``` 

