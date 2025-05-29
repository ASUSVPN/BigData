 ![LOGO DE UABC](https://github.com/ASUSVPN/BigData/blob/unidad3/Unidad%203/UABC.png?raw=true)
## Facultad de Contaduria y Administracion 
### Maestría en Gestión de Tecnologías de la Información y la Comunicación

---

## Práctica Evaluatoria U3
### Tema: Modelos de Regresión Logística y Clasificación Multicapa  

---

**Alumno:** Katherynne Plessmann  
**Alumno:** Salomón Cruz Vidal  

**Maestro:** "Jose Christian Romero Hernandez"

---

**Fecha:** 28 de mayo de 2025  
**Ciudad:** Tijuana, Baja California  


# Índice
1. [Introducción](#id1)
2. [Marco teórico](#id2)
3. [Implementación](#id3)
4. [Resultados](#id4)
5. [Conclusiones](#id5)
6. [Referencias](#id6)

# 1. Introducción <a name="id1"></a>
El presente ejercicio se enfoca en predecir si un cliente aceptará o no una oferta bancaria, basándose en un conjunto de variables demográficas, económicas y de comportamiento bancario que fueron provistas de un dataset.

Para ello, se utilizaron algoritmos de aprendizaje supervisado implementados en Apache Spark usando Scala, lo que permitió trabajar de forma eficiente con los datos y automatizar múltiples ejecuciones del modelo. En particular, se emplearon dos técnicas de clasificación: regresión logística y perceptrón multicapa.

El enfoque experimental consistió en ejecutar cada modelo 30 veces. Con ello se buscó garantizar resultados estadísticamente más robustos y comparar el rendimiento promedio de ambos algoritmos. La métrica de evaluación seleccionada fue la exactitud (accuracy), por ser adecuada en contextos donde las clases están balanceadas, como es el caso de este conjunto de datos.

# 2. Marco Teórico <a name="id2"></a>
## 2.1 Regresión logística:
La **regresión logística** es una técnica estadística utilizada para analizar la relación entre una variable dependiente categórica (usualmente binaria, como 0 o 1) y un conjunto de variables independientes que pueden ser tanto categóricas como cuantitativas. Su objetivo es modelar la probabilidad de ocurrencia de un evento, en función de las variables predictoras consideradas relevantes para el fenómeno en estudio (Martínez Pérez & Pérez Martín, 2023).

Este tipo de regresión se basa en el uso de la función logística para estimar la probabilidad de que un caso pertenezca a una determinada categoría. A diferencia de la regresión lineal, donde se predice un valor continuo, en la regresión logística se predice la probabilidad de un resultado binario, como “éxito o fracaso” o “sí o no” (Hilbe, 2015).

La regresión logística es ampliamente utilizada en disciplinas como la medicina, la psicología, la economía y la inteligencia artificial, donde se requiere analizar decisiones categóricas o comportamientos discretos.

## 2.2 Perceptrón Multicapa:
El **perceptrón multicapa** (multilayer perceptron, MLP) es un tipo de red neuronal artificial ampliamente utilizado en el aprendizaje supervisado. Según IBM (2024), el procedimiento MLP permite generar un modelo predictivo para una o más variables dependientes en función de los valores de las variables predictoras, siendo útil tanto en clasificación como en regresión.

Esta arquitectura está compuesta por capas de nodos o neuronas, organizadas en una capa de entrada, una o más capas ocultas y una capa de salida. Cada neurona procesa la información recibida y la transmite a la siguiente capa. Durante el entrenamiento, los pesos de conexión entre las neuronas se ajustan progresivamente con el objetivo de mejorar la precisión del modelo (Nimmada & Basha, 2024).

El algoritmo MLP se caracteriza por su capacidad para aprender a partir de datos etiquetados, lo cual le permite realizar predicciones precisas sobre nuevos datos no vistos. Este tipo de red es particularmente eficaz cuando se requiere modelar relaciones complejas no lineales entre las variables de entrada y salida.

# 3. Implementación <a name="id3"></a>
Para la implementación de este proyecto se utilizó **Apache Spark** con el lenguaje de programación **Scala** y el editor de Codigo **Visual Studio Code**. A continuación, se describen las herramientas empleadas y las razones por las cuales fueron seleccionadas:

## Herramientas utilizadas

- **Apache Spark ML (Spark Machine Learning Library)**: Biblioteca moderna y recomendada para construir modelos de machine learning escalables sobre grandes volúmenes de datos utilizando DataFrames y Pipelines. Permite un desarrollo eficiente y optimizado para clústeres distribuidos. (Zaharia et al., 2016)
- **Scala**: Es el lenguaje nativo de Spark. Al usar Scala, se obtiene una mejor integración y rendimiento con el motor de ejecución de Spark. Además, permite escribir código conciso y expresivo para el procesamiento de datos. (Odersky, Spoon, & Venners, 2016)
- **Dataset de Marketing Bancario**: El conjunto de datos utilizado fue el "Bank Marketing Dataset" disponible en el repositorio UCI Machine Learning ([enlace al dataset](https://archive.ics.uci.edu/ml/datasets/Bank+Marketing)). Este conjunto es ampliamente utilizado para problemas de clasificación binaria, como predecir si un cliente se suscribirá a un producto bancario. (Moro, Laureano, & Cortez, 2014)

## Justificación del uso de Spark + Scala
- **Procesamiento distribuido y eficiente**: Spark permite procesar datos de forma distribuida en memoria, lo cual es mucho más rápido que tecnologías tradicionales basadas en disco, especialmente cuando se repiten procesos como entrenamiento de modelos múltiples veces.
- **Escalabilidad**: Aunque el dataset utilizado no es extremadamente grande, el uso de Spark asegura que el sistema puede escalar a datasets mucho más grandes si se desea.
- **Facilidad para construir pipelines de ML**: Con Spark ML, es sencillo construir pipelines de procesamiento que incluyan etapas como transformación de datos, codificación de variables categóricas, ensamblado de características y entrenamiento del modelo, todo de forma modular.
- **Automatización de experimentos**: Gracias a las facilidades del lenguaje Scala y las estructuras funcionales de Spark, se pudo automatizar la ejecución de 30 iteraciones por algoritmo, almacenar resultados y calcular estadísticas de rendimiento sin dificultad.

## Algoritmos implementados
Se implementaron y compararon los siguientes algoritmos:

- Regresión Logística (`LogisticRegression`)
- Perceptrón multicapa (`MultilayerPerceptronClassifier`)

Cada uno fue evaluado usando una división aleatoria del conjunto de datos en entrenamiento (70%) y prueba (30%), repitiendo este proceso 30 veces para obtener métricas más robustas y confiables, como la **accuracy promedio** y la **matriz de confusión**.

# 4. Resultados <a name="id4"></a>

**Logistic Regresion**
| Run | Accuracy |
|-----|----------|
| 1   | 0.8997   |
| 2   | 0.9058   |
| 3   | 0.9031   |
| 4   | 0.9008   |
| 5   | 0.9015   |
| 6   | 0.9004   |
| 7   | 0.9012   |
| 8   | 0.8972   |
| 9   | 0.9043   |
| 10  | 0.8998   |
| 11  | 0.9008   |
| 12  | 0.8975   |
| 13  | 0.9019   |
| 14  | 0.9046   |
| 15  | 0.8992   |
| 16  | 0.9040   |
| 17  | 0.9007   |
| 18  | 0.9034   |
| 19  | 0.9007   |
| 20  | 0.9008   |
| 21  | 0.8992   |
| 22  | 0.9033   |
| 23  | 0.8984   |
| 24  | 0.9014   |
| 25  | 0.8979   |
| 26  | 0.8974   |
| 27  | 0.9008   |
| 28  | 0.9001   |
| 29  | 0.9049   |
| 30  | 0.8994   |

Promedio=0.9010

Se corrieron 30 veces el modelo de **regresión logística** con el dataset bank-full.csv. En cada corrida se usó 70% para entrenar y 30% para probar, para que no se sobreajuste y la evaluación sea más confiable. La métrica que usamos fue la exactitud (accuracy), que mide qué tan bien predice el modelo si un cliente va a aceptar o no la oferta del banco. En promedio, el modelo logra un 90.10% de exactitud, lo cual está bastante bien para este tipo de problema y muestra que con las variables que tenemos, el modelo aprende bien a identificar el comportamiento de los clientes.


**Multilayer Perceptron Classifier**
| Corrida | Exactitud  |
|---------|------------|
|       1 | 0.8824     |
|       2 | 0.8825     |
|       3 | 0.8817     |
|       4 | 0.8793     |
|       5 | 0.8819     |
|       6 | 0.8836     |
|       7 | 0.8804     |
|       8 | 0.8848     |
|       9 | 0.8842     |
|      10 | 0.8823     |
|      11 | 0.8852     |
|      12 | 0.8804     |
|      13 | 0.8840     |
|      14 | 0.8846     |
|      15 | 0.8810     |
|      16 | 0.8839     |
|      17 | 0.8847     |
|      18 | 0.8836     |
|      19 | 0.8775     |
|      20 | 0.8813     |
|      21 | 0.8800     |
|      22 | 0.8842     |
|      23 | 0.8869     |
|      24 | 0.8841     |
|      25 | 0.8836     |
|      26 | 0.8806     |
|      27 | 0.8833     |
|      28 | 0.8878     |
|      29 | 0.8792     |
|      30 | 0.8801     |
|Promedio | 0.8826361682159323 |

Se realizaron 30 corridas independientes del modelo **Multilayer Perceptron Classifier** sobre el conjunto de datos bank-full.csv. En cada corrida se realizó un 70% de entrenamiento y 30% de prueba, con el objetivo de evitar sobreajuste y obtener una evaluación robusta del modelo. La métrica utilizada fue exactitud (accuracy), que indica la proporción de predicciones correctas sobre el total de ejemplos evaluados. El promedio de exactitud tras 30 corridas: 0.8826, el modelo logra una alta exactitud en predecir si un cliente aceptará o no la oferta del banco, basándose en las características del cliente y de su comportamiento bancario.

# 5. Conclusiones <a name="id5"></a>

En este trabajo pudimos ver que tanto la regresión logística como el perceptrón multicapa son modelos útiles para predecir si un cliente aceptará una oferta bancaria, usando las variables del dataset.

La regresión logística obtuvo un mejor rendimiento promedio (90.1% de exactitud), lo que indica que es un modelo bastante bueno para este tipo de problemas con datos balanceados y variables claras.

Por otro lado, el perceptrón multicapa también funcionó bien (con un promedio del 88.26%), mostrando que las redes neuronales pueden capturar relaciones más complejas, pero en este caso no superó a la regresión logística.

Además, al correr cada modelo 30 veces con diferentes divisiones de datos, logramos obtener resultados más confiables y evitar sesgos o sobreajuste.

Finalmente, el uso de Apache Spark y Scala fue fundamental para manejar los datos de forma eficiente y automatizar los experimentos, lo que facilita la reproducibilidad y escalabilidad del proyecto.

En resumen, para este caso específico, la regresión logística es la mejor opción, pero el perceptrón multicapa sigue siendo una alternativa válida si se desea explorar modelos más complejos.


# 6. Referencias <a name="id6"></a>
## Referencias

Hilbe, J. M. (2015). *Practical Guide to Logistic Regression*. PDFDrive. https://ftp.idu.ac.id/wp-content/uploads/ebook/ip/REGRESI%20LOGISTIK/Practical%20Guide%20to%20Logistic%20Regression%20(%20PDFDrive%20).pdf

IBM. (2024, 30 de septiembre). *Perceptrón multicapa*. IBM Docs. https://www.ibm.com/docs/es/spss-statistics/saas?topic=networks-multilayer-perceptron

Martínez Pérez, J. A., & Pérez Martín, P. S. (2023). Regresión logística. *Formación Continuada - Metodología y Técnicas*. https://doi.org/10.1016/j.semerg.2023.102086

Moro, S., Laureano, R., & Cortez, P. (2014). *Bank Marketing Dataset*. UCI Machine Learning Repository. https://archive.ics.uci.edu/ml/datasets/Bank+Marketing

Nimmada, G., & Basha, S. M. (2024). A novel method for predicting the flight price using multilayer perceptron algorithm compared with support vector machine algorithm. *2024 Ninth International Conference on Science Technology Engineering and Mathematics (ICONSTEM)*, 1–5. https://doi.org/10.1109/ICONSTEM60960.2024.10568803

Odersky, M., Spoon, L., & Venners, B. (2016). *Programming in Scala* (3rd ed.). Artima Inc.

Zaharia, M., Chowdhury, M., Das, T., Dave, A., Ma, J., McCauley, M., Franklin, M. J., Shenker, S., & Stoica, I. (2016). Apache Spark: A unified engine for big data processing. *Communications of the ACM, 59*(11), 56-65. https://doi.org/10.1145/2934664



