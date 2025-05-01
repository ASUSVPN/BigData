// Practica_Evaluatoria_u2
// Katherynne Plessmann
// Salomon Cruz Vidal

// 1. Cargar en un dataframe de la fuente de datos Iris.csv 
// Importar librerias
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}

// Iniciar la sesion
val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierEvaluation").getOrCreate()

// Se cargan los datos
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")

// 2 ¿Cuáles son los nombres de las columnas?
data.columns

// 3 ¿Cómo es el esquema?
data.printSchema()

//4. Imprime las primeras 5 columnas.
data.show(5)

// 5. Usa el método describe () para aprender más sobre los datos del DataFrame.
data.describe().show()

// 6. Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
val indexer = new StringIndexer()
  .setInputCol("species")
  .setOutputCol("label")
val indexerModel = indexer.fit(data)
val indexedData = indexerModel.transform(data)

// 7. Construya el modelo de clasificación y explique su arquitectura.
// Crea la columna features combinando columnas numéricas
val assembler = new VectorAssembler()
  .setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width"))
  .setOutputCol("features")

val finalData = assembler.transform(indexedData)


// Divide los datos en entrenamiento (70%) y prueba (30%)
val Array(train, test) = finalData.randomSplit(Array(0.7, 0.3), seed = 1234L)

// Definie la arquitectura de la red neuronal
val layers = Array[Int](4, 5, 4, 3)

// Configura el modelo
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Entrena el modelo
val model = trainer.fit(train)

//  Hace las predicciones con el conjunto de prueba
val result = model.transform(test)

//8. Imprima los resultados del modelo y de sus observaciones.
// Evalua el modelo
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"******** Test set accuracy = ${evaluator.evaluate(predictionAndLabels)} ********")
// Muestra
result.select("features", "label", "prediction").show(10)

