package modelo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BancoDePalabras {

    private static final String RUTA_RECURSO = "/palabras.txt";

    private final ArrayList<FichaPalabra> fichas = new ArrayList<>();
    private final Random random = new Random();

    /*
      Carga el banco desde el recurso del proyecto.
      Lanza IOException si el recurso no existe o no se puede leer.
     */
    public BancoDePalabras() throws IOException {
        cargarDesdeRecurso();
    }

    /*
      Lee el recurso línea por línea. Ignora líneas vacías y comentarios (#).
      Formato esperado: categoria;PALABRA;pista
     */
    private void cargarDesdeRecurso() throws IOException {
        InputStream recurso = BancoDePalabras.class.getResourceAsStream(RUTA_RECURSO);
        if (recurso == null) {
            throw new IOException("No se encontró el recurso " + RUTA_RECURSO);
        }

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(recurso, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    fichas.add(new FichaPalabra(partes[0].trim(), partes[1].trim(), partes[2].trim()));
                }
            }
        }
    }
    /*
      Devuelve una ficha elegida al azar del banco.
     */
    public FichaPalabra obtenerAleatoria() {
        return fichas.get(random.nextInt(fichas.size()));
    }
}