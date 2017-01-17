import com.microsoft.azure.storage.table.TableServiceEntity;

public class LogEntity extends TableServiceEntity{
	public LogEntity(String pkey, String rkey){
		this.partitionKey = pkey;
		this.rowKey = rkey;
	}
	
	String t;
	
	
	public String getT(){
		return this.t;
	}
	
	public void setT(String c){
		this.t = c;
	}
}