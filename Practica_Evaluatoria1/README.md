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
### 11. a) Seleccionamos el dataframe que declaramos que contiene los datos y le aplicamos el filtro sobre la columna de Close donde seleccionamos las celdas que sean menores a 600 y con la función count las contamos
```
df.filter($"Close"<600).count()
```
Ejecucion:
```
scala> df.filter($"Close"<600).count()
val res13: Long = 1218
```
### 11. b) Seleccionamos el dataframe declarado y contamos las celdas de la columna High que fueron superiores a 500 y para obtener el porcentaje de tiempo que fue mayor a 500 aplicamos una regla de 3 basandonos en el total de filas, nos da un resultado redondeado de 4.92 % de tiempo donde el valor High del dia fue mayor a 500 
```
(df.filter($"High">500).count()*1.0/df.count())*100
```
Ejecucion:
```
scala> (df.filter($"High">500).count()*1.0/df.count())*100
val res12: Double = 4.924543288324067
```
### 11. c) para saber la correlaccion de pearson entre la columna High y la Columna Volumen es necesario aplicar la funcion corr(), el resultado que nos da es -0.2096, lo cual interpretando significa que hay una correlacion negativa debil, es decir que cuando High Aumenta Volume Baja pero no en gran medida
```
df.select(corr("High","Volume")).show()
```
Ejecucion:
```
scala> df.select(corr("High","Volume")).show()
+--------------------+
|  corr(High, Volume)|
+--------------------+
|-0.20960233287942157|
+--------------------+
```
### 11. d) Para saber cuales son los valores maximos de la columna High por año agregamos una columna para separar el year de la columna Date, de la cual hacemos un nuevo dataframe que llamamos yeardf, despues seleccionamos las columnas Year y High y las agrupamos por año y con la funcion max sacamos los valores mas altos de año, despues de cada año seleccionamos el valor maximo de High y lo mostramos.
```
val yeardf = df.withColumn("Year",year(df("Date")))
val yearmaxs = yeardf.select($"Year",$"High").groupBy("Year").max()
yearmaxs.select($"Year",$"max(High)").show()
```
Ejecucion:
```
scala> val yeardf = df.withColumn("Year",year(df("Date")))
val yeardf: org.apache.spark.sql.DataFrame = [Date: date, Open: double ... 6 more fields]

scala> val yearmaxs = yeardf.select($"Year",$"High").groupBy("Year").max()
val yearmaxs: org.apache.spark.sql.DataFrame = [Year: int, max(Year): int ... 1 more field]

scala> yearmaxs.select($"Year",$"max(High)").show()
+----+------------------+
|Year|         max(High)|
+----+------------------+
|2015|        716.159996|
|2013|        389.159988|
|2014|        489.290024|
|2012|        133.429996|
|2016|129.28999299999998|
|2011|120.28000300000001|
+----+------------------+
```
### 11. e) Para saber cuales son los valores promedios de la columna Close por mes agregamos una columna para separar el month de la columna Date, de la cual hacemos un nuevo dataframe que llamamos monthdf, despues seleccionamos las columnas Month y Close y las agrupamos por mes y con la funcion mean sacamos el promedio de esos grupos y lo declaramos como un dataframe llamado monthavgs, finalmente de cada mes mostramos el promedio.
```
val monthdf = df.withColumn("Month",month(df("Date")))
val monthavgs = monthdf.select($"Month",$"Close").groupBy("Month").mean()
monthavgs.select($"Month",$"avg(Close)").show()
```
Ejecucion:
```
scala> val monthdf = df.withColumn("Month",month(df("Date")))
val monthdf: org.apache.spark.sql.DataFrame = [Date: date, Open: double ... 6 more fields]

scala> val monthavgs = monthdf.select($"Month",$"Close").groupBy("Month").mean()
val monthavgs: org.apache.spark.sql.DataFrame = [Month: int, avg(Month): double ... 1 more field]

scala> monthavgs.select($"Month",$"avg(Close)").show()
+-----+------------------+
|Month|        avg(Close)|
+-----+------------------+
|   12| 199.3700942358491|
|    1|212.22613874257422|
|    6| 295.1597153490566|
|    3| 249.5825228971963|
|    5|264.37037614150944|
|    9|206.09598121568627|
|    4|246.97514271428562|
|    8|195.25599892727263|
|    7|243.64747528037387|
|   10|205.93297300900903|
|   11| 194.3172275445545|
|    2| 254.1954634020619|
+-----+------------------+
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
#### Este permite ver cuantos dias el Close estuvo entre 300 y 600, el count va a permitir hacer ese conteo
```
df.filter(col("Close").between(300, 600)).count()
```
Ejecución:
```
val res7: Long = 421
```
#### Calcula la diferencia entre "High" y "Low" (se restan) y luego encuentra el día con la mayor volatilidad
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

#### Precio promedio de Close y Open de todos los registros, seleccionamos todas las columnas de Close y aplicamos la funcion funcion avg para obtener promedio, lo mismo hacemos para Open, y vemos que como se espera es como un promedio general del valor general de las acciones a lo largo del tiempo y que en promedio siempre se abre un poco mas bajo de valor de lo que Close ya que logicamente se intentar ofertar para vender.
```
val avgClose = df.select(avg("Close")).first().getDouble(0)
val avgOpen = df.select(avg("Open")).first().getDouble(0)
```
Ejecucion:
```
scala> val avgClose = df.select(avg("Close")).first().getDouble(0)
val avgClose: Double = 230.522453845909

scala> val avgOpen = df.select(avg("Open")).first().getDouble(0)
val avgOpen: Double = 230.39351086656092
```