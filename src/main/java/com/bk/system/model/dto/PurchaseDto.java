package com.bk.system.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bk.system.model.entity.Classes;
import com.bk.system.model.entity.RegStatus;

import lombok.Data;
@Data
public class PurchaseDto {
	Long id;
	private Integer remainingCredit;
	RegStatus status;
	private Date expirationDate;

//    private User user;
//    private Packaged packaged; 

}
