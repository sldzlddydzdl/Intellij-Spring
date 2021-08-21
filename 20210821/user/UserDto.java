package user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class UserDto {
	private int id;
	private String userId;
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") // Field error in object 'userDto' on field 'birthDate': rejected value [2021-08-11] 에러 해결
	private LocalDate birthDate;
	private LocalDateTime joinDate;
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(int id, String userId, String password, LocalDate birthDate, LocalDateTime joinDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userId=" + userId + ", password=" + password + ", birthDate=" + birthDate
				+ ", joinDate=" + joinDate + "]";
	}

	
	
}
