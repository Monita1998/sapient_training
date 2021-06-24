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

import com.loveoffashion.core.components.beans.AccordionBean;



@Model(adaptables = Resource.class)
/*We are using @Model annotation to specify that this model class is a Sling Model. 
Each data member is annotated with @Inject. This class will be mapped to a resource in JCR. 
*/
public class Accordion{


	
	@Optional
	/*In the sling models, by default all the fields supposed to be required.Sometimes there is a need to mark them as optional 
	 and required specifically.So injector fields can be annotated with @Optional and @Required.*/
	@Self /* this annotation injects the adaptable object itself.*/
	Resource resource;
	
	List<AccordionBean> accordionlist = new ArrayList<AccordionBean>();
	


	private static final Logger LOGGER = LoggerFactory.getLogger(Accordion.class);

	
	@PostConstruct
	/*The @PostConstruct annotation can be used to add methods which are invoked upon completion of all injections:
	 *  This method automatically gets called when a sling model instance is created.*/
	public void init() throws IOException {
		
	
		/** Reading the multifield( multiple ... fields)**/
		Resource multifieldRootRes = resource.getChild("accordion");
		if(multifieldRootRes != null) {
			Iterator<Resource> itr = multifieldRootRes.listChildren();
			AccordionBean accordionBean = null;
			while(itr.hasNext()) {
				accordionBean = new AccordionBean();
				Resource childRes  = itr.next(); 
				ValueMap prop = childRes.getValueMap();
				String tabTitle = (String) prop.get("tabTitle");
				String text = (String) prop.get("text");
				accordionBean.setTabTitle(tabTitle);
				accordionBean.setText(text);
				accordionlist.add(accordionBean);
			}
		}
	}


	public List<AccordionBean> getAccordionlist() {
		return accordionlist;
	}

}

/*@inject->This annotation is used to inject a property, resource, request anything.This is a generic annotation, 
 * which traverses all the sling model injectors based on service ranking.*/
 */