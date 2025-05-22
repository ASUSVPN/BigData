// Practica_Evaluatoria_u2
// Katherynne Plessmann
// Salomon Cruz Vidal

// 1. Cargar en un dataframe de la fuente de datos Iris.csv 
// Importar librerias
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import scala.collection.mutable.ListBuffer
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}

// Iniciar la sesion
val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierEvaluation").getOrCreate()

// Se cargan los datos
val data = spark.read.option("header", "true").option("inferSchema", "true").option("delimiter", ";").format("csv").load("bank-full.csv")

// 2 ¿Cuáles son los nombres de las columnas?
data.columns

// 3 ¿Cómo es el esquema?
data.printSchema()

//4. Imprime las primeras 5 columnas.
data.show(5)

// 5. Usa el método describe () para aprender más sobre los datos del DataFrame.
data.describe().show()

// 6. Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
//Convertir la etiqueta "y" a "label"
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label").fit(data)
val withLabel = labelIndexer.transform(data)

// Convertir las columnas categóricas seleccionadas a numéricas
val jobIndexer = new StringIndexer().setInputCol("job").setOutputCol("jobIndex").fit(withLabel)
val maritalIndexer = new StringIndexer().setInputCol("marital").setOutputCol("maritalIndex").fit(withLabel)
val educationIndexer = new StringIndexer().setInputCol("education").setOutputCol("educationIndex").fit(withLabel)
val defaultIndexer = new StringIndexer().setInputCol("default").setOutputCol("defaultIndex").fit(withLabel)
val housingIndexer = new StringIndexer().setInputCol("housing").setOutputCol("housingIndex").fit(withLabel)
val loanIndexer = new StringIndexer().setInputCol("loan").setOutputCol("loanIndex").fit(withLabel)

// Aplicar los indexadores en cadena
val dataIndexed = loanIndexer.transform(housingIndexer.transform(defaultIndexer.transform(educationIndexer.transform(maritalIndexer.transform(jobIndexer.transform(withLabel))))))
dataIndexed.printSchema()

// 7. Construya el modelo de clasificación y explique su arquitectura.
// Crea la columna features combinando columnas numéricas
val assembler = new VectorAssembler().setInputCols(Array("age", "balance", "duration", "campaign", "jobIndex", "maritalIndex", "educationIndex", "defaultIndex", "housingIndex", "loanIndex")).setOutputCol("features")

val finalData = assembler.transform(dataIndexed)
// Divide los datos en entrenamiento (70%) y prueba (30%)
val Array(train, test) = finalData.randomSplit(Array(0.7, 0.3), seed = 1234L)

// Definie la arquitectura de la red neuronal
val layers = Array[Int](10, 8, 4, 2) 

// Configura el modelo
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("label").setFeaturesCol("features").setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Entrena el modelo
val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("label")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")

val resultados = ListBuffer[(Int, Double)]()

for (i <- 1 to 30) {
  println(s"Ejecutando recorrido: $i")

  val Array(train, test) = finalData.randomSplit(Array(0.7, 0.3), seed = System.nanoTime())

  val model = trainer.fit(train)
  val result = model.transform(test)
  val acc = evaluator.evaluate(result.select("prediction", "label"))

  resultados += ((i, acc))
  println(f"Corrida $i%2d -- Exactitud: ${acc}%.4f")
}

println("\nResultados de las 30 corridas:\n")
val c1 = "Corrida"
val c2 = "Exactitud"
println(f"| $c1%-7s | $c2%-10s |")
println("|---------|------------|")
resultados.foreach { case (i, acc) =>
  println(f"| $i%7d | ${acc}%-10.4f |")
}

val promedio = resultados.map(_._2).sum / resultados.length
println(f"\nPromedio de exactitud tras 30 corridas: ${promedio}%.4f")

