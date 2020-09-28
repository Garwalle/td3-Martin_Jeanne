package martin.td3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import martin.td3.models.Organization;
import martin.td3.repositories.OrgaRepository;
import martin.td3.repositories.UserRepository;

@RestController
@RequestMapping(value={"/orga","/orgas"})
public class RestOrgaController extends RestMainController<Organization> {
	
	protected OrgaRepository orgaRepo;
	
	@Autowired
	public RestOrgaController(OrgaRepository orgaRepo) {
		super(orgaRepo);
	}
	
	@GetMapping(value={"","/","index"})
	public @ResponseBody List<Organization> index() {
		return orgaRepo.findAll();
	}
	
	@GetMapping("{id}")
	public @ResponseBody String findOrgaById(@PathVariable int id) {
		Optional<Organization> opt = orgaRepo.findById(id);
		return opt.get().getName();
		
	}
	
	@PostMapping(value={"","/","index"})
	public @ResponseBody Organization addOrga(@RequestBody Organization orga) {
		orgaRepo.saveAndFlush(orga);
		return orga;
	}
	
	@PatchMapping("{id}")
	public @ResponseBody Organization updateOrga(@PathVariable int id,
			@Param("name") Optional<String> name,
			@Param("domain") Optional<String> domain,
			@Param("aliases") Optional<String> aliases) {
		Optional<Organization> opt = orgaRepo.findById(id);
		Organization toUpdateOrga = null;
		if(opt.isPresent()) {
			toUpdateOrga = opt.get();
			if (name.isPresent()) { toUpdateOrga.setName(name.get()); }
			if (domain.isPresent()) { toUpdateOrga.setDomain(domain.get()); }
			if (aliases.isPresent()) { toUpdateOrga.setAliases(aliases.get()); }
			orgaRepo.saveAndFlush(toUpdateOrga);
		}
		return toUpdateOrga;
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody String deleteOrga(@PathVariable int id) {
		Optional<Organization> opt = orgaRepo.findById(id);
		String orgaName = "Organization not find.";
		if (opt.isPresent()) {
			orgaName = opt.get().getName() + " deleted !";
			orgaRepo.delete(opt.get());
		}
		return orgaName;
		
	}
}

