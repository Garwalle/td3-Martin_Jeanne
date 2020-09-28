package martin.td3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import martin.td3.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
	// Find
	List<Organization> findAll();
	List<Organization> findByDomain(String domain);
	public Optional<Organization> findByName(String name);
	public Optional<Organization> findById(int id);
	
	@Query("SELECT o FROM Organization o WHERE o.id = ?1")
	Organization findByIdInteger(Integer id);
	
	@Query("SELECT o FROM Organization o WHERE o.name LIKE %?1% OR o.domain LIKE %?1% OR o.aliases LIKE %?1%")
	public List<Organization> search(String keyword);
	
	
	// Delete
	List<Organization> deleteById(int id);
	    
	    
	    
}
