package martin.td3.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
 
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String name;
    private String domain;
    private String aliases;
    private String organizationsettings;
    
    @OneToMany(mappedBy = "organization",cascade = CascadeType.PERSIST)
    private List<User> users;
    
    @OneToMany(mappedBy = "organization",cascade = CascadeType.PERSIST)
    private List<Groupe> groupes;
    
    public Organization() {
		this(null,null,null);
	}
    
	public Organization(String name) {
		this(name,null,null);
	}
	public Organization(String name, String domain) {
		this(name,domain,null);
	}
	
	public Organization(String name, String domain,String aliases) {
		this.name = name;
		this.domain = domain;
		this.aliases = aliases;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public String getOrganizationsettings() {
		return organizationsettings;
	}

	public void setOrganizationsettings(String organizationsettings) {
		this.organizationsettings = organizationsettings;
	}
	
	@Override
	public String toString() {
		return "Organization [name=" + name + ", users=" + users + "]";
	}
}