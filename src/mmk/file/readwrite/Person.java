package mmk.file.readwrite;

public class Person {
	private String name;
	private int age;
	private int height;
	private int weight;
	
	public Person(String name, int age, int height, int weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	private double coef() {
		return Math.round(100.0 * height / weight) / 100.0;
	}
	
	public String personInfo() {
		return (name + " | " + age + " | " + " : " + coef());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
