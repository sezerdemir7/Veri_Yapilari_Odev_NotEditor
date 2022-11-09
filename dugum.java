
package notedtr;


public class dugum {
    
    
   
    int index=0;
    
    String kelime;

    public dugum(int index, String kelime) {
        this.index = index;
        this.kelime = kelime;
    }

    public dugum() {
    }

   

    @Override
    public String toString() {
        return index + "-" +kelime ;
    }
    
    
    
}
