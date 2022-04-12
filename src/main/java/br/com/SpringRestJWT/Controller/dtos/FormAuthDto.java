package br.com.SpringRestJWT.Controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormAuthDto {
	
	private String clientId;
	
	private String secretPass;
	

}
