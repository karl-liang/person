package com.sciov.lh.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

public class VoPlugin extends PluginAdapter {

	VoClassGenerator voClassGenerator = null;
	
	@Override
	public boolean validate(List<String> warnings) {
		return Boolean.parseBoolean(properties.getProperty("enable","true"));
	}
	
	private void initGenerator(IntrospectedTable introspectedTable) {
		if(voClassGenerator == null) {
			voClassGenerator = new VoClassGenerator(this.properties);
		}
			
		voClassGenerator.setContext(context);
		voClassGenerator.setIntrospectedTable(introspectedTable);
		voClassGenerator.setProgressCallback(new VerboseProgressCallback());
	}
	
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		
		initGenerator(introspectedTable);
		
	
		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

		List<CompilationUnit> compilationUnits = voClassGenerator.getCompilationUnits();
		for (CompilationUnit compilationUnit : compilationUnits) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
					PluginUtil.getTargetProject(properties));
			answer.add(gjf);
		}
		return answer;

	}

}
