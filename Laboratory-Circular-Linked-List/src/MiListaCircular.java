import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class MiListaCircular implements ListInterface {

    private Node head;
    private Node tail;
    private int size;

    public MiListaCircular(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }


    @Override
    public Object getHead() { //Me devuelve el dato de la cabeza o dice si es nulo en casoo que este vacia
        return isEmpty() ? null : head.dato;
    }

    @Override
    public Object getTail() { // Me da el dato de la cola  o nulo si esta vacio
        return  isEmpty() ? null : tail.dato;
    }

    @Override
    public Object get(Node node) {
        return node == null ? null : node.dato;
    }

    @Override
    public Node search(Object object) { // Me devuelve el primer nodo cuyo dato sea igual al object
        Node actual = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(actual.dato, object)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    private boolean contieneNodo(Node nodo) {
        if (nodo == null) {
            return false;
        }
        Node actual = head;
        for (int i = 0; i < size; i++) {
            if (actual == nodo) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean add(Object object) { //Agrega al final de la lista
        return insertTail(object);
    }

    @Override
    public boolean insert(Node node, Object object) { //Inserta un nuevo nodo con object despues de node
        if (!contieneNodo(node)) {
            return false;
        }
        if (node == tail) {
            return insertTail(object); //Estoo inserta despues de la cola = nueva cola
        }
        Node nuevo = new Node(object);
        nuevo.siguiente = node.siguiente;
        node.siguiente = nuevo;
        size++;
        return true;
    }

    @Override
    public boolean insert(Object objectRef, Object object) { //Inserta un object despues del primer nodo que contenga objectRef
        Node ref = search(objectRef);
        if (ref == null) {
            return false;
        }
        return insert(ref,object);
    }

    @Override
    public boolean insertHead(Object object) {
        Node nuevo = new Node(object);
        if (isEmpty()) {
            head = nuevo;
            tail = nuevo;
            nuevo.siguiente = nuevo; //un nodo se apunta a si mismo
        } else {
            nuevo.siguiente = head; //el nuevo apunta a la anterior cabeza
            tail.siguiente = nuevo; //la cola apunta a la nueva cabeza y con esto mantenemos el circulo
            head = nuevo;
        }
        size++;
        return true;
    }

    @Override
    public boolean insertTail(Object object) {
        Node nuevo = new Node(object);
        if (isEmpty()) {
            head = nuevo;
            tail = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            nuevo.siguiente = head; //la nueva cola apunta a la cabeza
            tail.siguiente = nuevo; // la antigua cola ahora apunta a la nueva
            tail = nuevo;
        }
        size++;
        return true;
    }

    @Override
    public boolean set(Node node, Object object) {
        if (node == null) {
            return false;
        }
        node.dato = object;
        return true;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null || isEmpty()) {
            return false;
        }
        Node previo = tail;
        Node actual = head;
        for (int i = 0; i < size; i++) {
            if (actual == node) {
                if (size == 1) {
                    head = null;
                    tail = null;
                } else {
                    previo.siguiente = actual.siguiente;
                    if (actual == head) {
                        head = actual.siguiente;
                    }
                    if (actual == tail) {
                        tail = previo;
                    }
                }
                actual.siguiente = null;
                size --;
                return true;
            }
            previo = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[size];
        Node actual = head;
        for (int i = 0; i < size; i++) {
            arreglo[i] = actual.dato;
            actual = actual.siguiente;
        }
        return arreglo;
    }

    @Override
    public Object[] toArray(Object[] object) {
        Object[] arreglo = object;
        if (arreglo == null) {
            arreglo = new Object[size];
        } else if (arreglo.length < size) {
            arreglo = (Object[]) Array.newInstance(arreglo.getClass().getComponentType(), size);
        }
        Node actual = head;
        for (int i = 0; i < size; i++) {
            arreglo[i] = actual.dato;
            actual = actual.siguiente;
        }
        if (arreglo.length > size) {
            arreglo[size] = null;
        }
        return arreglo;
    }

    @Override
    public MiListaCircular subList(Node from, Node to) {
        if (!contieneNodo(from) || !contieneNodo(to)) {
            return null;
        }
        MiListaCircular sub = new MiListaCircular();
        Node actual = from;
        for (int i = 0; i < size; i++) {
            sub.insertTail(actual.dato);
            if (actual == to) {
                return sub;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public MiListaCircular sortList() {
        Object[] arreglo = toArray();
        Arrays.sort(arreglo, (a,b) -> ((Comparable) a).compareTo(b));
        MiListaCircular ordenada = new MiListaCircular();
        for (Object o : arreglo) {
            ordenada.insertTail(o);
        }
        return ordenada;
    }
}
