# VIDEOCLIP MULTIIMAGEN + THREADS

## 1. Descripción del proyecto

El proyecto genera un videoclip de 10 segundos utilizando varias imágenes y un archivo de audio WAV.

El audio es analizado mediante un arreglo `short[]` para obtener su nivel de intensidad. Las imágenes son procesadas mediante matrices y se aplican diferentes efectos visuales.

El proyecto permite comparar la generación de frames utilizando procesamiento serial y procesamiento paralelo mediante threads.

## 2. Integrantes

- Jhony Laqui Cutipa

## 3. Configuración utilizada

- FPS: 12
- Duración: 10 segundos
- Total de frames: 120
- Cantidad de imágenes: 4
- Audio: audio_base.wav
- Modo: both
- Threads probados: 2 y 4

## 4. Imágenes utilizadas

Se utilizaron cuatro imágenes para generar la animación del videoclip.

Las imágenes cambian progresivamente durante los 120 frames:

- Imagen 1: frames 0 al 29
- Imagen 2: frames 30 al 59
- Imagen 3: frames 60 al 89
- Imagen 4: frames 90 al 119

## 5. Análisis del audio

El método `calculateAudioLevel()` recorre el segmento del arreglo `short[]` correspondiente a cada frame.

Se calcula el promedio del valor absoluto de las muestras y posteriormente se normaliza entre 0.0 y 1.0.

Este nivel de audio se utiliza para determinar los efectos visuales aplicados a los frames.

## 6. Efectos utilizados

Se implementó una transformación geométrica mediante rotación.

También se utilizaron diferentes efectos dependiendo del nivel del audio:

- Cambio de brillo
- Blur
- Sharpen
- Sobel

Los filtros Blur, Sharpen y Sobel utilizan procesamiento basado en matrices/convolución.

## 7. Procesamiento serial y paralelo

En el modo serial los frames se generan de manera secuencial.

En el modo paralelo el trabajo se distribuye entre varios threads, permitiendo procesar diferentes frames de forma concurrente.

Se realizaron pruebas utilizando 2 y 4 threads.

## 8. Resultados obtenidos

| Threads | Tiempo Serial | Tiempo Paralelo | Speedup | Eficiencia |
|---------|---------------|-----------------|---------|------------|
| 2 | 7.459 s | 4.102 s | 1.818x | 90.91% |
| 4 | 7.548 s | 2.326 s | 3.245x | 81.12% |

## 9. Análisis de resultados

Con 2 threads el tiempo paralelo fue de 4.102 segundos, mientras que con 4 threads disminuyó a 2.326 segundos.

El mayor speedup se obtuvo utilizando 4 threads, alcanzando aproximadamente 3.245x.

Sin embargo, utilizar más threads no significa necesariamente obtener una eficiencia mayor. En la prueba con 2 threads se obtuvo una eficiencia de 90.91%, mientras que con 4 threads fue de 81.12%.

Esto ocurre debido a la sobrecarga asociada a la creación, coordinación y ejecución de múltiples threads.

## 10. Archivos generados

El proyecto genera los siguientes archivos principales:

- `videoclip_serial.mp4`
- `videoclip_parallel.mp4`
- `output/metrics.csv`

Los dos videos tienen una duración de 10 segundos y utilizan el mismo audio e imágenes.

## 11. Conclusión

La implementación permitió comprobar la diferencia entre el procesamiento serial y paralelo.

En las pruebas realizadas, el procesamiento paralelo redujo considerablemente el tiempo de generación de los frames. La configuración de 4 threads obtuvo el menor tiempo de ejecución y el mayor speedup.
