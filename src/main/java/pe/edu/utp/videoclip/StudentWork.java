package pe.edu.utp.videoclip;

/*
 * ============================================================
 * ARCHIVO PRINCIPAL QUE DEBE MODIFICAR EL ESTUDIANTE
 * ============================================================
 *
 * El proyecto ya contiene:
 * - lectura WAV
 * - lectura de varias imágenes
 * - generación de frames
 * - modo serial
 * - modo paralelo con threads
 * - medición de tiempos
 *
 * El estudiante debe completar/personalizar los TODO.
 */
public final class StudentWork {

    private StudentWork() {}

    /*
     * TODO 1
     * Analizar un segmento del arreglo short[] y devolver un nivel
     * entre 0.0 y 1.0.
     */
    public static double calculateAudioLevel(
            short[] samples,
            int start,
            int end
    ) {

        if (samples == null || samples.length == 0) {
            return 0.0;
        }

        start = Math.max(0, start);
        end = Math.min(samples.length, end);

        if (start >= end) {
            return 0.0;
        }

        double suma = 0.0;

        // Recorrer el segmento del audio correspondiente al frame.
        for (int i = start; i < end; i++) {
            suma += Math.abs((double) samples[i]);
        }

        // Calcular el promedio de amplitud.
        double promedio = suma / (end - start);

        // Normalizar entre 0.0 y 1.0.
        double nivel = promedio / Short.MAX_VALUE;

        return Math.min(1.0, nivel);
    }

    /*
     * TODO 2
     * Elegir qué imagen se utilizará en cada frame.
     *
     * Las imágenes se distribuyen durante toda la duración
     * del videoclip para asegurar que todas aparezcan.
     */
    public static int chooseImageIndex(
            double level,
            int frameNumber,
            int totalFrames,
            int imageCount
    ) {

        if (imageCount <= 1) {
            return 0;
        }

        // Cantidad aproximada de frames para cada imagen.
        int block = Math.max(1, totalFrames / imageCount);

        // Cambiar de imagen según el avance del video.
        int indice = frameNumber / block;

        // Evitar superar el número de imágenes disponibles.
        return Math.min(imageCount - 1, indice);
    }

    /*
     * TODO 3
     * Crear el aspecto visual de cada frame.
     *
     * Se utiliza:
     * - transformación geométrica: rotación
     * - filtros de matriz/convolución
     * - diferentes estados visuales según el audio
     */
    public static MatrixImage applyEffects(
            MatrixImage base,
            double level,
            int frameNumber,
            int totalFrames
    ) {

        // Rotación suave que cambia durante el video.
        double angle = Math.sin(frameNumber * 0.12) * 5.0;

        MatrixImage imagen = base.rotate(angle);

        /*
         * El efecto aplicado depende de la intensidad
         * del audio correspondiente a cada frame.
         */
        if (level < 0.20) {

            // Estado visual 1: cambio de brillo.
            return imagen.brighten(0.8);

        } else if (level < 0.50) {

            // Estado visual 2: desenfoque por convolución.
            return imagen.blur();

        } else if (level < 0.75) {

            // Estado visual 3: aumento de nitidez.
            return imagen.sharpen();

        } else {

            // Estado visual 4: detección de bordes.
            return imagen.sobel();
        }
    }
}