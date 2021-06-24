package com.loveoffashion.core.workflows;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(property = {
		Constants.SERVICE_DESCRIPTION + "=My Custom Workflow Process for love of fashion",
		Constants.SERVICE_VENDOR + "=Adobe Systems",
		"process.label" + "=My Custom Workflow Process for love of fashion"
})
public class MyCustomWFProcessStep implements WorkflowProcess {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomWFProcessStep.class);

	@Reference
	QueryBuilder queryBuilder;

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments)
			throws WorkflowException {
		//String item = workItem.getWorkflowData().getPayload().toString();
		LOGGER.info("inside my MyCustomWFProcessStep");
		 
	}
}