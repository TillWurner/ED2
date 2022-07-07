
import java.util.LinkedList;
import javax.swing.JTextArea;


public class ArbolM {
    private NodoM raiz;
    private int n;          //n=cantidad de nodos.
    
    public ArbolM(){
        raiz = null;
        n = 0;
    }
    public int getCantNodos(){
        return n;
    }
    
    public void add(int x){
        if (raiz == null)
            raiz = new NodoM(x);
        else{
            int i=0;
            NodoM ant=null, p=raiz;
            while (p != null){
                if (!p.isLleno()){  //Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }
                
                i = hijoDesc(p, x);
                if (i == -1)
                    return;     //x está en el nodo p.
                
                ant = p;
                p = p.getHijo(i);
            }
            
                //p llegó a null.  Crear una nueva hoja y engancharla a ant.
            NodoM nuevo = new NodoM(x);
            ant.setHijo(i, nuevo);
        }
        
        n++;
    }
    
    
    private boolean hoja(NodoM T){
        return (T !=null && T.cantHijos()==0);
    }
    
    public void inorden(JTextArea ta){
        if (raiz == null)
            ta.append(String.valueOf("No hay datos"));
        else{
            inorden(raiz, ta);
        }
            
    }
    
    private void inorden(NodoM T, JTextArea ta){
        if (T != null){
            int z=T.cantDatasUsadas();      //z = índice de la última data usada.
            for (int i=1; i<=z; i++){
                inorden(T.getHijo(i),ta);
                ta.append(String.valueOf( T.getData(i)+ " "));
            }
            inorden(T.getHijo(z+1),ta);
        }
    }
    
    private int hijoDesc(NodoM N, int x){   //Corrutina de insertar.
        int i=1;
        while (i <= N.cantDatasUsadas()){
            if (x < N.getData(i))
                return i;
            
            if (x == N.getData(i))
                return -1;
            
            i++;
        }
        
        return N.cantDatasUsadas()+1;
    }
    
    private void print(NodoM N){
       for (int i=1; i<= N.cantDatasUsadas(); i++)
          System.out.print(" "+N.getData(i)); 
    }
    
    
    
    public void niveles(){
        niveles("");
    }
    
    public void niveles(String nombreVar){
        if (nombreVar != null && nombreVar.length()>0)
            nombreVar = " del Arbol "+nombreVar;
        else
            nombreVar = "";
                    
        System.out.print("Niveles"+nombreVar+": ");
        
        if (raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(raiz);
    }
    
    //EJERCICIOSPRACTICOS
    public boolean eshoja(){
        return eshoja(raiz);
    }
    private boolean eshoja(NodoM p){
        boolean bandera = true;
        if (p==null) {
            return false;
        }
        for (int i = 1; i <= p.M; i++) {
            if (p.getHijo(i)!=null) {
                bandera = false;
            }
        }
        return bandera;
    }
    public int cantnodos(){
        return cantnodos(raiz);
    }
    private int cantnodos(NodoM p){
        if (p==null) {
            return 0;
        }
        if (eshoja(p)) {
            return 1;
        }
        int cont = 0;
        for (int i = 1; i <= p.M; i++) {
            cont = cont + cantnodos(p.getHijo(i));
        }
        return cont + 1;
    }
    public int cantelemento(){
        return cantelemento(raiz);
    }
    private int cantelemento(NodoM p){
        if (p==null) {
            return 0;
        }
        if (eshoja(p)) {
            return p.cantDatasUsadas();
        }
        int aux = 0;
        for (int i = 1; i <= p.M; i++) {
            aux = aux + cantelemento(p.getHijo(i));
        }
        return aux + p.cantDatasUsadas();
    }
    public int altura(){
        return altura(raiz);
    }
    private int altura(NodoM p){
        if (p==null) {
            return 0;
        }
        if (eshoja(p)) {
            return 1;
        }
        int aux = 0;
        for (int i = 1; i <= p.M; i++) {
            if (aux< altura(p.getHijo(i))) {
                aux = altura(p.getHijo(i));
            }
        }
        return aux + 1;
    }
    
    public boolean existex(int x){
        return existex(raiz,x);
    }
    private boolean existex(NodoM p , int x){
        if (p==null) {
            return false;
        }
            for (int i = 1; i <= p.cantDatasUsadas(); i++) {
                if (x==p.getData(i)) {
                    return true;
                }
            }
        
    boolean bandera = false;
        for (int j = 1; j <= p.M; j++) {
            boolean aux = existex(p.getHijo(j), x);
                if (aux != bandera) {
                    return true;
                }
            }
        return bandera;
    }
    
    public boolean isleaf(int x){
        return isleaf(raiz, x);
    }
    private boolean isleaf(NodoM p, int x){
        if (p==null) {
            return false;
        }
        if (eshoja(p)) {
            for (int i = 1; i <= p.cantDatasUsadas(); i++) {
                if (p.getData(i) == x) {
                    return true;
                }
            }
        }
        boolean bandera = false;
        for (int i = 1; i <= p.M; i++) {
          boolean aux = isleaf(p.getHijo(i), x);
            if (bandera != aux) {
                bandera = true;
            }
  
        }
        return bandera;
    }
   public NodoM delLeaf(int x){
       NodoM aux = delLeaf(raiz,x);
       return raiz = aux;
   }
   private NodoM delLeaf(NodoM p, int x){
       if (p == null) {
           return null;
       }
       if (eshoja(p)) {
           for (int i = 1; i <= p.cantDatasUsadas(); i++) {
               if (x== p.getData(i)) {
                   return p = null;
               }
           }
           return p;
       }
       for (int i = 1; i <= p.M; i++) {
           NodoM aux = delLeaf(p.getHijo(i), x);
           p.setHijo(i, aux);
       }
       return p;
   }
   public int alturita(){
       return alturita(raiz);
   }
   private int alturita(NodoM p){
       if (p==null) {
           return 0;
       }
       if (eshoja(p)) {
           return 1;
       }
       int cont = 0;
       for (int i = 1; i <= p.M; i++) {
           int aux = alturita(p.getHijo(i));
           if (aux > cont) {
               cont = aux;
           }
       }
       return cont + 1;
   }
   //EjerciciosParciales
   //EZY +50Pts
   public NodoM delLeafPadre(int p, int h){
       NodoM aux = delLeafPadre(raiz, p , h);
       return raiz = aux;
   }
   private NodoM delLeafPadre(NodoM t, int p, int h){
       if (t==null) {
           return null;
       }
       if (altura(t)>1) {
           for (int i = 1; i <= t.cantDatasUsadas(); i++) {
               if (t.getData(i)==p) {
                   for (int j = 1; j <= t.M; j++) {
                       if (t.getHijo(j)!=null) {
                           if (hoja(t.getHijo(j))) {
                               for (int k = 1; k <= t.getHijo(j).cantDatasUsadas(); k++) {
                                   if (t.getHijo(j).getData(k)==h) {
                                       t.setHijo(j, null);
                                       return t;
                                   }
                               }
                           }
                       }
                   }
               }
               
           }
       }
    
       for (int i = 1; i <= t.M; i++) {
           NodoM aux = delLeafPadre(t.getHijo(i), p, h);
           t.setHijo(i, aux);
       }
       return t;
   }
   public int niveldex(int x){
       return niveldex(raiz, x, 1);
    }
    private int niveldex(NodoM p, int x, int actual){
        if (p==null) {
            return 0;
        }
            for (int i = 1; i <= p.cantDatasUsadas(); i++) {
                if (p.getData(i)==x) {
                    return actual;
                }
            }
        for (int i = 1; i <= p.M; i++) {
            int aux = niveldex(p.getHijo(i), x, actual + 1);
            if (aux!=0) {
                return aux;
            }
        }
        return 0;
    }
    
   //PREG 2  ///CANTGAJOS VERIFICAR
   
   public int niveldexfinal(int x){
        return niveldexfinal(raiz,x,0);
    }
    private int niveldexfinal(NodoM p, int x, int actual){
        if(p==null){
            return -1;
        }
        
            for (int i = 1; i <= p.cantDatasUsadas(); i++) {
                if(p.getData(i)==x){
                    return actual;
                }
            }
        
        for (int i = 1; i <= p.M; i++) {
            int aux=niveldexfinal(p.getHijo(i),x, actual + 1);
            if(aux!=-1){
                return aux;
            }
        }
        return -1;
    }
    public int cantGajos(int x){
        return cantGajos(raiz, x,0,false);
    }
    private int cantGajos(NodoM p, int x, int actual, boolean bandera){
        int auxa = 0; int auxb=-1; boolean banderas = false;
        if (p==null) {
            return -1;
        }
            if (contains(p, x)==true) {
               auxa = actual;
               banderas = true;
               return auxa;
            }
        for (int i = 1; i <= p.M; i++) {
            if (p.getHijo(i)!=null && banderas == false) {
                     auxb = cantGajos(p.getHijo(i), x, actual + 1, bandera);
            }
        }
        return auxb;
    }
    private boolean contains(NodoM p,int x){
        boolean bandera = false;
        if (p==null) {
            return false;
        }
        for (int i = 1; i <= p.cantDatasUsadas(); i++) {
            if (p.getData(i)== x) {
                bandera = true;
            }
        }
        return bandera;
    }
   
    
    
 //PRACTICO MVIAS
    
    
    
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
    private void niveles(NodoM T){   //Pre: T no es null.
        LinkedList <NodoM> colaNodos   = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();
        
        int nivelActual = 0;
        String coma="";
        
        colaNodos.addLast(T);
        colaNivel.addLast(1);
        
        do{
            NodoM p = colaNodos.pop();       //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.
            
            if (nivelP != nivelActual){ //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel "+nivelP+": ");
                nivelActual = nivelP;
                coma = "";
            }
            
            System.out.print(coma + p);
            coma = ", ";
            
            addHijos(colaNodos, colaNivel, p, nivelP);   
        }while (colaNodos.size() > 0);
        
        System.out.println();
    }
    
    private void addHijos(LinkedList <NodoM> colaNodos, LinkedList<Integer> colaNivel,  NodoM p, int nivelP){
        for (int i=1; i<=NodoM.M; i++){  //Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);
            
            if (hijo != null){
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP+1);
            }
        }
    }    
}


