# Practica_x
## Katherynne Plessmann
## Salomon Cruz Vidal

### 1. Esta linea 
```
line
```
Ejecución:
```
scala
```

scala> import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession


scala> import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.regression.LinearRegression

scala> import org.apache.log4j._
import org.apache.log4j._

scala> Logger.getLogger("org").setLevel(Level.ERROR)

scala> val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Clean-Ecommerce.csv")
val data: org.apache.spark.sql.DataFrame = [Email: string, Avatar: string ... 5 more fields]

scala> data.printSchema
warning: 1 deprecation (since 2.13.3); for details, enable `:setting -deprecation` or `:replay -deprecation`
root
 |-- Email: string (nullable = true)
 |-- Avatar: string (nullable = true)
 |-- Avg Session Length: double (nullable = true)
 |-- Time on App: double (nullable = true)
 |-- Time on Website: double (nullable = true)
 |-- Length of Membership: double (nullable = true)
 |-- Yearly Amount Spent: double (nullable = true)

 scala> data.head(1)
val res2: Array[org.apache.spark.sql.Row] = Array([mstephenson@fernandez.com,Violet,34.49726772511229,12.65565114916675,39.57766801952616,4.0826206329529615,587.9510539684005])

scala> val colnames = data.columns
val colnames: Array[String] = Array(Email, Avatar, Avg Session Length, Time on App, Time on Website, Length of Membership, Yearly Amount Spent)

scala> val firstrow = data.head(1)(0)
val firstrow: org.apache.spark.sql.Row = [mstephenson@fernandez.com,Violet,34.49726772511229,12.65565114916675,39.57766801952616,4.0826206329529615,587.9510539684005]

scala> println("\n")

scala> println("Example data row")
Example data row

scala> for(ind <- Range(1, colnames.length)){
     |     println(colnames(ind))
     |     println(firstrow(ind))
     |     println("\n")
     | }
Avatar
Violet

Avg Session Length
34.49726772511229

Time on App
12.65565114916675

Time on Website
39.57766801952616

Length of Membership
4.0826206329529615

Yearly Amount Spent
587.9510539684005

scala> import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.VectorAssembler

scala> import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.linalg.Vectors

scala> val df = data.select(data("Yearly Amount Spent").as("label"), $"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership")
val df: org.apache.spark.sql.DataFrame = [label: double, Avg Session Length: double ... 3 more fields]

scala> val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App", "Time on Website", "Length of Membership")).setOutputCol("features")
val assembler: org.apache.spark.ml.feature.VectorAssembler = VectorAssembler: uid=vecAssembler_932846c0ce8f, handleInvalid=error, numInputCols=4

scala> val output = assembler.transform(df).select($"label", $"features")
val output: org.apache.spark.sql.DataFrame = [label: double, features: vector]

scala> val lr = new LinearRegression()
val lr: org.apache.spark.ml.regression.LinearRegression = linReg_5eb14df7b07e

scala> val lrModel = lr.fit(output)
25/03/26 21:45:17 WARN Instrumentation: [443033d6] regParam is zero, which might cause numerical instability and overfitting.
25/03/26 21:45:17 WARN InstanceBuilder: Failed to load implementation from:dev.ludovic.netlib.lapack.JNILAPACK
val lrModel: org.apache.spark.ml.regression.LinearRegressionModel = LinearRegressionModel: uid=linReg_5eb14df7b07e, numFeatures=4

scala> println(s"Coeficientes: ${lrModel.coefficients}")
Coeficientes: [25.734271084670716,38.709153810828816,0.43673883558514964,61.57732375487594]

scala> println(s"Intercepto: ${lrModel.intercept}")
Intercepto: -1051.5942552990748

scala> val trainingSummary = lrModel.summary
val trainingSummary: org.apache.spark.ml.regression.LinearRegressionTrainingSummary = org.apache.spark.ml.regression.LinearRegressionTrainingSummary@1cc7ed26

scala> trainingSummary.residuals.show()
+-------------------+
|          residuals|
+-------------------+
| -6.788234090018818|
| 11.841128565326073|
| -17.65262700858966|
| 11.454889631178617|
| 7.7833824373080915|
|-1.8347332184773677|
|  4.620232401352382|
| -8.526545950978175|
| 11.012210896516763|
|-13.828032682158891|
| -16.04456458615175|
|  8.786634365463442|
| 10.425717191807507|
| 12.161293785003522|
|  9.989313714461446|
| 10.626662732649379|
|  20.15641408428496|
|-3.7708446586326545|
| -4.129505481591934|
|  9.206694655890487|
+-------------------+
only showing top 20 rows

scala> println(s"RMSE (Raíz del Error Cuadrático Medio): ${trainingSummary.rootMeanSquaredError}")
RMSE (Raíz del Error Cuadrático Medio): 9.923256785022229

scala> println(s"MSE (Error Cuadrático Medio): ${trainingSummary.meanSquaredError}")
MSE (Error Cuadrático Medio): 98.47102522148971

scala> println(s"R2 (Coeficiente de determinación): ${trainingSummary.r2}")
R2 (Coeficiente de determinación): 0.9843155370226727