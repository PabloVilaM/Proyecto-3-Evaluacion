package view;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


public class Preguntas {
    /**
     * Crea una lista Mapa con 65 preguntas sobre Java
     * @return Devuelve el mapa para posteriormente dar respuestas a las preguntas
     */
     public static Map crearLista(){
        Map<Integer, String> map = new HashMap<>();
        //Sección de casteo
        map.put(1, "Se puede hacer casteo de int a double? \na)Si. \nb)No. \nc)No es obligatorio pero puedes.");
        map.put(2, "Se puede hacer casteo de int a double? \na)Si. \nb)No. \nc)No es obligatorio pero puedes.");
         map.put(3, "Se puede hacer casteo de short a byte? \na)Si. \nb)No. \nc)No es obligatorio pero puedes. ");
        map.put(4, "Se puede hacer casteo de float a double? \na)Si. \nb)No. \nc)No es obligatorio pero puedes.");
        map.put(5, "Se puede hacer casteo de double a float? \na)Si. \nb)No. \nc)No es obligatorio pero puedes. ");
        map.put(6, "Se puede hacer casteo de int a long? \na)Si. \nb)No. \nc)No es obligatorio pero puedes. ");
        map.put(7, "Se puede hacer casteo de long a int? \na)Si. \nb)No. \nc)No es obligatorio pero puedes. ");
        map.put(8, "Se puede hacer casteo de short a long? \na)Si. \nb)No. \nc)No es obligatorio pero puedes. ");
        map.put(9, "Se puede hacer casteo de long a short? \na)Si. \nb)No. \nc)No es obligatorio pero puedes.");
        map.put(10, "Se puede hacer casteo de byte a short? \na)Si. \nb)No. \nc)No es obligatorio pero puedes.");
        //Seccion de String
        map.put(11, "Que se puede utilizar para invertir un String? \na)contrary(). \nb)reverse(). \nc)opposite(). \nd).counter()");
        map.put(12, "Que se puede utilizar para dividir un String? \na)divide(). \nb)substring(). \nc)partition(). \nd).separate()");
        map.put(13, "Que se puede utilizar para concatenar Strings? \na)concatenate(). \nb)C y D son correctas(). \nc)+,texto,+. \nd).concat()");
        map.put(14, "Que se puede utilizar para remplazar algun caracter por otro especificado? \na)substitute(). \nb)replace(). \nc)rerun(). \nd).supersede()");
        map.put(15, "Para conseguir un caracter de un String? \na)charIn(). \nb)charAt(). \nc)charOut(). \nd).charPatt()");
        map.put(16, "Cual es la forma incorrecta de pasar un string a minusculas? \na)tolowerCase(). \nb)tolowercase(). \nc)ToLowerCase(). \nd).Ninguna es correcta" );
        map.put(17, "Cual es la forma incorrecta de pasar un string a mayusculas? \na)toupperCase(). \nb)touppercase(). \nc)ToUpperCase(). \nd).Ninguna es correcta");
        map.put(18, "Existe el split()? \na)Existe en StringBuilder. \nb)No en String. \nc)No existe. \nd)Si en String.");
        map.put(19, "Como comparamos (por ejemplo en un if) dos Strings? \na)Con un ==. \nb)Con un compareTo(). \nc)No se puede. \nd)Con equals()");
        map.put(20, "length() en un String que nos da? \na)Los caracteres del String. \nb)El codigo asscii del String. \nc)No nos da nada. \nd)La longitud del String.");
        //Sección de Ficheros
        map.put(21, "Como se llama la comunicación entre el origen y el destino de un Fichero? \na)Flujo o Stream. \nb)Flujo. \nc)No tiene nombre \nd)Stream.");
        map.put(22, "Es importante cerrar el flujo de un Fichero? \na)Si para volver al último lugar que trataste. \nb)No porque el archivo seguira igual. \nc)Si porque luego no se puede abrir. \nd)No porque se cierra solo. ");
        map.put(23, "Formato de un fichero de Bytes? \na).dat. \nb).txt. \nc).sql. \nd).doc. ");
        map.put(24, "Para que se utiliza la serialización? \na)Clases operadas por nosotros. \nb)Datos primitivos. \nc)Los String. \nd)Datos primitivos + String");
        map.put(25, "Que viene en los archivos de Bytes serializado por defecto? \na)Datos primitivos + String. \nb)Datos primitivos. \nc)Los String. \nd)Clases operadas por nosotros.");
        map.put(26, "Que se utiliza cuando un atributo es transitorio (Archivos)? \na)transit. \nb)transitory. \nc)transient. \nd)temporary");
        map.put(27, "Cuantos pasos tenemos que hacer para tratar un Fichero? \na)1. \nb)2. \nc)3. \nd)4.");
        map.put(28, "Se trata con la misma clase la escritura y la lectura? \na)Si. \nb)No es obligatorio. \nc)No.");
        map.put(29, "Que pasa si intentamos escribir en un archivo inexistente? \na)Rompe el programa. \nb)No pasa nada. \nc)Lo crea. \nd)Lo crea pero salta error.");
        map.put(30, "Que tipos de ficheros hemos dado? \na)Texto. \nb)Bytes. \nc)Texto y Bytes. \nd)Texto y Numericos");
        //Sección de Try catch
        map.put(31, "Cual caracteristica es correcta del catch? \na)Puede haber infinitos por un try. \nb)Todas son ciertas. \nc)Sirve para capturar un error. \nd)No puede haber codigo entre ellos.");
        map.put(32, "Cual caracteristica es correcta del finally? \na)Es obligatoria. \nb)Es opcional. \nc)No se ejecuta siempre. \nd)Trata el error.");
        map.put(33, "Cuantos catch puede haber por try? \na)Los justos por cada error del try. \nb)Infinitos. \nc)1. \nd)2.");
        map.put(34, "Que componentes tiene una mecanica de excepciones básica? \na)catch, try, finally. \nb)catch, try . \nc)catch, finally. \nd)finally, try");
        map.put(35, "Cuales se pueden usar en un mecanismo de excepciones \na)finally. \nb)Todas son correctas. \nc)catch. \nd)try");
        map.put(36, "Caracteristicas de throws new? \na)Lanza el error a otra clase. \nb)Tratar el error. \nc)Se pone en el nombre de la clase. \nd)A y C son correctas" );
        map.put(37, "Que clases tiene la clase Throwable \na)Error. \nb)RunTimeException y Exception. \nc)Exception. \nd)A y C son correctas");
        map.put(38, "Que se trata en la clase de Error. \na)Hardware. \nb)Maquinas Virtuales. \nc)Software. \nd)A y B son correctas.");
        map.put(39, "El try... \na)Va a ser codigo que se intente. \nb)Tiene que tener mínimo un catch relacionado. \nc)Captura el error. \nd)A y B son ciertas.");
        map.put(40, "Como se crea un throw? \na)throw.... \nb)throw new \"el finally\".... \nc)throw new \"clase a la que va\". \nd)throw new...");
        //  Secion de interfaces
        map.put(41, "Como se implementa una interfaz? \na)implements. \nb)implement. \nc)A y B son correctas. \nd)extends.");
        map.put(42, "Cuantas interfaces se pueden implementar en una clase? \na)Infinitas. \nb)1. \nc)2. \nd)Ninguna.)");
        map.put(43, "Que terminos existen dentro de una interfaz? \na)Todas son ciertas. \nb)static. \nc)abstract. \nd)default.");
        map.put(44, "En que se diferencia un metodo default de uno abstracto? \na)Uno tiene contenido y el otro no. \nb)Uno puede ser static y otro no. \nc)Uno puede hacerse override y otro no. \nd).B y C son correctas");
        map.put(45, "El metodo default... \na)Todas son ciertas. \nb)Se implemento en Java 8. \nc)Tiene contenido. \nd)Se suele implementar en interfaces.");
        map.put(46, "En un diagrama de clases las interfaces se unen con... \na)Lineas regulares. \nb)Ninguna es correcta. \nc)Lineas discontinuas. \nd)Lineas en ziczac" );
        map.put(47, "Un metodo abstracto... \na)Debe tener minimo un metodo abstract. \nb)Esta marcado con abstract. \nc)Todas son ciertas. \nd)Lleva normalmente metodos vacios.");
        map.put(48, "Los atributos en interfaces... \na)Deben declararse e inicializarse al principio. \nb)Ninguna es correcta. \nc)Deben ser final. \nd)No puede haber atributos.");
        map.put(49, "Que se implementa para las interfaces en Java 9? \na)Metodos protected. \nb)Metodos static. \nc)Metodos private. \nd)Metodos default.");
        map.put(50, "Como se suelen denominar las interfaces? \na)Un verbo sin mas. \nb)No importa como se llama. \nc)Una i y el nombre seguido acabado en -able. \nd)Ninguna es correcta.");
        //Seccion de Colecciones
        map.put(51, "Que colecciones entran dentro de Interfaz.Colection? \na)Map, Queue, Set. \nb)Queue, Set, List. \nc)List, Queue, Map. \nd)List, Set, Queue");
        map.put(52, "Que clases conocemos dentro de Set? \na)HashSet. \nb)A y D son ciertas. \nc)HasSet. \nd)TreeSet.");
        map.put(53, "El mapa que tiene de especial? \na)Es de Dora la Exploradora. \nb)Que tiene una clave y un valor. \nc)Que tiene una clase llamada MapArray. \nd)Ninguna es correcta.");
        map.put(54, "Que estructuras de colecciones conocemos? \na)FIFO y FITO. \nb)FIFO y FILO. \nc)FICO y FIFO. \nd)FIFO y FIMO");
        map.put(55, "Una caracteristica del ArrayList es: \na)Busqueda lenta elimación rápida. \nb)Busqueda rapída elimacion lenta. \nc)Busquedas y elimaciones rapidas. \nd)Busquedas y elimaciones lentas");
        map.put(56, "Que clases hemos dado de List? \na)ListTo. \nb)LinkedList. \nc)ArrayList. \nd).B y C son correctas" );
        map.put(57, "Una caracteristica del mapa es: \na)Se pueden repiten valores. \nb)Lleva solo un parametro que es lo que contiene. \nc)B y D son correctas. \nd)No se pueden repetir valores.");
        map.put(58, "Si te digo que estas preguntas estan hechas con una Coleccion y que cada una tiene una clave, que uso? \na)Set. \nb)List. \nc)Queue. \nd)Map.");
        map.put(59, "El list... \na)No pueden repetirse valores. \nb)Es el que menos hemos utilizado en clase. \nc). A y B son ciertas \nd)Pueden repetirse valores.");
        map.put(60, "Como se recorre de manera óptima una LinkedList? \na)Un for. \nb)Un foreach. \nc)B y C son ciertas. \nd)Un iterator.");
        //Sección de arrats
        map.put(61, "Que tipos de Array hemos dado? \na)bidimensionales y unidimensionales. \nb)ArrayList y Array. \nc)unidimensionales y tablas. \nd)ArrayList y tablas.");
        map.put(62, "Que tipo de memoria usa un array? \na)Estatica. \nb)Dinamica. \nc)Movible. \nd)Quieta.");
        map.put(63, "Caracteristicas de los Arrays: \na)Todas son ciertas. \nb)Son muy simples. \nc)Almacenan datos de un mismo tipo. \nd)Pueden almacenar tipos primitivos o objetos");
        map.put(64, "Formas  de dar valores a un Array: \na)Todas son correctas. \nb)for. \nc)foreach. \nd)Valor por valor.");
        map.put(65, "La papelera de java que es? \na)Existe en el java default. \nb)Hay que añadir el java.util.trash. \nc)Ninguna es correcta. \nd)Hay que añadir el java.trash");
        return map;
    }

