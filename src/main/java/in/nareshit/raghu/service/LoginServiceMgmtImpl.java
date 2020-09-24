package in.nareshit.raghu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.dao.UserRepo;
import in.nareshit.raghu.dto.UserDTO;

@Service("loginService")
public class LoginServiceMgmtImpl implements LoginServiceMgmt {
	@Autowired
	private UserRepo repo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED,transactionManager = "transactionManager")
	public String authenticate(UserDTO dto) {
		long count=0;
		//use DAO
		count=repo.validate(dto.getUser(),dto.getPwd());
		if(count==0)
			 return "InValid Credentials";
		else
			return "Valid Credentails";
	}

}
