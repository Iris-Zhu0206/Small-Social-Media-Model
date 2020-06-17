import java.util.ArrayList;

public class UndirectedGraph<T> {
	private ArrayList<T> vertexes;
	
	private ArrayList<ArrayList<Boolean>> matrix;
	
	UndirectedGraph(){
		
		vertexes = new ArrayList<T>();
		matrix = new ArrayList<ArrayList<Boolean>>();
	}
	
	public int addVertex(T p) {
		printerror("before addVertex");
		ArrayList<Boolean>list = new ArrayList<Boolean>();
		for(int i = 0; i < vertexes.size(); i++) {
			list.add(false);
		}
		matrix.add(list);
		vertexes.add(p);
		for(int i = 0; i < vertexes.size(); i++) {
			matrix.get(i).add(false);
		}
		printerror("after addVertex");
		
		return vertexes.size() -1;
	}
	
	private void printerror(String theError) {
		System.out.println("----------"+theError+":");
		for(int i = 0; i<matrix.size(); i++) {
			for(int j = 0; j<matrix.size();j++) {
				System.out.println(matrix.get(i).get(j)+" ");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}
	 
	public ArrayList<T> search(String keyword){
		ArrayList<T> searchResults = new ArrayList<T>();
		for(int i = 0; i < vertexes.size(); i++) {
			String ver = vertexes.get(i).toString();
			if(keyword.isEmpty()) {
				searchResults.add(vertexes.get(i));
				continue;
			}
			if(ver.equals(keyword)) {
				searchResults.add(vertexes.get(i));
			}
		}
		return searchResults;
	}
	
	public void addEdge(int firstIndex, int secondIndex) {
		printerror("Before add edge: "+ firstIndex + ":" + secondIndex);
		matrix.get(firstIndex).set(secondIndex, true);
		matrix.get(secondIndex).set(firstIndex, true);
		printerror("After add edge: " + firstIndex + ":" + secondIndex);
	}
	
	public boolean hasEdge(int firstIndex, int secondIndex) {
		return matrix.get(firstIndex).get(secondIndex);
	}
	
	public void updateVertex(int index, T profile) {
		vertexes.set(index, profile);
	}
	
	public ArrayList<T> getAdjacentVertexes(int index){
		ArrayList<T> result = new ArrayList<T>();
		for(int i = 0; i < matrix.size(); i ++) {
			if(matrix.get(index).get(i)){
				result.add(vertexes.get(i));
			}
		}
		return result;
	}

}
