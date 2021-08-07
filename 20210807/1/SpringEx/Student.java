package SpringEx;

public class Student {
	
	private String name;
	private int age;
	private Score score;
	private Address address;
	
	public Student() {
		super();
	}

	public Student(String name, int age, Score score, Address address) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
		this.address = address;
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

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", score=" + score + ", address=" + address + "]";
	}
	

}
