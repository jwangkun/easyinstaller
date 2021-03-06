package com.jianglibo.vaadin.dashboard.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.jianglibo.vaadin.dashboard.config.ApplicationConfig;
import com.jianglibo.vaadin.dashboard.domain.Software;

@Component
public class SoftwareUtil {

	public static final Pattern COMMON_SCRIPT_TAG = Pattern.compile("^.*insert-common-script-here:\\s*(\\S+)\\s*$");
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SoftwareUtil.class);
	
	private final ApplicationContext context;
	private final List<String> scriptSources;
	
	@Autowired
	public SoftwareUtil(ApplicationContext context, ApplicationConfig applicationConfig) {
		this.context = context;
		this.scriptSources = applicationConfig.getScriptSources();
	}
	
	private List<String> parseOne(String rsptn) {
		for(String scriptSource: scriptSources) {
			String fullPath = scriptSource + rsptn;
			Resource rs = context.getResource(fullPath);
			try {
				List<String> lines =  CharStreams.readLines(new InputStreamReader(rs.getInputStream(), Charsets.UTF_8));
				LOGGER.info("found code snippet in {}", fullPath);
				return lines;
			} catch (IOException e) {
				LOGGER.info("code snippet not found in {}", fullPath);
			}
		}
		return Lists.newArrayList();
	}
	
	private List<String> getParsedLines(List<String> lines) throws IOException {
			List<String> newlines = Lists.newArrayList();
			for(String line : lines) {
				Matcher m = COMMON_SCRIPT_TAG.matcher(line);
				if (m.matches()) {
					newlines.addAll(parseOne(m.group(1).trim()));
				} else {
					newlines.add(line);
				}
			}
			return newlines;
	}
	
	// allow code snippet to insert. if a line in code contains "insert-common-script-here: spring resource url", then get resource content, and insert here.	
	public String getParsedCodeToExecute(Software software) {
		String code = software.getCodeToExecute();
		try {
			List<String> lines = CharStreams.readLines(new StringReader(code));
			// will process 2 level depth substitute.
			List<String> newlines =  getParsedLines(lines);
			newlines = getParsedLines(newlines);
			return Joiner.on(software.parseLs()).join(newlines);
		} catch (IOException e) {
			return code;
		}
	}
}
