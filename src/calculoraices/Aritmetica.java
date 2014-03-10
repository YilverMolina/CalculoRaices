package calculoraices;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Mora Ramos
 */
public class Aritmetica {

    public final String abc[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
        "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "w", "x", "y", "z"};
    public final String separador = ",";

    public Aritmetica() {
    }

 

    String[] Detectar_Letras_toArray(String ecuacion) {
        return Detectar_Letras(ecuacion).split(separador);
    }

    int Cantidad_Incognitas(String ecuacion) {
        return Detectar_Letras_toArray(ecuacion).length;
    }

    public String Detectar_Letras(String ecuacion) {
        String letra = "";
        if (ecuacion.trim().length() > 0) {
            int ii = 0;
            for (int i = 0; i < ecuacion.length(); i++) {
                for (String abc1 : abc) {
                    if ((ecuacion.charAt(i) + "").equals(abc1)) {
                        letra += abc1;
                        ii = i;
                        break;
                    } else if ((ecuacion.charAt(i) + "").equals(abc1.toUpperCase())) {
                        letra += abc1.toUpperCase();
                        ii = i;
                        break;
                    }
                }
                if (ii + 1 == i) {
                    letra += separador;
                }
            }
            if (letra.startsWith(separador)) {
                letra = letra.replaceFirst(separador, "");
            }
            if (letra.endsWith(separador)) {
                letra = letra.substring(0, letra.length() - 1);
            }
        }
        return letra;
    }

    /**
     * @param ecuacion
     * @param nom_var es el nombre de la variable que existe en la ecuacion
     * @param valores valores para operar la ecuacion (tabulacion)
     * @return lista de resultados
     */
    String Operacion_Algebraica_1_incognita(String ecuacion, String nom_var,
            float valores[]) {
        if (ecuacion.contains(nom_var)) {
            String ecu1 = ecuacion, listado = "";
            for (int i = 0; i < valores.length; i++) {
                listado += (i + 1) + ">> \t" + Operar_Ecuacion(ecu1.replace(nom_var, "" + valores[i])) + "\n";
            }
            return listado;
        }
        return "No se ha detectado la variable '" + nom_var + "'";
    }

    /**
     * @param ecuacion, aqui es donde entra la acuacion, por ejemplo
     * (45/6*34)/6*(1/2)
     */
    String Operar_Ecuacion(String ecuacion) {
        ScriptEngineManager script = new ScriptEngineManager(); //Administrador de codigo javascript
        ScriptEngine js = script.getEngineByName("JavaScript");//
        String codigo = "function calc(formula) {\n"//Aqui se define todo el codigo javascript para ejecutar
                + "with(Math) x=eval(formula);\n"
                + "if (x==\"NaN\") {\n"
                + "return \"Error\";\n"
                + "} else {\n"
                + "return x;\n"
                + "}\n"
                + "}\n";
        try {
            js.eval(codigo);
            Invocable inv = (Invocable) js; // ESta opcion permite llamar los metodos de javascript puestos en 
            //la variable 'codigo'
            return inv.invokeFunction("calc", ecuacion).toString();//Aqui se llama el metodo.
            //los parametros se separan por ',' entonces de esta manera se esta llamando
            // el metodo 'calc' el cual recibe un parametro 'formula'
        } catch (NoSuchMethodException ex) {
            return "No se pudo operar, error: " + ex;
        } catch (ScriptException | NullPointerException ex) {
            Logger.getLogger(Aritmetica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "No se realizo la operacion";
    }
}
