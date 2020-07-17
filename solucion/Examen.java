import java.util.List;

class Examen {
    
    private List<Pregunta> preguntas;

    private CorrectorDeExamenes corrector = new CorrectorDeExamenes();

    private static int NOTA_APROBADO = 4; // Podria ser 6. Esto lo podriamos sacar a un archivo de configuracion para manejarlo fuera del programa

    Double calcularNota() {
        return corrector.calcularNota(totalPuntosRespuestas());
    }

    Boolean estaAprobado() {
        return calcularNota() >= NOTA_APROBADO;
    }

    private Integer totalPuntosExamen() {
        return preguntas.stream().map(pregunta -> pregunta.getPesoEspecifico()).reduce(0,Integer::sum);
    }

    private Integer totalPuntosRespuestas() {
        return preguntas.stream().filter(pregunta -> pregunta.esRespuestaCorrecta()).map(pregunta -> pregunta.getPesoEspecifico()).reduce(0,Integer::sum);
    }

}
