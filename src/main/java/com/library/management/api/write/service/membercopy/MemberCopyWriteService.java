package com.library.management.api.write.service.membercopy;

import com.library.management.api.dto.membercopy.MemberCopyDTO;
import com.library.management.api.entity.MemberCopy;

public interface  MemberCopyWriteService {
	
	public MemberCopy save(MemberCopyDTO memberCopy);
	public String delete(Long id);
	public MemberCopy update(Long id, MemberCopyDTO memberCopyDTO);


}
