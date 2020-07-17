import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class CriterioCorreccion {

    abstract Double calcularNota(Integer puntosRespuestas);
}

class RestarNPuntos extends CriterioCorreccion {

    private Integer excedente;

    RestarNPuntos(Integer exc) {
        this.excedente = exc;
    }

    @Override
    Double calcularNota(Integer puntosRespuestas) {
        return Double.max(puntosRespuestas - excedente,0);
    }
}

class Regla3Simples extends CriterioCorreccion {

    private Double totalPuntos;

    Regla3Simples(Double total) {
        this.totalPuntos = total;
    }

    @Override
    Double calcularNota(Integer puntosRespuestas) {
        return (puntosRespuestas * 10) / totalPuntos;
    }
}

class TablaDePuntos extends CriterioCorreccion {

    private Map<Integer,Double> tabla;

    TablaDePuntos(Map<Integer,Double> tabla) {
        this.tabla.putAll(tabla);
    }

    @Override
    Double calcularNota(Integer puntosRespuestas) {
        return tabla.get(puntosRespuestas);
    }
}

class MayorNotaCriterios extends CriterioCorreccion {

    private List<CriterioCorreccion> criterios;

    public void agregarCriterio(CriterioCorreccion criterio) {
        criterios.add(criterio);
    }

    private List<Double> aplicarCriterios(Integer puntosRespuestas) {
        return criterios.stream().map(criterio -> criterio.calcularNota(puntosRespuestas)).collect(Collectors.toList());
    }

    @Override
    Double calcularNota(Integer puntosRespuestas) {
        return aplicarCriterios(puntosRespuestas).stream().mapToDouble(v -> v).max().orElse(0);
    }
}

class PromedioCriterios extends CriterioCorreccion {

    List<CriterioCorreccion> criterios;

    public void agregarCriterio(CriterioCorreccion criterio) {
        criterios.add(criterio);
    }

    private List<Double> aplicarCriterios(Integer puntosRespuestas) {
        return criterios.stream().map(criterio -> criterio.calcularNota(puntosRespuestas)).collect(Collectors.toList());
    }

    @Override
    Double calcularNota(Integer puntosRespuestas) {
        return aplicarCriterios(puntosRespuestas).stream().mapToDouble(v -> v).average().getAsDouble();
    }
}

class CorrectorDeExamenes {

    private CriterioCorreccion criterio;

    Double calcularNota(Integer puntosRespuestas) {
        return criterio.calcularNota(puntosRespuestas);
    }


}