package mvca;

public class Vertice {
	String id;

	public Vertice(){
//		this.id = String.valueOf(getClass().hashCode());
	}
        
	
	public Vertice(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    Object getCor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
