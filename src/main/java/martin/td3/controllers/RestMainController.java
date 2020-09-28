package martin.td3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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

@RestController
public class RestMainController<T> {
	
	@Autowired
	private JpaRepository<T, Integer> repo;
	
	@GetMapping(value={"","/","index"})
	public @ResponseBody List<T> index() {
		return repo.findAll();
	}
	
	@GetMapping("{id}")
	public @ResponseBody T findById(@PathVariable int id) {
		Optional<T> opt = repo.findById(id);
		return opt.get();
		
	}
	
	@PostMapping(value={"","/","index"})
	public @ResponseBody T add(@RequestBody T t) {
		repo.saveAndFlush(t);
		return t;
	}
	
	// UPDATE NE PEUT PAS ETRE EXTEND, A FAIRE MANUELLEMENT SUR LES CLASSES
	
	/*
	@PatchMapping("{id}")
	public @ResponseBody Organization updateOrga(@PathVariable int id,
			@Param("name") Optional<String> name,
			@Param("domain") Optional<String> domain,
			@Param("aliases") Optional<String> aliases) {
		Optional<Organization> opt = repo.findById(id);
		Organization toUpdateOrga = null;
		if(opt.isPresent()) {
			toUpdateOrga = opt.get();
			if (name.isPresent()) { toUpdateOrga.setName(name.get()); }
			if (domain.isPresent()) { toUpdateOrga.setDomain(domain.get()); }
			if (aliases.isPresent()) { toUpdateOrga.setAliases(aliases.get()); }
			repo.saveAndFlush(toUpdateOrga);
		}
		return toUpdateOrga;
	}
	*/
	
	@DeleteMapping("{id}")
	public @ResponseBody T delete(@PathVariable int id) {
		Optional<T> opt = repo.findById(id);
		if (opt.isPresent()) {
			T temp = opt.get();
			repo.delete(opt.get());
			return temp;
		}
		return null;
		
	}
}

