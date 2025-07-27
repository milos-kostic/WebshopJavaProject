package cubes.main.security;

 
 

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource myDataSource; // paket javax.sql
	
	@Override // overrajdujemo:
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// brine o korisnicima. Gde citas info o korisnicima kad pokusaju login
		// mi radimo da metoda trazi u bazi korisnika, ne mora, moze i fajl.
		// i koja je rola: admin ili obican ili opcije.
		
		
		// da cita fiksno korisnike za sad: dodajemo:
//		UserBuilder users = User.withDefaultPasswordEncoder(); // bez enkodovanja lozinke	
//		auth.inMemoryAuthentication()
//		.withUser(users.username("milos").password("milos123").roles("admin"))
//		.withUser(users.username("pera").password("pera123").roles("admin"))
//		.withUser(users.username("jovan").password("jovan123").roles("employee")); // i tako redom dodajem
		
		
		
		// da cita iz baze: Spring ima unapred podesen veci deo:
		auth.jdbcAuthentication().dataSource(myDataSource);
		
		
		
		  
	}
	
	
	// override ove iste metode: 
	// podesavamo da login ide na nasu login str a ne na podrazumevanu Springovu:
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		
		//.anyRequest().authenticated()      -- sve resurse je branio pa je pravio problem
		.antMatchers("/").permitAll()   // sve Rute su otvorene, u web.xml postavimo: proveri sve rute, 
						// a ovde postavimo: dopusti svima, ali za Ove pazi:
		
		// kad ima vise rola onda je metoda: .hasAnyRole
		// ova linija znaci dozvoljene su sve str osim "product-list" za koju treba da je ulogovan i to kao admin rola
		// dodajemo druge stranice:
		.antMatchers("admin/dist/**").permitAll() // za resurse .css  .js i pluginove
		.antMatchers("/api/**").permitAll() // da radi i Post metod. treba jos nesto, ne radi
		.antMatchers("admin/plugins/**").permitAll() 
		
		.antMatchers("/admin/product-list").hasAnyRole("admin, employee")	
		.antMatchers("/admin/product-form").hasAnyRole("admin, employee")
		
		.antMatchers("/admin/category-list").hasRole("admin")
		.antMatchers("/admin/category-form").hasRole("admin")
		.antMatchers("/admin/sticker-list").hasRole("admin")
		.antMatchers("/admin/sticker-form").hasRole("admin")
		.antMatchers("/admin/user-list").hasRole("admin")
		.antMatchers("/admin/user-form").hasRole("admin")
		 
		
		.antMatchers("/admin/**").hasAnyRole("admin, employee") 
			// ** sve ostale stranice i akcije pocinju sa /admin moze da pristupi i admin i employee
			// tj da budu ulogovani
		
		.and().formLogin().loginPage("/login-page").loginProcessingUrl("/authenticateTheUser").permitAll();
		
		
		// privremeno:
		http.cors().and().csrf().ignoringAntMatchers("/api/**").disable();
	}
	
}
