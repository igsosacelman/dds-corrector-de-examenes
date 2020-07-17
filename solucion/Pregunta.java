import java.util.List;

class Pregunta {

    private TipoPregunta tipo;
    private int pesoEspecifico;

    Pregunta(TipoPregunta tipo, int peso) {
        this.tipo = tipo;
        this.pesoEspecifico = peso;
    }

    Boolean esRespuestaCorrecta() { 
        return tipo.esCorrecta();
    }

    public Integer getPesoEspecifico() {
        return pesoEspecifico;
    }
    
    /* Setters y Getters */
}

interface TipoPregunta {

    Boolean esCorrecta();
}

class VerdaderoFalso implements TipoPregunta {

    private Boolean respuestaCorrecta;
    private Boolean respuestaAlumno;

    VerdaderoFalso(Boolean respuesta) {
        this.respuestaCorrecta = respuesta;
    }

    @Override
    public Boolean esCorrecta() {
        return respuestaCorrecta == respuestaAlumno;
    }

    // El metodo responder no logre hacerlo polimorfico, creo que no es responsabilidad del tipo pregunta
    public void responder(Boolean respuesta) {
        respuestaAlumno = respuesta;
    }
}

class MultipleChoice implements TipoPregunta {

    private List<String> respuestasCorrectas;
    private List<String> respuestasAlumno;

    MultipleChoice(List<String> respuestas) {
        this.respuestasCorrectas.addAll(respuestas);
    }

    @Override
    public Boolean esCorrecta() {
        return respuestasCorrectas.containsAll(respuestasAlumno) && respuestasCorrectas.size() == respuestasAlumno.size();
    }

    // El metodo responder no logre hacerlo polimorfico, creo que no es responsabilidad del tipo pregunta
    public void responder(List<String> respuestas) {
        respuestasAlumno.addAll(respuestas);
    }
}

class PreguntaConcreta implements TipoPregunta {

    private String respuestaCorrecta;
    private String respuestaAlumno;

    PreguntaConcreta(String respuesta) {
        this.respuestaCorrecta = respuesta;
    }

    @Override
    public Boolean esCorrecta() {
        return respuestaCorrecta == respuestaAlumno;
    }

    // El metodo responder no logre hacerlo polimorfico, creo que no es responsabilidad del tipo pregunta
    public void responder(String respuesta) {
        respuestaAlumno = respuesta;
    }
}