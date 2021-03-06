package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.RendeRepository;

import domain.Rende;



@Component
@Transactional
public class StringToRendeConverter implements Converter<String, Rende>{
	
	@Autowired
	RendeRepository rendeRepository;
	
	@Override
	public Rende convert(final String text){
		Rende result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = this.rendeRepository.findOne(id);
			}
		}catch(final Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
}
