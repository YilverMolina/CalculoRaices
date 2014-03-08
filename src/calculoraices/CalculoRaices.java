package calculoraices;

/**
 * Clase de pruebas
 *
 * @author Mora Ramos
 */
public class CalculoRaices {

    public static void main(String[] args) {
        Aritmetica a = new Aritmetica();
//        String ecuacion1 = "(4+2)*8-8";
//        System.out.println("Solucion de la ecuacion: " + a.Operar_Ecuacion(ecuacion1));

        //-------------------Probando metodo para 1 letra------------------------
//        String ecuacion2 = "((a*2)+1)-5";
//        float val[] = {2, 1, 4, 6};
//        System.out.println(a.Operacion_Algebraica_1_incognita(ecuacion2, "a", val));
        //-------------------Probando detector de letras------------------------
        String ecuacion2 = "((abcd*2cw)+1)-5d e+AA";
        float val[] = {2, 1, 4, 6};
        System.out.println("Las incognitas son: " + a.Detectar_Letras(ecuacion2));
        System.out.println("Son " + a.Cantidad_Incognitas(ecuacion2) + " incognitas");
    }
}
