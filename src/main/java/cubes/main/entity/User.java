package cubes.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity // persistence paket
@Table(name="users")
public class User {
 
	@Id
	// Generated Value sami unosimo
	@Column
	private String username; // ovo je id za users tabelu
	@Column
	private String password;
	@Column
	private boolean enabled;
	@Column
	private String name;
	@Column
	private String surname;
	// vracena veza vise-na-vise: Role-Users: kako je podrazumevano za role i korisnike:
	@ManyToMany(
			cascade= {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
					},
			fetch = FetchType.EAGER
			)
 	@JoinTable(
 			name="authorities", 
 			joinColumns  = @JoinColumn(name="username"),
 			inverseJoinColumns = @JoinColumn(name="authority"))	
	private List<Role> roles;
	
	 
	
	public User() {
		
	}

	public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEncodedPassword() {
		return password.replace("{bcrypt}", "");
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() { // bilo je isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {  
		this.roles = roles;
	}

	
	// za pojedinacne Role geter i seter brisemo:
//	public Role getRole() {
//		if(roles==null || roles.isEmpty()) {
//			return new Role();
//		}//		
//		return roles.get(0); // vrati prvu iz liste
//	}
//		
//	public void setRole(Role role) {
//		roles.add(role); // tu rolu ubacujem u niz
//	}

	
	
	
	public void generateBCryptPassword() { // get i set nazivamo metodu samo ako je pozivamo na jsp stranici
		
		if( ! getPassword().contains("{bcrypt}")) { // dodata provera: ako nije enkriptovan onda kriptuj
			
			String password = new BCryptPasswordEncoder().encode(this.getPassword());  // enkript pre unosa u bazu
			this.setPassword("{bcrypt}"+password);
			
		}
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
