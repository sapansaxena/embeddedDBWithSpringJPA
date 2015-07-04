package com.library.management.impl.rest.services.member;

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
import com.library.management.api.dto.member.MemberDTO;
import com.library.management.api.entity.Member;
import com.library.management.api.read.service.member.MemberReadService;
import com.library.management.api.rest.services.member.MemberService;
import com.library.management.api.write.service.member.MemberWriteService;
import com.library.management.impl.log.Log;
import com.sun.media.jfxmedia.logging.Logger;


@RestController
@RequestMapping("/rest/")
@Service
public class  MemberServiceImpl implements MemberService  {

	@Inject
	MemberReadService memberReadSvc;
	@Inject
	MemberWriteService memberWriteSvc;
	
	
	@Inject 
	IConverterFactory converterFactory;

	String message = "";

	
	@Override
	@RequestMapping(value = "member/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public @Valid MemberDTO getMember(@PathVariable Long  id) {
		MemberDTO memberDTO =memberReadSvc.findById(id);
		
		return memberDTO;
	}
	
	

	@Override
	@RequestMapping(value = "member", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MemberDTO createMember(@Valid @RequestBody MemberDTO memberDto) {
		Member member = memberWriteSvc.save(memberDto);
		if(member == null){
			Log.log(Logger.ERROR, "Member cant be created.");
			return new MemberDTO();
		}
		
		message = "A new member is created with ID : " + member.getId();
		Log.log(Logger.INFO, message);
		
		memberDto = (MemberDTO) converterFactory.convertBeans(member, memberDto);
		
		return  memberDto;
	}

	@Override
	@RequestMapping(value = "member/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MemberDTO updateMember(@PathVariable Long  id, @Valid @RequestBody MemberDTO memberDto) {
		
		converterFactory.convertBeans(memberWriteSvc.update(id, memberDto), memberDto);
		
		return memberDto;
	}
	
	@Override
	@RequestMapping(value = "member/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String  deleteMember(@PathVariable Long id) {
			
			
		Log.log(Logger.INFO, "Member with ID"+id+"deleted");
		
		String response = memberWriteSvc.delete(id);
		if(("Success".equalsIgnoreCase(response))){
			message = "Member with ID "+id+" is deleted.";
			Log.log(Logger.INFO, message);
		}
		
		return response;
	}
	
	 	
	 
	

}
