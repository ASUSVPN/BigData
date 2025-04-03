////////////////////////////////////////////
//// LINEAR REGRESSION EXERCISE ///////////
/// Coplete las tareas comentadas ///
/////////////////////////////////////////

// Import LinearRegression
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression

// Opcional: Utilice el siguiente codigo para configurar errores
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)


// Inicie una simple Sesion Spark
val spark = SparkSession.builder().getOrCreate()

// Utilice Spark para el archivo csv Clean-Ecommerce .
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Clean-Ecommerce.csv")

// Imprima el schema en el DataFrame.
data.printSchema

// Imprima un renglon de ejemplo del DataFrane.
data.head(1)

//////////////////////////////////////////////////////
//// Configure el DataFrame para Machine Learning ////
//////////////////////////////////////////////////////

// Transforme el data frame para que tome la forma de
// ("label","features")
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}

// Importe VectorAssembler y Vectors
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Renombre la columna Yearly Amount Spent como "label"
// Tambien de los datos tome solo la columa numerica 
// Deje todo esto como un nuevo DataFrame que se llame df
data.columns
val df = data.select(data("Yearly Amount Spent").as("label"), $"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership")

// Configure las columnas de entrada de donde se supone que leemos los valores.
// Llamar a esto nuevo assambler.
// Que el objeto assembler convierta los valores de entrada a un vector
val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App", "Time on Website", "Length of Membership")).setOutputCol("features")

// Utilice el assembler para transformar nuestro DataFrame a dos columnas: label and features
// Utilice el objeto VectorAssembler para convertir las columnas de entrada del df
// a una sola columna de salida de un arreglo llamado "features"
val output = assembler.transform(df).select($"label", $"features")

// Crear un objeto para modelo de regresion linea.
val lr = new LinearRegression()

// Ajuste el modelo para los datos y llame a este modelo lrModelo
val lrModel = lr.fit(output)

// Imprima the  coefficients y intercept para la regresion lineal
println(s"Coeficientes: ${lrModel.coefficients}")
println(s"Intercepto: ${lrModel.intercept}")


// Utilize metodo .summary de nuestro  modelo para crear un objeto llamado trainingSummary
val trainingSummary = lrModel.summary

// Resuma el modelo sobre el conjunto de entrenamiento imprima la salida de algunas metricas!
// Muestre los valores de residuals, el RMSE, el MSE, y tambien el R^2 .

trainingSummary.residuals.show()

println(s"RMSE (Raíz del Error Cuadrático Medio): ${trainingSummary.rootMeanSquaredError}")
println(s"MSE (Error Cuadrático Medio): ${trainingSummary.meanSquaredError}")
println(s"R2 (Coeficiente de determinación): ${trainingSummary.r2}")
