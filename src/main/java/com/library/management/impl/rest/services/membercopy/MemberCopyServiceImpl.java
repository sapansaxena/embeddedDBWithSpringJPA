package com.library.management.impl.rest.services.membercopy;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.membercopy.MemberCopyDTO;
import com.library.management.api.entity.MemberCopy;
import com.library.management.api.read.service.membercopy.MemberCopyReadService;
import com.library.management.api.rest.services.membercopy.MemberCopyService;
import com.library.management.api.write.service.membercopy.MemberCopyWriteService;
import com.library.management.impl.log.Log;
import com.sun.media.jfxmedia.logging.Logger;


@RestController
@RequestMapping("/rest/")
@Service
public class  MemberCopyServiceImpl implements MemberCopyService  {

	@Inject
	MemberCopyReadService memberCopyReadSvc;
	@Inject
	MemberCopyWriteService memberCopyWriteSvc;
	
	
	@Inject 
	IConverterFactory converterFactory;

	String message = "";

	
	@Override
	@RequestMapping(value = "memberCopy/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public @Valid MemberCopyDTO getMemberCopy(@PathVariable Long  id) {
		MemberCopyDTO memberCopyDTO =memberCopyReadSvc.findById(id);
		
		return memberCopyDTO;
	}
	
	

	@Override
	@RequestMapping(value = "memberCopy", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MemberCopyDTO createMemberCopy(@Valid @RequestBody MemberCopyDTO memberCopyDto) {
		MemberCopy memberCopy = memberCopyWriteSvc.save(memberCopyDto);
		if(memberCopy == null){
			Log.log(Logger.ERROR, "MemberCopy cant be created.");
			return new MemberCopyDTO();
		}
		
		message = "A new memberCopy is created with ID : " + memberCopy.getId();
		Log.log(Logger.INFO, message);
		
		memberCopyDto = (MemberCopyDTO) converterFactory.convertBeans(memberCopy, memberCopyDto);
		
		return  memberCopyDto;
	}

	@Override
	@RequestMapping(value = "memberCopy/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MemberCopyDTO updateMemberCopy(@PathVariable Long  id, @Valid @RequestBody MemberCopyDTO memberCopyDto) {
		
		converterFactory.convertBeans(memberCopyWriteSvc.update(id, memberCopyDto), memberCopyDto);
		
		return memberCopyDto;
	}
	
	@Override
	@RequestMapping(value = "memberCopy/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String  deleteMemberCopy(@PathVariable Long id) {
			
			
		Log.log(Logger.INFO, "MemberCopy with ID"+id+"deleted");
		
		String response = memberCopyWriteSvc.delete(id);
		if(("Success".equalsIgnoreCase(response))){
			message = "MemberCopy with ID "+id+" is deleted.";
			Log.log(Logger.INFO, message);
		}
		
		return response;
	}
	
	 	
	 
	

}
