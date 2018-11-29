package Lab8;

public interface DecisionTreeInterface <T> extends BinaryTreeInterface {

	public T getCurrentData();
	public void setCurrentData(T newData);
	public void setResponses(T responseForNo, T responseForYes);
	public boolean isAnswer();
	public void advanceToNo();
	public void advanceToYes();
	public void resetCurrentNode();
	
}
