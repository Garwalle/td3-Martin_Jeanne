package martin.td3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import martin.td3.models.Organization;
import martin.td3.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();
	Optional<User> findById(int id);
	List<User> findByOrganization(Organization organization);
	
	@Modifying
	@Query("UPDATE User u set u.organization = null WHERE u.organization = :organization")
	void updateUserSetOrganization(@Param("organization") Organization organization);
	
	@Query("SELECT u FROM User u WHERE u.organization = :orgaDetail")
	List<User> findUserOrganization(@Param("orgaDetail") Organization orgaDetail);
}
