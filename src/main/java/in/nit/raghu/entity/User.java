package in.nit.raghu.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "app_user_tab")
@Data
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String username;
	private String password;
	/* For making changes for no reasons */
	@ElementCollection
	private List<String> roles;
}
