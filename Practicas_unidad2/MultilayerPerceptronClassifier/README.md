# Practica_MultiLayerPerceptron
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Se importan los paquetes
```
println(s"******** Importing classifier and evaluator ********")
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
```
Ejecución:
```
scala> println(s"******** Importing classifier and evaluator ********")
******** Importing classifier and evaluator ********

scala> import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier

scala> import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

scala> import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession
```
### 2. Aquí se hace la creación de la sesión e importación de los datos
```
println(s"******** Creating session and loading data ********")
val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierExample").getOrCreate()

// Load the data stored in LIBSVM format as a DataFrame.
val data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")
```
Ejecución:
```
scala> println(s"******** Creating session and loading data ********")
******** Creating session and loading data ********

scala> val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierExample").getOrCreate()
warning: 1 deprecation (since 2.13.3); for details, enable `:setting -deprecation` or `:replay -deprecation`
25/04/23 19:56:06 WARN SparkSession: Using an existing Spark session; only runtime SQL configurations will take effect.
val spark: org.apache.spark.sql.SparkSession = org.apache.spark.sql.SparkSession@19932a6a

scala> val data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")
25/04/23 19:57:34 WARN LibSVMFileFormat: 'numFeatures' option not specified, determining the number of features by going though the input. If you know the number in advance, please specify it via 'numFeatures' option to avoid the extra scan.
val data: org.apache.spark.sql.DataFrame = [label: double, features: vector]
```
### 3. Se separan los datos para el entranamiento
```
// Split the data into train and test
println(s"******** Splitting the data into training and testing ********")
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)
```
Ejecución:
```
scala> println(s"******** Splitting the data into training and testing ********")
******** Splitting the data into training and testing ********

scala> val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val splits: Array[org.apache.spark.sql.Dataset[org.apache.spark.sql.Row]] = Array([label: double, features: vector], [label: double, features: vector])

scala> val train = splits(0)
val train: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]

scala> val test = splits(1)
val test: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
```
### 4. Se especifican las capas de la red neuronal
```
// specify layers for the neural network:
// input layer of size 4 (features), two intermediate of size 5 and 4
// and output of size 3 (classes)
println()
println(s"******** Specifying the layers for neural network ********")
val layers = Array[Int](4, 5, 4, 3)
```
Ejecución:
```
scala> println(s"******** Specifying the layers for neural network ********")
******** Specifying the layers for neural network ********

scala> val layers = Array[Int](4, 5, 4, 3)
val layers: Array[Int] = Array(4, 5, 4, 3)
```

### 5. Aquí es la creación del entrenador y la configuración de parámetros
```
// create the trainer and set its parameters
println(s"******** Create the trainer ********")
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
```
Ejecución:
```
scala> println(s"******** Create the trainer ********")
******** Create the trainer ********

scala> val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
val trainer: org.apache.spark.ml.classification.MultilayerPerceptronClassifier = mlpc_b8d579e5fb30
```

### 6. Se entrena el modelo
```
// train the model
println()
println(s"******** Training the model ********")
val model = trainer.fit(train)
```
Ejecución:
```
scala> println(s"******** Training the model ********")
******** Training the model ********

scala> val model = trainer.fit(train)
val model: org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel = MultilayerPerceptronClassificationModel: uid=mlpc_b8d579e5fb30, numLayers=4, numClasses=3, numFeatures=4
```

### 7. Se calcula la precisión en el conjunto de prueba.
```
// compute accuracy on the test set
println()
println(s"******** Computing accuracy ********")
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println()
println(s"******** Test set accuracy = ${evaluator.evaluate(predictionAndLabels)} ********")
```
Ejecución:
```
scala> println(s"******** Computing accuracy ********")
******** Computing accuracy ********

scala> val result = model.transform(test)
val result: org.apache.spark.sql.DataFrame = [label: double, features: vector ... 3 more fields]

scala> val predictionAndLabels = result.select("prediction", "label")
val predictionAndLabels: org.apache.spark.sql.DataFrame = [prediction: double, label: double]

scala> val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
val evaluator: org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator = MulticlassClassificationEvaluator: uid=mcEval_6c023273fba4, metricName=accuracy, metricLabel=0.0, beta=1.0, eps=1.0E-15

scala> println(s"******** Test set accuracy = ${evaluator.evaluate(predictionAndLabels)} ********")
******** Test set accuracy = 0.9523809523809523 ********
```