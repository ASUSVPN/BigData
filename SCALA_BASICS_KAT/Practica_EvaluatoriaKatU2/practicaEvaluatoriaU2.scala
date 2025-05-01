// Practica_Evaluatoria_u2
// Katherynne Plessmann
// Salomon Cruz Vidal

// 1. Cargar en un dataframe de la fuente de datos Iris.csv 
// Importar librerias
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}


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
  .fit(data)

// 7. Construya el modelo de clasificación y explique su arquitectura.


//8. Imprima los resultados del modelo y de sus observaciones.