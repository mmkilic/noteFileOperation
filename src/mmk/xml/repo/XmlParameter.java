package mmk.xml.repo;

public class XmlParameter<T> {
	private String name;
	private String type;
	private T value;
    
	public XmlParameter() { }
	
	public XmlParameter(String name, String type, T value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public T getValeu() {
		return value;
	}

	public void setValeu(T valeu) {
		this.value = valeu;
	}
	
	@Override
	public String toString() {
		return name +" - "+ type +" - "+ value;
	}
}
