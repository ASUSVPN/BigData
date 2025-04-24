// Practica_Evaluatoria_u2
// Katherynne Plessmann
// Salomon Cruz Vidal

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
