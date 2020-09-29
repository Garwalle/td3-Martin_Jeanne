package martin.td3.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import martin.td3.models.Organization;
import martin.td3.repositories.OrgaRepository;

@CrossOrigin("http://127.0.0.1:8080")
@RestController
@RequestMapping(value = { "/orga", "/orgas" })
public class RestOrgaController extends RestMainController<Organization> {

	@Autowired
	public RestOrgaController(OrgaRepository orgaRepo) {
		super(orgaRepo);
	}

	@Override
	protected void updateObject(Organization toUpdateObject, HttpServletRequest request) {
		if (request.getParameter("name") != null) {
			toUpdateObject.setName(request.getParameter("name"));
		}
		if (request.getParameter("domain") != null) {
			toUpdateObject.setDomain(request.getParameter("domain"));
		}
		if (request.getParameter("aliases") != null) {
			toUpdateObject.setAliases(request.getParameter("aliases"));
		}
	}
}
