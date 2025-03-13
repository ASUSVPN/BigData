// Practica_Evaluatoria_1
// Katherynne Plessmann
// Salomon Cruz Vidal

import org.apache.spark.sql.SparkSession

// 1. Comienza una simple sesión Spark.
val spark = SparkSession.builder().getOrCreate()

// 2. Cargue el archivo Netflix Stock CSV en dataframe llamado df, haga que Spark infiera los tipos de datos.

val df = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")

// 3. ¿Cuáles son los nombres de las columnas?
df.columns

// 4. ¿Cómo es el esquema?
df.printSchema()

// 5. Imprime las primeras 5 renglones.
df.head(5)
df.show(5)

// 6. Usa el método describe () para aprender sobre el DataFrame.
df.describe().show()

// 7. Crea un nuevo dataframe con una columna nueva llamada “HV Ratio” que es la
//relación que existe entre el precio de la columna “High” frente a la columna
//“Volumen” de acciones negociadas por un día. Hint - es una operación
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))

// 8. ¿Qué día tuvo el pico más alto en la columna “Open”?
df.select($"Date", $"Open").orderBy($"Open".desc).show(1)

// La 9 se responde en el README

// 10. ¿Cuál es el máximo y mínimo de la columna “Volumen”?
df.select(max("Volume")).show()
df.select(min("Volume")).show()

// 11. a ¿Cuántos días fue la columna “Close” inferior a $ 600?
df.filter($"Close"<600).count()

// 11. b) ¿Qué porcentaje del tiempo fue la columna “High” mayor que $ 500?
(df.filter($"High">500).count()*1.0/df.count())*100

// 11. c) ¿Cuál es la correlación de Pearson entre columna “High” y la columna “Volumen”?
df.select(corr("High","Volume")).show()

// 11 d) ¿Cuál es el máximo de la columna “High” por año?
val yeardf = df.withColumn("Year",year(df("Date")))
val yearmaxs = yeardf.select($"Year",$"High").groupBy("Year").max()
yearmaxs.select($"Year",$"max(High)").show()

// 11 e) ¿Cuál es el promedio de la columna “Close” para cada mes del calendario?
val monthdf = df.withColumn("Month",month(df("Date")))
val monthavgs = monthdf.select($"Month",$"Close").groupBy("Month").mean()
monthavgs.select($"Month",$"avg(Close)").show()

//EXTRAS
df.select($"Date", $"Volume").orderBy($"Volume".desc).show(1)

df.filter(col("Close").between(300, 600)).count()

val df3 = df.withColumn("Volatility", df("High") - df("Low"))
df3.select($"Date", $"Volatility").orderBy($"Volatility".desc).show(1)

val avgClose = df.select(avg("Close")).first().getDouble(0)

val avgOpen = df.select(avg("Open")).first().getDouble(0)