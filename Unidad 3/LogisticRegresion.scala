// Practica_Evaluatoria_u3
// Katherynne Plessmann
// Salomon Cruz Vidal

//Importamos librerias
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, OneHotEncoder}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.sql.functions.col


//Disminuimos los mensajes de los Logs
Logger.getLogger("org").setLevel(Level.ERROR)

//Creamos la sesion de Spark
val spark = SparkSession.builder().getOrCreate()

//Cargamos la data de bank-full.csv en un dataframe
val data = spark.read.option("header", "true").option("inferSchema", "true").option("delimiter", ";").format("csv").load("bank-full.csv")

import spark.implicits._ 
//seleccionamos las columnas y renombramos la columba objetivo
val logregdataall = data.select($"age", $"job", $"marital", $"education", $"default", $"balance",$"housing", $"loan", $"contact", $"day", $"month", $"duration",$"campaign", $"pdays", $"previous", $"poutcome", $"y".as("label"))

//eliminamos filas con valores nulos para limpiar mas los datos
val logregdata = logregdataall.na.drop()

// Indexadores para features categóricas, convertimos valores string a numericos
val jobIndexer        = new StringIndexer().setInputCol("job").setOutputCol("jobIndex")
val maritalIndexer    = new StringIndexer().setInputCol("marital").setOutputCol("maritalIndex")
val educationIndexer  = new StringIndexer().setInputCol("education").setOutputCol("educationIndex")
val defaultIndexer    = new StringIndexer().setInputCol("default").setOutputCol("defaultIndex")
val housingIndexer    = new StringIndexer().setInputCol("housing").setOutputCol("housingIndex")
val loanIndexer       = new StringIndexer().setInputCol("loan").setOutputCol("loanIndex")
val contactIndexer    = new StringIndexer().setInputCol("contact").setOutputCol("contactIndex")
val monthIndexer      = new StringIndexer().setInputCol("month").setOutputCol("monthIndex")
val poutcomeIndexer   = new StringIndexer().setInputCol("poutcome").setOutputCol("poutcomeIndex")

// Indexador para label
val labelIndexer      = new StringIndexer().setInputCol("label").setOutputCol("labelIndexed")

// OneHotEncoders para hacer vectores binarios de las columas numericas
val jobEncoder        = new OneHotEncoder().setInputCol("jobIndex").setOutputCol("jobVec")
val maritalEncoder    = new OneHotEncoder().setInputCol("maritalIndex").setOutputCol("maritalVec")
val educationEncoder  = new OneHotEncoder().setInputCol("educationIndex").setOutputCol("educationVec")
val defaultEncoder    = new OneHotEncoder().setInputCol("defaultIndex").setOutputCol("defaultVec")
val housingEncoder    = new OneHotEncoder().setInputCol("housingIndex").setOutputCol("housingVec")
val loanEncoder       = new OneHotEncoder().setInputCol("loanIndex").setOutputCol("loanVec")
val contactEncoder    = new OneHotEncoder().setInputCol("contactIndex").setOutputCol("contactVec")
val monthEncoder      = new OneHotEncoder().setInputCol("monthIndex").setOutputCol("monthVec")
val poutcomeEncoder   = new OneHotEncoder().setInputCol("poutcomeIndex").setOutputCol("poutcomeVec")


// VectorAssembler para poner en una columna todas las features
val assembler = new VectorAssembler()
  .setInputCols(Array(
    "age", "balance", "day", "duration", "campaign", "pdays", "previous",
    "jobVec", "maritalVec", "educationVec", "defaultVec", "housingVec",
    "loanVec", "contactVec", "monthVec", "poutcomeVec"
  ))
  .setOutputCol("features")


//Creamos un objeto del modelo de regresion con la columna de features y el objetivo
val lr = new LogisticRegression()
  .setLabelCol("labelIndexed")
  .setFeaturesCol("features")

//se crean las fases de entrenamiento
val pipeline = new Pipeline().setStages(Array(
  jobIndexer, maritalIndexer, educationIndexer, defaultIndexer, housingIndexer,
  loanIndexer, contactIndexer, monthIndexer, poutcomeIndexer, labelIndexer,
  jobEncoder, maritalEncoder, educationEncoder, defaultEncoder, housingEncoder,
  loanEncoder, contactEncoder, monthEncoder, poutcomeEncoder,
  assembler, lr
))

//Se esablece la metrica a evaluar accuracy
val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("labelIndexed")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")

var accuracies = scala.collection.mutable.ArrayBuffer.empty[Double]

//entrenamos el modelo en un ciclo para calcular la accuracy en cada ocasion
println(f"Run\tAccuracy")
for (i <- 1 to 30) {
  val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = System.currentTimeMillis() + i)
  val model = pipeline.fit(training)
  val results = model.transform(test)

  val accuracy = evaluator.evaluate(results)
  accuracies += accuracy
  println(f"$i%2d\t$accuracy%.4f")
}

//promediamos el accuracy despues de 30 corridas
val avgAccuracy = accuracies.sum / accuracies.length
println(f"\nAverage accuracy after 30 runs: $avgAccuracy%.4f")
