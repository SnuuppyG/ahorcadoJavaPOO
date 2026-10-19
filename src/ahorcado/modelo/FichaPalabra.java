package modelo;

public class FichaPalabra {

    private final String categoria;
    private final String palabra;
    private final String pistaEscrita;

    /*
      Crea una ficha. La palabra se normaliza a mayúsculas.
     */
    public FichaPalabra(String categoria, String palabra, String pistaEscrita) {
        this.categoria = categoria;
        this.palabra = palabra.toUpperCase();
        this.pistaEscrita = pistaEscrita;
    }

    /*
      Devuelve la categoría de la palabra.
     */
    public String getCategoria() {
        return categoria;
    }

    /*
      Devuelve la palabra secreta en mayúsculas.
     */
    public String getPalabra() {
        return palabra;
    }

    /*
      Devuelve la pista escrita asociada a la palabra.
     */
    public String getPistaEscrita() {
        return pistaEscrita;
    }
}