import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static void mostrarMenu() {
        System.out.println();
        System.out.println("========== MENU LISTA CIRCULAR ==========");
        System.out.println(" 1. Agregar al final (add)");
        System.out.println(" 2. Insertar al inicio (insertHead)");
        System.out.println(" 3. Insertar al final (insertTail)");
        System.out.println(" 4. Insertar despues de un dato (insert)");
        System.out.println(" 5. Buscar un dato (search / contains)");
        System.out.println(" 6. Modificar un dato (set)");
        System.out.println(" 7. Eliminar un dato (remove)");
        System.out.println(" 8. Ver cabeza y cola (getHead / getTail)");
        System.out.println(" 9. Convertir a arreglo (toArray)");
        System.out.println("10. Sublista de un dato a otro (subList)");
        System.out.println("11. Ordenar (sortList)");
        System.out.println("12. Recorrer N pasos desde un dato (muestra que es circular)");
        System.out.println("13. Vaciar la lista (clear)");
        System.out.println(" 0. Salir");
        System.out.println("=========================================");
        System.out.print("Seleccione una opcion: ");
    }

    // Lee un numero entero (los datos de la lista son Integer).
    // Devuelve null si lo escrito no es un numero o si ya no hay entrada.
    private static Integer leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        if (!sc.hasNextLine()) {
            return null;
        }
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Debe escribir un numero entero.");
            return null;
        }
    }

    // Lee la opcion del menu: -1 si no es un numero, 0 (salir) si ya no hay entrada.
    private static int leerOpcion(Scanner sc) {
        if (!sc.hasNextLine()) {
            return 0;
        }
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        MiListaCircular lista = new MiListaCircular();
        Scanner sc = new Scanner(System.in);   // un solo Scanner para todo el programa
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1: {
                    Integer dato = leerEntero(sc, "Dato: ");
                    if (dato != null) {
                        lista.add(dato);
                    }
                    break;
                }
                case 2: {
                    Integer dato = leerEntero(sc, "Dato: ");
                    if (dato != null) {
                        lista.insertHead(dato);
                    }
                    break;
                }
                case 3: {
                    Integer dato = leerEntero(sc, "Dato: ");
                    if (dato != null) {
                        lista.insertTail(dato);
                    }
                    break;
                }
                case 4: {
                    Integer ref = leerEntero(sc, "Insertar despues de que dato: ");
                    Integer nuevo = (ref == null) ? null : leerEntero(sc, "Dato nuevo: ");
                    if (ref != null && nuevo != null) {
                        if (!lista.insert(ref, nuevo)) {
                            System.out.println("No existe el dato " + ref + " en la lista.");
                        }
                    }
                    break;
                }
                case 5: {
                    Integer dato = leerEntero(sc, "Dato a buscar: ");
                    if (dato != null) {
                        Node nodo = lista.search(dato);
                        if (nodo == null) {
                            System.out.println("El dato " + dato + " NO esta en la lista.");
                        } else {
                            System.out.println("El dato " + dato + " esta en la lista (contains = "
                                    + lista.contains(dato) + "). Su siguiente es " + nodo.siguiente.dato + ".");
                        }
                    }
                    break;
                }
                case 6: {
                    Integer viejo = leerEntero(sc, "Dato a modificar: ");
                    Integer nuevo = (viejo == null) ? null : leerEntero(sc, "Nuevo valor: ");
                    if (viejo != null && nuevo != null) {
                        Node nodo = lista.search(viejo);
                        if (nodo == null) {
                            System.out.println("No existe el dato " + viejo + " en la lista.");
                        } else {
                            lista.set(nodo, nuevo);
                        }
                    }
                    break;
                }
                case 7: {
                    Integer dato = leerEntero(sc, "Dato a eliminar: ");
                    if (dato != null) {
                        Node nodo = lista.search(dato);
                        if (nodo == null) {
                            System.out.println("No existe el dato " + dato + " en la lista.");
                        } else {
                            lista.remove(nodo);
                        }
                    }
                    break;
                }
                case 8:
                    System.out.println("Cabeza: " + lista.getHead() + "   Cola: " + lista.getTail());
                    break;
                case 9:
                    System.out.println("Arreglo: " + Arrays.toString(lista.toArray()));
                    break;
                case 10: {
                    Integer desde = leerEntero(sc, "Desde que dato: ");
                    Integer hasta = (desde == null) ? null : leerEntero(sc, "Hasta que dato: ");
                    if (desde != null && hasta != null) {
                        MiListaCircular sub = lista.subList(lista.search(desde), lista.search(hasta));
                        if (sub == null) {
                            System.out.println("No se pudo: uno de los dos datos no esta en la lista.");
                        } else {
                            System.out.println("Sublista: " + sub);
                        }
                    }
                    break;
                }
                case 11:
                    System.out.println("Lista ordenada (la original no cambia): " + lista.sortList());
                    break;
                case 12: {
                    Integer dato = leerEntero(sc, "Empezar en el dato: ");
                    Integer pasos = (dato == null) ? null : leerEntero(sc, "Cuantos pasos (max. 100): ");
                    if (dato != null && pasos != null) {
                        Node nodo = lista.search(dato);
                        if (nodo == null) {
                            System.out.println("No existe el dato " + dato + " en la lista.");
                        } else {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < Math.min(pasos, 100); i++) {
                                sb.append(nodo.dato).append(" -> ");
                                nodo = nodo.siguiente;
                            }
                            System.out.println(sb + "...");
                        }
                    }
                    break;
                }
                case 13:
                    lista.clear();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion no valida. Elija un numero del 0 al 13.");
            }

            if (opcion != 0) {
                System.out.println("Lista: " + lista + "   (tamano: " + lista.getSize() + ")");
            }
        } while (opcion != 0);
    }
}
