package martin.td3.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

abstract public class RestMainController<T> {
	
	protected JpaRepository<T, Integer> repo;

	@Autowired
	public RestMainController(JpaRepository<T, Integer> repo) {
		this.repo = repo;
	}
	
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
		
	
	@PatchMapping("{id}")
	public @ResponseBody T update(@PathVariable int id,
			HttpServletRequest request) {
		Optional<T> opt = repo.findById(id);
		T toUpdateObject = null;
		if(opt.isPresent()) {
			toUpdateObject = opt.get();
			updateObject(toUpdateObject,request);
			repo.saveAndFlush(toUpdateObject);
		}
		return toUpdateObject;
	}
	
	protected abstract void updateObject(T toUpdateObject, HttpServletRequest request);

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