    /**
     * Da preguntas a la ventana de JOptionPane.
     * @param code El codigo del Map
     * @param mapa El Map que consultamos
     * @return La pregunta que consigue
     */
    public static String darPreguntas(int code, Map mapa){
        String pregunta = (String) mapa.get(code);
        return  pregunta;
    }

    /**
     * Sivve para comprobar si la respuesta que da la persona es correcta o incorrecta
     * @param code El codigo de la pregunta
     * @param respuesta La respuesta dependiendo de si es correcta o no
     * @return Verdadero si ha hacertado, falso si no
     */
    public static boolean comprobarRespuesta(int code, String respuesta){
      boolean correcion = false;
        switch (code){
            case 10:
            case 8:
            case 4:
            case 1:
            case 6:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:

                switch (respuesta){
                    case "c":
                        System.out.println("Correctoo");
                        correcion = true;
                        break;
                    case "d":
                    case "a":
                    case "b":
                        System.out.println("Incorrectoo");
                        correcion = false;
                        break;
                }
                break;
            case 9:
            case 7:
            case 3:
            case 2:
            case 5:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
                switch (respuesta){
                    case "a":
                        System.out.println("Correctoo");
                        correcion = true;
                        break;
                    case "b":
                    case "c":
                    case "d":
                        System.out.println("Incorrectoo");
                        correcion = false;
                        break;
                }
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                switch (respuesta){
                    case "b":
                        System.out.println("Correctoo");
                        correcion = true;
                        break;
                    case "d":
                    case "c":
                    case "a":
                        System.out.println("Incorrectoo");
                        correcion = false;
                        break;
                }
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
                switch (respuesta){
                    case "d":
                        System.out.println("Correctoo");
                        correcion = true;
                        break;
                    case "b":
                    case "c":
                    case "a":
                        System.out.println("Incorrectoo");
                        correcion = false;
                        break;
                }
                break;
        }
        return correcion;
    }
    
}
