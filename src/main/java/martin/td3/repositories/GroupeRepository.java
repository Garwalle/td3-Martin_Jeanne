package martin.td3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import martin.td3.models.Groupe;
import martin.td3.models.Organization;

public interface GroupeRepository extends JpaRepository<Groupe, Integer> {
	Groupe findById(int id);
	List<Groupe> findByOrganization(Organization organization);
	List<Groupe> deleteByOrganization(Organization organization);
	
	@Modifying
	@Query("UPDATE Groupe g set g.organization = null WHERE g.organization = :organization")
	void updateGroupeSetOrganization(@Param("organization") Organization organization);
	
	@Query("SELECT g FROM Groupe g WHERE g.organization = :orgaDetail")
	List<Groupe> findGroupeOrganization(@Param("orgaDetail") Organization orgaDetail);
}
