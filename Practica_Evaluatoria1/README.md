# Practica_Evaluatoria_1
## Katherynne Plessmann
## Salomon Cruz Vidal

### Esta linea nos permite importar lo necesario para poder trabajar con datos en Spark
```
import org.apache.spark.sql.SparkSession
```
Ejecución:
```
scala> import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession
```
### 1. Se crea una sesion en Spark, necesaria para ejecutar operaciones de datos
```
val spark = SparkSession.builder().getOrCreate()
```
Ejecución:
```
val spark: org.apache.spark.sql.SparkSession = org.apache.spark.sql.SparkSession@246b2469
```
### 2. Se carga un archivo csv, el cual se llama Netflix_2011_2016.csv
```
val df = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")
```
Ejecución:
```
val df: org.apache.spark.sql.DataFrame = [Date: date, Open: double ... 5 more fields]
```
### 3. Muestra los nombres de las columnas 
```
df.columns
```
Ejecución:
```
val res0: Array[String] = Array(Date, Open, High, Low, Close, Volume, Adj Close)
```
### 4. Muestra la estructura del dataframe (nombres y tipos de columnas)
```
df.printSchema()
```
Ejecución:
```
root
 |-- Date: date (nullable = true)
 |-- Open: double (nullable = true)
 |-- High: double (nullable = true)
 |-- Low: double (nullable = true)
 |-- Close: double (nullable = true)
 |-- Volume: integer (nullable = true)
 |-- Adj Close: double (nullable = true)
```
### 5. Obtiene las primero 5 filas en forma de array
```
df.head(5)
```
Ejecución:
```
val res2: Array[org.apache.spark.sql.Row] = Array([2011-10-24,119.100002,120.28000300000001,115.100004,118.839996,120460200,16.977142], [2011-10-25,74.899999,79.390001,74.249997,77.370002,315541800,11.052857000000001], [2011-10-26,78.73,81.420001,75.399997,79.400002,148733900,11.342857], [2011-10-27,82.179998,82.71999699999999,79.249998,80.86000200000001,71190000,11.551428999999999], [2011-10-28,80.280002,84.660002,79.599999,84.14000300000001,57769600,12.02])
```
### Esta es otra forma ordenada para imprimir las primeras 5 lineas 
```
df.show(5)
```
Ejecución:
```
+----------+----------+------------------+----------+-----------------+---------+------------------+
|      Date|      Open|              High|       Low|            Close|   Volume|         Adj Close|
+----------+----------+------------------+----------+-----------------+---------+------------------+
|2011-10-24|119.100002|120.28000300000001|115.100004|       118.839996|120460200|         16.977142|
|2011-10-25| 74.899999|         79.390001| 74.249997|        77.370002|315541800|11.052857000000001|
|2011-10-26|     78.73|         81.420001| 75.399997|        79.400002|148733900|         11.342857|
|2011-10-27| 82.179998| 82.71999699999999| 79.249998|80.86000200000001| 71190000|11.551428999999999|
|2011-10-28| 80.280002|         84.660002| 79.599999|84.14000300000001| 57769600|             12.02|
+----------+----------+------------------+----------+-----------------+---------+------------------+
only showing top 5 rows
```
### 6. Nos refleja una estadistica de cada columna
```
df.describe().show()
```
Ejecución:
```
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|summary|              Open|              High|               Low|             Close|              Volume|         Adj Close|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|  count|              1259|              1259|              1259|              1259|                1259|              1259|
|   mean|230.39351086656092|233.97320872915006|226.80127876251044|  230.522453845909|2.5634836060365368E7|55.610540036536875|
| stddev|164.37456353264244| 165.9705082667129| 162.6506358235739|164.40918905512854| 2.306312683388607E7|35.186669331525486|
|    min|         53.990001|         55.480001|             52.81|              53.8|             3531300|          7.685714|
|    max|        708.900017|        716.159996|        697.569984|        707.610001|           315541800|        130.929993|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
```
### 7. Agrega una nueva columna que se llama "HV Ratio" la cual se calcula dividiendo "High" entre "Volume"
```
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))
```
Ejecución:
```
val df2: org.apache.spark.sql.DataFrame = [Date: date, Open: double ... 6 more fields]
```
### 8. Se selecciona date y open para ver el dia con el valor de open mas alto, luego se debe de llamar de forma descendente y mostrar el primer valor
```
df.select($"Date", $"Open").orderBy($"Open".desc).show(1)

```
Ejecución:
```
+----------+----------+
|      Date|      Open|
+----------+----------+
|2015-07-14|708.900017|
+----------+----------+
only showing top 1 row
```
### 9. Significado de la columna Cerrar "Close" (en el contexto de informacion financiera)
```
Entendemos, que es el ultimo precio en el que se encuentra un producto o un activo financiero antes de cerrar su operacion diaria. Pasa mucho en las bolsas de valores, que el mercado cierra en la tarde y si revisas en un horario donde ya este cerrado solo podras ver el valor de close, hasta que se vuelva a abrir al dia siguiente y se empiece a mover el precio.
```
### 10. Se obtiene el valor minimo y maximo de la columna volume
```
df.select(max("Volume")).show()
df.select(min("Volume")).show()
```
Ejecución:
```
+-----------+
|max(Volume)|
+-----------+
|  315541800|
+-----------+

+-----------+
|min(Volume)|
+-----------+
|    3531300|
+-----------+
```

### EXTRA
#### Se puede encontrar la fecha con el mayor volumen de transacciones, agregando date y con el descendente de Volume (es una mezcla entre el ejercicio 8 y 10)
```
df.select($"Date", $"Volume").orderBy($"Volume".desc).show(1)
```
Ejecución:
```
+----------+---------+
|      Date|   Volume|
+----------+---------+
|2011-10-25|315541800|
+----------+---------+
only showing top 1 row
```
# Este permite ver cuantos dias el Close estuvo entre 300 y 600, el count va a permitir hacer ese conteo
```
df.filter(col("Close").between(300, 600)).count()
```
Ejecución:
```
val res7: Long = 421
```
# Calcula la diferencia entre "High" y "Low" (se restan) y luego encuentra el día con la mayor volatilidad
```
val df3 = df.withColumn("Volatility", df("High") - df("Low"))
df3.select($"Date", $"Volatility").orderBy($"Volatility".desc).show(1)
```
Ejecución:
```
scala> val df3 = df.withColumn("Volatility", df("High") - df("Low"))
val df3: org.apache.spark.sql.DataFrame = [Date: date, Open: double ... 6 more fields]

scala> df3.select($"Date", $"Volatility").orderBy($"Volatility".desc).show(1)
+----------+-----------------+
|      Date|       Volatility|
+----------+-----------------+
|2013-10-22|67.65999899999997|
+----------+-----------------+
only showing top 1 row
```