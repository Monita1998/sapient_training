package com.loveoffashion.core.models;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loveoffashion.core.components.beans.CarouselBean;


@Model(adaptables = Resource.class)
public class Carousel{

	
	@Optional
	@Self
	Resource resource;
	
	List<CarouselBean> carousellist = new ArrayList<CarouselBean>();
	

	private static final Logger LOGGER = LoggerFactory.getLogger(Carousel.class);

	
	@PostConstruct
	public void init() throws IOException {
				
		
		/** Reading the multifield( multiple ... fields)**/
		Resource carores = resource.getChild("carousel");
		if(carores != null) {
			Iterator<Resource> itr = carores.listChildren();
			CarouselBean caroBean = null;
			while(itr.hasNext()) {
				caroBean = new CarouselBean();
				Resource childRes  = itr.next(); // item0
				ValueMap prop = childRes.getValueMap();
				String image = (String) prop.get("image");
				LOGGER.info(image);
				String text = (String) prop.get("text");
				LOGGER.info(text);
				caroBean.setImage(image);
				caroBean.setText(text);
				carousellist.add(caroBean);
			}
		}
	}


	public List<CarouselBean> getCarousellist() {
		return carousellist;
	}

	
}